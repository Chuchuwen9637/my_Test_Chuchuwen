package com.example.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.entity.UserVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-03-20
 */
public interface UserMapper extends BaseMapper<User> {
   User  loadUserByUsername(String username);
   List<Role> getRolesByuid(Integer  uid);
   Page<UserVO> selectUserPage(Page<UserVO> page, String username);
}
