package com.mavendemo.mapper;

import com.mavendemo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public Integer save(User user);
    public User findById (Integer id);
    public Integer delById(Integer id);
    public Integer updateUser(User user);
}
