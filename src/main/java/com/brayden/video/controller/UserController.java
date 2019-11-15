package com.brayden.video.controller;

import com.brayden.video.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user")
    public User login(@RequestBody User user){
        logger.info("name : {}, password : {}", user.getName(), user.getPassword());
        return user;
    }
}
