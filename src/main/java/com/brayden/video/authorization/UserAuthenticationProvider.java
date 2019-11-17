package com.brayden.video.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider extends DaoAuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountDetailsService userDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if(!(userDetails instanceof LoginDetail)){
            throw new InsufficientAuthenticationException("身份认证失败");
        }
        super.additionalAuthenticationChecks(userDetails, authentication);
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        logger.info("通过身份认证前检查。。。");
        if(!(user instanceof LoginDetail)){
            throw new InsufficientAuthenticationException("身份认证失败");
        }
        LoginDetail login = (LoginDetail) user;
        login.setAuthenticated(true);
        return login;
    }

    @Override
    protected void doAfterPropertiesSet() {
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);
        super.doAfterPropertiesSet();
    }
}
