package com.mavendemo.service.impl;

import com.mavendemo.mapper.UserMapper;
import com.mavendemo.model.User;
import com.mavendemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;


@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer saveUser(Integer id, String name) {
        try {
            User user = new User();
            user.setId(id);
            user.setName(name);
            System.out.println(id+"--------------"+name);
            Integer num = userMapper.save(user);
            System.out.println("UserServiceImpl userMapper.save(user)=="+num);
            return num;
        }catch (Exception e){
            logger.error("保存用户失败",e);
        }
        return null;
    }

    @Override
    public User findUser(Integer id) {
        try {
            return userMapper.findById(id);
        }catch (Exception e){
            logger.error("查询用户失败",e);
        }
        return null;
    }

    @Override
    public Integer deleteUser(Integer delId) {
        try {
//            resultNum = userMapper.delById(delId);
            Integer resultNum = userMapper.delById(delId);
            System.out.println("UserServiceImpl userMapper.delById(delId)=="+resultNum);
            return resultNum;
        }catch (Exception e){
            logger.error("删除用户失败",e);
        }
        return null;
    }

    @Override
    public Integer updateUser(User user) {
        try {
            Integer num = userMapper.updateUser(user);
            System.out.println("UserServiceImpl userMapper.updateUser(user)=="+num);
            return num;
        }catch (Exception e){
            logger.error("保存修改失败",e);
        }
        return null;
    }
}
