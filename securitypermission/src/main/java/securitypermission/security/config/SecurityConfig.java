package securitypermission.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import securitypermission.security.access.AccessDeniedHandler;
import securitypermission.security.access.TokenExceptionHandler;
import securitypermission.security.jwt.filter.JwtTokenFilter;

import javax.annotation.Resource;
 
/**
 * security配置
 * @Author WQY
 * @Date 2019/11/26 11:12
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //token异常返回
    @Resource
    private TokenExceptionHandler tokenExceptionHandler;
    //没权限放回
    @Resource
    private AccessDeniedHandler accessDeniedHandler;
    @Resource
    private JwtTokenFilter jwtTokenFilter;
 
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 因为我们的token是无状态的，不需要跨站保护
                .csrf().disable()
                // 添加异常处理，以及访问禁止（无权限）处理
                .exceptionHandling().authenticationEntryPoint(tokenExceptionHandler).accessDeniedHandler(accessDeniedHandler).and()
 
                // 我们不再需要session了
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
 
                //定义拦截页面，所有api全部需要认证
                .authorizeRequests()
 
                .anyRequest().authenticated();
 
        //最后，我们定义 filter，用来替换原来的UsernamePasswordAuthenticationFilter
        httpSecurity.addFilterAt(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
 
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                // 让我们获取 token的api不走springsecurity的过滤器，大道开放
                .antMatchers(HttpMethod.GET,
                        "/token",
                        "/webjars/**",
                        "/resources/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs"
                        )
        .antMatchers(HttpMethod.POST,
                "/token");
    }
}
//@Slf4j
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
//        http
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")//处理登录请求接口
//                .successForwardUrl("/hello")
//                .defaultSuccessUrl("/hello",true)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/index","/login","/error").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf()
//                .disable();;
//
////        http.authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
////                .antMatchers("/login","/error","/index").permitAll()// 设置所有人都可以访问登录页面
////                .anyRequest().authenticated()  // 任何请求,登录后可以访问
////                .and()
////                .formLogin().loginPage("/login");
////        ;
//    }
//}