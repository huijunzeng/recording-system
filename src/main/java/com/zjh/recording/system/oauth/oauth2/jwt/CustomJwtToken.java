package com.zjh.recording.system.oauth.oauth2.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义JWT
 * @Author: ZJH
 * @Date: 2019/6/26 18:50
 */
@Slf4j
public class CustomJwtToken implements TokenEnhancer {

    /**
     *
     * @param oAuth2AccessToken  存放用户的token信息
     * @param oAuth2Authentication  存放用户的认证信息
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        //自定义token内容
        Map<String, Object> additionalInfo = new HashMap();
        Collection<GrantedAuthority> authorities = oAuth2Authentication.getAuthorities();
        Set<String> roles = authorities.stream().map(grantedAuthority -> grantedAuthority.getAuthority()).collect(Collectors.toSet());
        log.info("roles: {}", roles);
        additionalInfo.put("roles", roles);// 这里加入用户的角色code
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
