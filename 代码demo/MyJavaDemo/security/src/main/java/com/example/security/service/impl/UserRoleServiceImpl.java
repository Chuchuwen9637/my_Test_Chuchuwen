package com.example.security.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.entity.UserRole;
import com.example.security.mapper.UserRoleMapper;
import com.example.security.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
