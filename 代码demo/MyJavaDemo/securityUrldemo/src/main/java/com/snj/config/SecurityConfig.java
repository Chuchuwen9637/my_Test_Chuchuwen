package com.snj.config;

import com.snj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomSecurityMetadataSource customSecurityMetadataSource;
    private final UserService userService;

    @Autowired
    public SecurityConfig(CustomSecurityMetadataSource customSecurityMetadataSource, UserService userService) {
        this.customSecurityMetadataSource = customSecurityMetadataSource;
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        http.apply(new UrlAuthorizationConfigurer<>(applicationContext))
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(customSecurityMetadataSource);
                        object.setRejectPublicInvocations(true);
                        return object;
                    }
                });
        http.formLogin()
                .and()
                .csrf().disable();
    }
}
