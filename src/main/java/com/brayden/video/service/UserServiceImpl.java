package com.brayden.video.service;

import com.brayden.video.entity.Account;
import com.brayden.video.entity.User;
import com.brayden.video.mapper.AccountMapper;
import com.brayden.video.mapper.UserMapper;
import com.brayden.video.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User getUserById(int id){
        return userMapper.getUserById(id);
    }

    @Override
    public void addAccount(Account account) {
        accountMapper.addAccount(account);
    }

    @Override
    public Account getAccountByName(String name) {
        return accountMapper.getAccountByName(name);
    }
}
