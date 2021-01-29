package org.example.service;

import org.example.mapper.UserMapper;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-29;   Time: 17:11
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User query(String username) {
        return userMapper.query(username);
    }
}
