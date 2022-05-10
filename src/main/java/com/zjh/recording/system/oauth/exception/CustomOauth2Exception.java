package com.zjh.recording.system.oauth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author zjh
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauth2Exception extends OAuth2Exception {

    public CustomOauth2Exception(OAuth2Exception oAuth2Exception) {
        super(oAuth2Exception.getMessage(), oAuth2Exception);
    }

    public CustomOauth2Exception(String msg, Throwable t) {
        super(msg, t);

    }
    public CustomOauth2Exception(String msg) {
        super(msg);
    }
}