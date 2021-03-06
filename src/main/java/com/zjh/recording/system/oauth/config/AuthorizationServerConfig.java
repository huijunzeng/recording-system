package com.zjh.recording.system.oauth.config;

import com.zjh.recording.system.oauth.exception.CustomWebResponseExceptionTranslator;
import com.zjh.recording.system.oauth.oauth2.MyUserDetailsService;
import com.zjh.recording.system.oauth.oauth2.SmsCodeUserDetailsService;
import com.zjh.recording.system.oauth.oauth2.granter.PhoneCustomTokenGranter;
import com.zjh.recording.system.oauth.oauth2.jwt.CustomJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AuthorizationServerConfig?????????????????????????????????????????????????????????clients???endpoints???security?????????
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "recording-system";

    @Value("${spring.security.oauth2.jwt.signing-key:123456}")
    private String SIGNING_KEY;

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private SmsCodeUserDetailsService smsCodeUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * ?????????OAuth2????????????
     *
     * @return CustomWebResponseExceptionTranslator
     */
    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> customExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

    /**
     * ?????????????????????  clientId scope redirect_uri???  ????????????????????????????????????????????????oauth_client_details
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // ???????????? http://localhost:9701/oauth/token?scope=read&grant_type=password  ??????Authorization??????Basic Auth???Username??????????????????oauth_client_details???client_id??????Password??????client_secret????????????????????????headers??????Authorization???????????????????????? Basic + ??????Base64UrlEncode(clientId:clientSecret)?????????????????????Basic BCRoZEp8X3dlYmoxMjM0NTY= ??????Body??????x-www-form-urlencoded????????????username??????password???
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    /**
     * ???????????????????????????????????????????????????????????????  ??????token?????????
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> tokenGranters = getTokenGranters(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
        tokenGranters.add(endpoints.getTokenGranter());
        endpoints
                .userDetailsService(myUserDetailsService)
                .authorizationCodeServices(authorizationCodeServices())
                .tokenEnhancer(tokenEnhancerChain())
                .tokenServices(tokenServices())
                .authenticationManager(authenticationManager)
                .tokenGranter(new CompositeTokenGranter(tokenGranters))
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .exceptionTranslator(customExceptionTranslator());
    }

    /**
     * ????????????????????????????????????  ?????????????????????????????????
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * ?????????????????????????????????code
     *
     * @return JdbcAuthorizationCodeServices
     */
    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * jwt  token???????????????
     * @return jwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);
        return jwtAccessTokenConverter;
    }

    /**
     * @return tokenEnhancerChain
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomJwtToken(), accessTokenConverter()));
        return tokenEnhancerChain;
    }

    /**
     * @return defaultTokenServices
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true); // ????????????refreshToken
        defaultTokenServices.setSupportRefreshToken(false); // refreshToken????????????????????? ??????true???????????? false, ???????????????????????????????????? refresh_token, ???????????? refresh_token
        //defaultTokenServices.setAccessTokenValiditySeconds(); // refresh_token ??????????????? (???), ?????? 30 ???
        //defaultTokenServices.setRefreshTokenValiditySeconds(); // access_token ??????????????? (???), ?????? 12 ??????
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain());
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    private List<TokenGranter> getTokenGranters(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        return new ArrayList<>(Arrays.asList(
                new PhoneCustomTokenGranter(tokenServices, clientDetailsService, requestFactory, smsCodeUserDetailsService)
        ));
    }


}

