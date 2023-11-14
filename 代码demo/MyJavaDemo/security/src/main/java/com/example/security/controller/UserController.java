package com.example.security.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.security.entity.User;
import com.example.security.entity.UserVO;
import com.example.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-03-20
 */
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/find")
    public   List<User>  finduser(){

        List<User> users =userMapper.selectList(null);
        return users;
    }

    @RequestMapping("/user/u")
    public Page selectpageuser() {

        Page<UserVO> list = userMapper.selectUserPage(new Page<>(1, 1), "root");
        for (UserVO u : list.getRecords()
        ) {
            System.out.println(u);

        }

        return  list;
    }
}
