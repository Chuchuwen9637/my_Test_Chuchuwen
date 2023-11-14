package com.snj.mapper;

import com.snj.entity.Role;
import com.snj.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<Role> getUserRoleByUid(Integer uid);
    User loadUserByUsername(String username);
}