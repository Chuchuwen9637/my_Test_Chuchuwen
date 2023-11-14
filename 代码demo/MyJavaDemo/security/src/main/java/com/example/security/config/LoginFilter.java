package com.example.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

/*自定义前后端分离验证fillter*/
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //是否为post
        if (request.getMethod().equals("GET")){
            throw  new AuthenticationServiceException("错误"+request.getMethod());
        }
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)){
            //从json获取用户输入的账户密码
            try {
                Map<String,String> userInfo=    new ObjectMapper().readValue(request.getInputStream(), Map.class);
                String username=userInfo.get(getUsernameParameter());
                String passowrd=userInfo.get(getPasswordParameter());
                System.out.println("用户名"+username+"密码"+passowrd );
                UsernamePasswordAuthenticationToken autoRequest = new UsernamePasswordAuthenticationToken(username,passowrd);
                setDetails(request,autoRequest);
                return this.getAuthenticationManager().authenticate(autoRequest);
            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
        //是否为json
        //从json获取用户输入的账户密码
        return super.attemptAuthentication(request, response);
    }
}
