package com.example.security.config;

import com.example.security.service.impl.MyUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter  {
    private  final MyUserDetailService myUserDetailService;
    @Autowired
    public SecurityConfig(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("root").password("{bcrypt}$2a$10$vsy09RCsIYNKXfX9z71AfeO7suOF14Yreg1uoijSdrIK8ThxQtlra").roles("admin").build());

        return inMemoryUserDetailsManager;
    }
   /* @Bean
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());

    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
        //注入userdetails
       // auth.userDetailsService(myUserDetailService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter=new LoginFilter();
        loginFilter.setFilterProcessesUrl("/doLogin");
        loginFilter.setUsernameParameter("uname");
        loginFilter.setPasswordParameter("passwd");
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            Map<String,Object> result=new HashMap<String,Object>();
            result.put("msg","登陆成功");
            result.put("status",200);
            //result.put("用户名",(com.example.security.entity.User)(a))
            result.put("用户名",authentication.getPrincipal());
            response.setContentType("application/json;charset=UTF-8");
            String s=new ObjectMapper().writeValueAsString(result);
            response.getWriter().print(s);
        });
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            Map<String,Object> result=new HashMap<String,Object>();
            result.put("msg","登陆失败"+exception.getMessage());
            result.put("status",401);
            //result.put("用户名",(com.example.security.entity.User)(a))
           // result.put("用户名",authentication.getPrincipal());
            response.setContentType("application/json;charset=UTF-8");
            String s=new ObjectMapper().writeValueAsString(result);
            response.getWriter().print(s);
        });
        return loginFilter;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().mvcMatchers("/user/**").permitAll();
        http.authorizeRequests().anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, exception) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().println("尚未认证");
                })
                .and()
                .logout()
                .logoutUrl("/loginout")
                .logoutSuccessHandler((res,reso,auth)->{
                    Map<String,Object> result=new HashMap<String,Object>();
                    result.put("msg","注销成功");
                    result.put("status",200);
                    //result.put("用户名",(com.example.security.entity.User)(a))
                   // result.put("用户名",auth.getPrincipal());
                    reso.setContentType("application/json;charset=UTF-8");
                    String s=new ObjectMapper().writeValueAsString(result);
                    reso.getWriter().print(s);
                })
                .and().csrf().disable();//所有请求都得认证
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
