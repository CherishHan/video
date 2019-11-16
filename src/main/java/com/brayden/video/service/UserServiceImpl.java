package com.brayden.video.service;

import com.brayden.video.entity.Account;
import com.brayden.video.entity.User;
import com.brayden.video.mapper.UserMapper;
import com.brayden.video.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void addAccount(Account account) {
        userMapper.addAccount(account);
    }

    @Override
    public Account getAccountByName(String name) {
        return userMapper.getAccountByName(name);
    }
}
