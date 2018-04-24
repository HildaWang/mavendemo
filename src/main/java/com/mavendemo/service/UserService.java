package com.mavendemo.service;

import com.mavendemo.model.User;

public interface UserService {
    public Integer saveUser(Integer id,String name);
    public User findUser(Integer id);
    public Integer deleteUser(Integer id);
    public Integer updateUser(User user);
}
