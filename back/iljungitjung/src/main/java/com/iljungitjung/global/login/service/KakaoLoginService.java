package com.iljungitjung.global.login.service;

import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.service.UserService;
import com.iljungitjung.global.login.exception.NotMemberException;
import com.iljungitjung.global.oauth.dto.KakaoTokenResponseDto;
import com.iljungitjung.global.oauth.dto.KakaoUserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class KakaoLoginService implements LoginService{

    @Value("${login.kakao.path}")
    private String PATH;

    @Value("${login.kakao.host}")
    private String HOST;

    @Value("${login.kakao.rest_api_key}")
    private String REST_API_KEY;

    @Value("${login.kakao.redirect_uri}")
    private String REDIRECT_URI;

    @Value("${login.kakao.response_type}")
    private String RESPONSE_TYPE;

    @Value("${oauth.kakao.grant_type}")
    private String OAUTH_GRANT_TYPE;

    @Value("${oauth.kakao.token.path}")
    private String OAUTH_TOKEN_PATH;

    private final UserService userService;

    private String createUri(){
        return UriComponentsBuilder.newInstance().scheme("https")
                .host(HOST)
                .path(PATH)
                .queryParam("client_id", REST_API_KEY)
                .queryParam("redirect_uri", REDIRECT_URI)
                .queryParam("response_type", RESPONSE_TYPE)
                .build().toString();
    }

    @Override
    public void sendRedirectToLoginPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(createUri());
        }catch (Exception e){
            log.error("sendRedirect error : {}", e.getMessage());
        }
    }

    private String createTokenRequestUri(){
        return UriComponentsBuilder.newInstance().scheme("https")
                .host(HOST)
                .path(OAUTH_TOKEN_PATH)
                .build().toString();
    }

    private MultiValueMap<String, String> createTokenRequestBody(String code){
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", OAUTH_GRANT_TYPE);
        parameters.add("client_id", REST_API_KEY);
        parameters.add("redirect_uri", REDIRECT_URI);
        parameters.add("code", code);
        return parameters;
    }

    private HttpHeaders createUserInfoRequestHeader(String accessToken){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBearerAuth(accessToken);
        return httpHeaders;
    }
    @Override
    public void sendRedirectToClientUriWithEmail(String clientUri, String code, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoTokenResponseDto> kakaoTokenResponse = restTemplate.postForEntity(createTokenRequestUri(), createTokenRequestBody(code), KakaoTokenResponseDto.class);
        log.debug("kakaoTokenReponse : {}", kakaoTokenResponse.getBody());

        HttpEntity<String> entity = new HttpEntity<>(null, createUserInfoRequestHeader(kakaoTokenResponse.getBody().getAccessToken()));

        ResponseEntity<KakaoUserInfoResponseDto> responseEntity = restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET, entity, KakaoUserInfoResponseDto.class);
        log.debug("responseEntity : {}", responseEntity.getBody());

        if(!userService.isExistUserByEmail(responseEntity.getBody().getKakaoAccount().getEmail())) throw new NotMemberException();

        log.debug("session id : {}", session.getId());

        try {
            response.sendRedirect(clientUri);
        } catch (Exception e) {
            log.error("sendRedirect error : {}", e.getMessage());
        }
    }
}
