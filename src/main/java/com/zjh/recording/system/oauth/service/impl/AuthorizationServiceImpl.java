package com.zjh.recording.system.oauth.service.impl;

import com.zjh.recording.system.common.constant.Constant;
import com.zjh.recording.system.common.util.JSONUtil;
import com.zjh.recording.system.oauth.service.AuthorizationService;
import com.zjh.recording.system.user.service.UserService;
import com.zjh.recording.system.user.vo.UserDetailsVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;


@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.security.oauth2.jwt.signing-key:123456}")
    private String SIGNING_KEY;

    private static final String ACCESS = "access:";
    private static final String AUTH_TO_ACCESS = "auth_to_access:";
    private static final String AUTH = "auth:";
    private static final String REFRESH_AUTH = "refresh_auth:";
    private static final String ACCESS_TO_REFRESH = "access_to_refresh:";
    private static final String REFRESH = "refresh:";
    private static final String REFRESH_TO_ACCESS = "refresh_to_access:";
    private static final String CLIENT_ID_TO_ACCESS = "client_id_to_access:";
    private static final String UNAME_TO_ACCESS = "uname_to_access:";

    @Override
    public UserDetailsVO getByUniqueId(String uniqueId) {
        return userService.getByUniqueId(uniqueId);
    }

    @Override
    public boolean logout(String token) {
        String authToAccessKey = getAuthToAccess(token);
        boolean flag = stringRedisTemplate.delete(ACCESS + token) && stringRedisTemplate.delete(AUTH + token) && stringRedisTemplate.delete(AUTH_TO_ACCESS + authToAccessKey);
        return Boolean.TRUE;
    }

    @Override
    public String parseToken(String token) {
        Claims body = this.getJwtClaims(token).getBody();
        return JSONUtil.objectToJson(body);
    }

    private Jws<Claims> getJwtClaims(String token) {
        if (token.startsWith(Constant.BEARER)) {
            token = StringUtils.substring(token, Constant.BEARER.length());
        }
        log.info("getJwtClaims======={}", token);
        log.info(SIGNING_KEY);
        return Jwts.parser().setSigningKey(SIGNING_KEY.getBytes()).parseClaimsJws(token);
    }

    private String getAuthToAccess(String token) {
        Jws<Claims> jwtClaims = this.getJwtClaims(token);
        Claims body = jwtClaims.getBody();
        Map<String, String> values = new LinkedHashMap();
        values.put(Constant.USERNAME, body.get(Constant.USER_NAME).toString());
        values.put(Constant.CLIENT_ID, body.get(Constant.CLIENT_ID).toString());
        values.put(Constant.SCOPE, OAuth2Utils.formatParameterList(new TreeSet((Collection) body.get(Constant.SCOPE))));
        JSONUtil.objectToJson(values);
        return this.generateKey(values);
    }

    private String generateKey(Map<String, String> values) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(values.toString().getBytes("UTF-8"));
            return String.format("%032x", new BigInteger(1, bytes));
        } catch (NoSuchAlgorithmException var4) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).", var4);
        } catch (UnsupportedEncodingException var5) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).", var5);
        }
    }
}
