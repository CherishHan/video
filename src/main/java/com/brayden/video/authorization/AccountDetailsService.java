package com.brayden.video.authorization;

import com.brayden.video.common.VideoException;
import com.brayden.video.entity.Account;
import com.brayden.video.entity.User;
import com.brayden.video.mapper.AccountMapper;
import com.brayden.video.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static java.lang.String.format;

@Service
public class AccountDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AccountDetailsService.class);

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Objects.requireNonNull(name, "name must be not null!");
        Account account = accountMapper.getAccountByName(name);
        if(StringUtils.isEmpty(account)){
            throw new UsernameNotFoundException(format("用户%s没有找到！", name));
        }
        LoginDetail login = new LoginDetail();
        login.setAccount(account);
        logger.info("通过用户名加载用户");
        return login;
    }
}
