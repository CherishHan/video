package com.brayden.video.controller;

import com.brayden.video.entity.User;
import com.brayden.video.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User login(@RequestBody User user){
        logger.info("name : {}, password : {}", user.getName(), user.getPassword());
        return user;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        logger.info("name : {}, password : {}, email : {}", user.getName(), user.getPassword(), user.getEmail());
        userService.addUser(user);
        return user;
    }
}
