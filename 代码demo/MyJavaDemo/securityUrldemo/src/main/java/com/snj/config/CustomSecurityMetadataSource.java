package com.snj.config;

import com.snj.entity.Menu;
import com.snj.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Configuration
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private final MenuService menuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    public CustomSecurityMetadataSource(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        //获取请求的Url地址
        String requestURI = ((FilterInvocation) object).getRequest().getRequestURI();
        //数据库查询菜单权限地址
        List<Menu> allMenu = menuService.getAllMenu();
        System.out.println(allMenu);
        for (Menu menu : allMenu) {
            if (antPathMatcher.match(menu.getPattern(), requestURI)) {
                String[] roles = menu.getRoles().stream().map(r -> r.getName()).toArray(String[]::new);

                for (String s:roles
                     ) {
                    System.out.println(s);
                }
                return SecurityConfig.createList(roles);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
