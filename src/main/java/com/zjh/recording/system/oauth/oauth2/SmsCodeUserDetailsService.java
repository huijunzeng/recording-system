package com.zjh.recording.system.oauth.oauth2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义用户细节实现类
 * @Author: ZJH
 * @Date: 2019/6/27 13:17
 */

@Component
public class SmsCodeUserDetailsService {

    /*@Autowired
    private UserService userService;*/
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 查出UserDetails并比较
     * 根据提供的密码加密器passwordEncoder（默认是）去比较当前请求的密码与数据库查出来的密码比较，不匹配则报错：
     * {
     *     "error": "invalid_grant",
     *     "error_description": "Bad credentials"
     * }
     * 自定义用户细节实现类 根据手机号查询用户
     * @param phone
     * @param smsCode
     * @return
     */
    public UserDetails loadUserByPhone(String phone, String smsCode) {
        // 内存的方式
        /*InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
        return (UserDetails) manager;
        */
        //查询账号是否存在，是就返回一个UserDetails的对象，若不存在就抛出异常！
        Set<GrantedAuthority> authoritiesSet = new HashSet<GrantedAuthority>();
        // 授权权限
        authoritiesSet.add(new SimpleGrantedAuthority("USER"));
        return new User("admin", new BCryptPasswordEncoder().encode("password"), true, true, true, true,authoritiesSet);
        // 数据库的方式
        // 从数据库验证用户密码 查询用户权限  测试账号 用户名：admin  密码：password
       /* UserEntity userEntity = userService.getByUniqueId(phone);
        System.out.println(userEntity.toString());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));// 授权权限
        *//*if (userEntity != null) {
            List<TbPermission> tbPermissions = tbPermissionMapper.selectByUserId(tbUser.getId());

            tbPermissions.stream().forEach(tbPermission -> {
                if (tbPermission != null && tbPermission.getEnname() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
                    grantedAuthorities.add(grantedAuthority);
                }
            });
        }*//*
        // （1）假如WebSecurityConfig中的AuthenticationManagerBuilder配置了passwordEncoder，但在数据库中保存的密码不是明文的而是已经用相同的passwordEncoder加密后的密文，那么封装查询出来的用户User的密码时就不需要再用passwordEncoder加密
        // （2）假如WebSecurityConfig中的AuthenticationManagerBuilder配置了passwordEncoder，但在数据库中保存的密码是明文，那么封装查询出来的用户User的密码时就需要再用相同的passwordEncoder加密
        //return new User(userEntity.getUsername(), passwordEncoder.encode(userEntity.getPassword()), true, true, true, true, grantedAuthorities);
        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true, grantedAuthorities);*/

    }

}
