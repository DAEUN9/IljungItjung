package com.iljungitjung.domain.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iljungitjung.domain.notification.dto.NotificationMessageRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.exception.*;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    @Value("${message.ncloud.service_id}")
    private String serviceId;
    @Value("${message.ncloud.access_key}")
    private String accessKey;
    @Value("${message.ncloud.secret_key}")
    private String secretKey;
    @Override
    @Transactional
    public NotificationResponseDto sendMessage(NotificationRequestDto requestDto) {
        Long time = System.currentTimeMillis();

        NotificationMessageRequestDto notificationMessageRequestDto = new NotificationMessageRequestDto(requestDto);
        // convert object to json
        // jackson으로 파싱하면 argument가 없는 생성자를 찾지 못함
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", accessKey);
        String sig = makeSignature(time);
        headers.set("x-ncp-apigw-signature-v2", sig);

        try {
            // object -> json
            jsonBody = objectMapper.writeValueAsString(notificationMessageRequestDto);
        } catch (JsonProcessingException e) {
            throw new ConvertToJsonErrorException();
        }

        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        NotificationResponseDto notificationResponseDto;
        try {
            notificationResponseDto = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages"), body, NotificationResponseDto.class); // 반환타입
        } catch (URISyntaxException e) {
            throw new MessageUriSyntaxErrorException();
        }
        if (!(notificationResponseDto.getStatusCode().equals("202"))) {
            throw new FailSendMessageException();
        }

        return notificationResponseDto;
    }

    public String makeSignature(Long time) {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+ serviceId+"/messages";
        String timestamp = time.toString();
        SecretKeySpec signingKey;
        Mac mac;
        byte[] rawHmac = new byte[0];

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        try {
            signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
        } catch (UnsupportedEncodingException e) {
            throw new SecretKeyEncodingException();
        } catch (NoSuchAlgorithmException e) {
            throw new NoExistMacInstanceException();
        } catch (InvalidKeyException e) {
            throw new InvalidSigningKeyException();
        }

        try {
            rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new MacFinalMessageEncodingException();
        }
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }
}
