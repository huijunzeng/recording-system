package com.zjh.recording.system.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 用户上下文
 * @author zjh
 */
@Slf4j
public class UserContextHolder {

    private ThreadLocal<Map<String, Object>> threadLocal;

    private UserContextHolder() {
        this.threadLocal = new ThreadLocal<>();
    }

    public static UserContextHolder getInstance() {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final UserContextHolder sInstance = new UserContextHolder();
    }

    public void setContext(Map<String, Object> map) {
        threadLocal.set(map);
    }

    public Map<String, Object> getContext() {
        return threadLocal.get();
    }

    public String getUserId() {
        Object userId = Optional.ofNullable(threadLocal.get()).orElse(new HashMap<>()).get("user_id");
        return Objects.nonNull(userId)?userId.toString():"";
    }

    public String getUsername() {
        log.info("username:{}", Optional.ofNullable(threadLocal.get()).orElse(new HashMap<>()).get("username"));
        Object dbUsername = Optional.ofNullable(threadLocal.get()).orElse(new HashMap<>()).get("username");
        return Objects.nonNull(dbUsername)?dbUsername.toString():"";
    }

    public void clear() {
        threadLocal.remove();
    }

}
