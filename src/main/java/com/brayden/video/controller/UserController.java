package com.brayden.video.controller;

import com.brayden.video.entity.Account;
import com.brayden.video.entity.User;
import com.brayden.video.service.interfaces.UserService;
import com.brayden.video.util.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    @Transactional
    public ResponseData create(@RequestBody User user){
        String telephone = user.getAccount().getName();
        Account a = userService.getAccountByName(telephone);
        if(!StringUtils.isEmpty(a)){
            return new ResponseData(false, "此账号已注册!");
        }
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedTime(now);
        userService.addUser(user);
        Account account = new Account();
        account.setUserId(user.getId());
        account.setName(telephone);
        account.setCredentials(encoder.encode(user.getAccount().getCredentials()));
        account.setCreatedTime(now);
        userService.addAccount(account);
        return new ResponseData("创建用户成功");
    }

    @PostMapping("/login")
    public ResponseData login(@RequestBody Account account){
        logger.info("name : {}, pass : {}", account.getName(), account.getCredentials());
        Account ac = userService.getAccountByName(account.getName());
        if(StringUtils.isEmpty(ac)){
            return new ResponseData(false, "此账号不存在！");
        }
        if(!encoder.matches(account.getCredentials(), ac.getCredentials())){
            return new ResponseData(false, "账号或密码错误！");
        }
        return new ResponseData(true, "登录成功！");
    }

}
