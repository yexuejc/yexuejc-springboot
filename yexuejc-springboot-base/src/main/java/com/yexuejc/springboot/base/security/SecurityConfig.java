package com.yexuejc.springboot.base.security;

import com.yexuejc.springboot.base.security.inte.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Security Web 配置
 *
 * @ClassName: SecurityConfig
 * @Description:Security Web 配置
 * @author: maxf
 * @date: 2017年11月22日 下午4:40:22
 */
//@EnableWebSecurity(debug = false)
public abstract class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    /**
     * 查询数据库DB=>获取用户数据loadUserByUsername()
     *
     * @return
     */
    protected abstract UserService getUserService();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = createUserDetailsManager();
        AuthenticationProvider authenticationProvider = createConsumerAuthenticationProvider(userDetailsService);
        auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 初始化 AuthenticationProvider
     *
     * @param userDetailsService
     * @return
     */
    protected AuthenticationProvider createConsumerAuthenticationProvider(UserDetailsService userDetailsService) {
        return new ConsumerAuthenticationProvider(userDetailsService, getUserService());
    }

    /**
     * 初始化 UserDetailsService
     *
     * @return
     */
    protected UserDetailsService createUserDetailsManager() {
        return new UserDetailsManager(getUserService());
    }


    @Bean
    public ConsumerAuthenticationProcessingFilter consumerAuthenticationProcessingFilter(
            AuthenticationManager authenticationManager) throws Exception {
        ConsumerAuthenticationProcessingFilter filter = createConsumerAuthenticationProcessingFilter(authenticationManager);
        filter.setAuthenticationManager(this.authenticationManager());
        loginHodler(filter);
        return filter;
    }

    /**
     * 初始化 ConsumerAuthenticationProcessingFilter
     *
     * @param authenticationManager
     * @return
     */
    protected ConsumerAuthenticationProcessingFilter createConsumerAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        return new ConsumerAuthenticationProcessingFilter(authenticationManager);
    }

    /**
     * <pre>
     * 处理登录
     * 成功: filter.setAuthenticationSuccessHandler()
     * 失败: filter.setAuthenticationFailureHandler()
     * </pre>
     *
     * @param filter
     */
    protected abstract void loginHodler(ConsumerAuthenticationProcessingFilter filter);


    @Bean
    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/login");
        return loginUrlAuthenticationEntryPoint;
    }

    /**
     * 解决跨域问题
     * <pre>
     *      http.csrf().disable()
     *          .cors()
     *          .and()
     * </pre>
     * 参考 https://www.jianshu.com/p/87e1ef68794c -> https://github.com/gothinkster/spring-boot-realworld-example-app
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        //指定允许跨域的请求(*所有)：http://wap.guansichou.com
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode
        // is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "X-User-Agent", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * 关键.cors()
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors()
                .and().servletApi().disable()
                .requestCache().disable()
                .securityContext().securityContextRepository(createConsumerSecurityContextRepository())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterAt(consumerAuthenticationProcessingFilter(super.authenticationManager()),
                UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 创建 SecurityContextRepository
     *
     * @return
     */
    protected SecurityContextRepository createConsumerSecurityContextRepository() {
        return new ConsumerSecurityContextRepository(getRedisDB());
    }

    /**
     * 初始化放置用户信息的reids库
     *
     * @return
     */
    protected abstract RedisTemplate<Object, Object> getRedisDB();


    @Override
    public void configure(WebSecurity web) throws Exception {
        // 不需要经过SpringSecurity的过滤器的URLs
        // web.ignoring();
    }

}
