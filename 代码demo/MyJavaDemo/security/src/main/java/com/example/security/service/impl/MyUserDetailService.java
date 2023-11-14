package com.example.security.service.impl;

import com.example.security.entity.User;
import com.example.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class MyUserDetailService implements UserDetailsService {

    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User  user =userMapper.loadUserByUsername(username);
        if (ObjectUtils.isEmpty(user) ) throw  new RuntimeException("账户不存在");
            user.setRoleList(userMapper.getRolesByuid(user.getId()));
        return user;
    }
}
