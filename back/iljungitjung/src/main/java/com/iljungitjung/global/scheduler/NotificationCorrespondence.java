package com.iljungitjung.global.scheduler;

import com.iljungitjung.domain.notification.dto.MessageRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.exception.FailSignatureKeyErrorException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@RequiredArgsConstructor
public class NotificationCorrespondence {

    @Value("${message.ncloud.service_id}")
    private String NCLOUD_SERVICE_ID;
    @Value("${message.ncloud.access_key}")
    private String NCLOUD_ACCESS_KEY;
    @Value("${message.ncloud.secret_key:default}")
    private String NCLOUD_SECRET_KEY;
    private final String SPACE = " ";
    private final String NEWLINE = "\n";
    private final String METHOD = "POST";
    private final String SIGNATURE_URL = "/sms/v2/services/";
    private final String MESSAGE_REQUEST_URL = "https://sens.apigw.ntruss.com/sms/v2/services/";

    public NotificationResponseDto sendNcloud(HttpEntity<MessageRequestDto> body) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(MESSAGE_REQUEST_URL + NCLOUD_SERVICE_ID + "/messages",
                        HttpMethod.POST, body, NotificationResponseDto.class).getBody();
    }
    public HttpHeaders makeHeaders() {
        Long time = System.currentTimeMillis();
        HttpHeaders headers = new HttpHeaders();
        String signature = makeSignature(time);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", NCLOUD_ACCESS_KEY);
        headers.set("x-ncp-apigw-signature-v2", signature);
        return headers;
    }

    public String makeSignature(Long time) {
        String timeStamp = time.toString();
        SecretKeySpec signingKey;
        Mac mac;
        byte[] rawHmac = new byte[0];

        String message = new StringBuilder()
                .append(METHOD)
                .append(SPACE)
                .append(SIGNATURE_URL + NCLOUD_SERVICE_ID +"/messages")
                .append(NEWLINE)
                .append(timeStamp)
                .append(NEWLINE)
                .append(NCLOUD_ACCESS_KEY)
                .toString();
        try {
            signingKey = new SecretKeySpec(NCLOUD_SECRET_KEY.getBytes("UTF-8"), "HmacSHA256");
            mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new FailSignatureKeyErrorException();
        }
        return Base64.encodeBase64String(rawHmac);
    }
}
