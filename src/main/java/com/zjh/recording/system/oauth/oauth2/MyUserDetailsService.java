package com.zjh.recording.system.oauth.oauth2;


import com.zjh.recording.system.common.enums.LogicEnum;
import com.zjh.recording.system.oauth.exception.AuthorizationExceptionEnums;
import com.zjh.recording.system.user.service.UserService;
import com.zjh.recording.system.user.vo.UserDetailsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * UserDetails => Spring Security基础接口，包含某个用户的账号，密码，权限，状态（是否锁定）等信息。只有getter方法。
 * Authentication => 认证对象，认证开始时创建，认证成功后存储于SecurityContext上下文
 * principal => 用户信息对象，是一个Object，通常可转为UserDetails
 *
 * 继承UserDetailsService，实现返回一个用户信息的UserDetails对象  SpringSecurity框架
 * @Author: ZJH
 * @Date: 2019/6/27 13:17
 */

@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 查出UserDetails并比较
     * 根据提供的密码加密器passwordEncoder（默认是）去比较当前请求的密码与数据库查出来的密码比较，不匹配则报错：
     * {
     *     "error": "invalid_grant",
     *     "error_description": "Bad credentials"
     * }
     * 可通过在这根据用户名从数据库查找该用户名数据，并且封装该用户的角色
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsVO vo = userService.getByUniqueId(username);
        if (Objects.isNull(vo)) {
            throw new UsernameNotFoundException(AuthorizationExceptionEnums.NOT_EXIST_USER_ACCOUNT.getMsg());
        }
        log.info("loadUserByUsername======: {}", vo.toString());
        MyUserDetails user = new MyUserDetails(vo.getUsername(), vo.getPassword(), true, true, true, vo.getStatus().equals(LogicEnum.YES.getCode())?true:false, this.obtainGrantedAuthorities(vo));
        user.setUserId(vo.getId());
        return user;
    }

    /**
     * 获得用户的所有角色id.
     * @param userDetailsVO
     * @return
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(UserDetailsVO userDetailsVO) {
        List<Long> roleIds = userDetailsVO.getRoleIds();
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptySet();
        }
        return roleIds.stream().map(roleId -> new SimpleGrantedAuthority(String.valueOf(roleId))).collect(Collectors.toSet());
    }

}
