package com.example.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.mapper.RoleMapper;
import com.example.security.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-03-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public User loadUserByUsername(String username) {
        return null;
    }
}