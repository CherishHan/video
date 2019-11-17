package com.brayden.video.service.interfaces;

import com.brayden.video.entity.Account;
import com.brayden.video.entity.User;

public interface UserService {

    public void addUser(User user);

    public User getUserById(int id);

    public void addAccount(Account account);

    public Account getAccountByName(String name);
}
