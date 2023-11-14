package com.example.security.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.entity.User;
import com.example.security.mapper.UserMapper;
import com.example.security.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
