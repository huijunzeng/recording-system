package com.zjh.recording.system.user.config;

import com.zjh.recording.system.common.constant.Constant;
import com.zjh.recording.system.common.exception.BusinessException;
import com.zjh.recording.system.common.util.JSONUtil;
import com.zjh.recording.system.oauth.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjh.recording.system.common.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 用户信息拦截器
 * @Author: ZJH
 * @Date: 2020/3/1 12:18
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    /**
     * 请求头变量key
     */
    private static final String X_CLIENT_USER = "X-Client-User";

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截token
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(token)) {
            // 抛自定义异常？
            log.error("token is null");
            return false;
        }
        String userInfo = this.checkTokenAndParseAsJson(token);
        String decodeUserInfo = URLDecoder.decode(userInfo, StandardCharsets.UTF_8.toString());
        log.info("X-Client-User========{}", decodeUserInfo);
        log.info("decodeUserInfo========{}", new ObjectMapper().readValue(decodeUserInfo, Map.class));
        UserContextHolder.getInstance().setContext(new ObjectMapper().readValue(decodeUserInfo, Map.class));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContextHolder.getInstance().clear();
    }

    /**
     * 将checkToken端点返回的token信息转为json，然后保存到请求头
     * @param token
     * @return
     */
    private String checkTokenAndParseAsJson(String token) {
        String tokenBodyInfo = "{}";
        String substringToken = StringUtils.substring(token, Constant.BEARER.length());
        tokenBodyInfo = authorizationService.parseToken(substringToken);
        // todo 优化 解释token异常判断
        if (tokenBodyInfo.contains("code") && tokenBodyInfo.contains("msg")) {
            Map<String, Object> objectMap = JSONUtil.jsonToMap(tokenBodyInfo);
            throw new BusinessException(Integer.valueOf(objectMap.get("code").toString()), objectMap.get("msg").toString());
        }
        log.info("tokenBodyInfo========{}", tokenBodyInfo);
        return tokenBodyInfo;
    }
}