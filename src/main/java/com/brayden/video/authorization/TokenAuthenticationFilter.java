package com.brayden.video.authorization;

import com.brayden.video.util.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    public TokenAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)) {
           throw new AuthenticationCredentialsNotFoundException("token is null");
        }
        logger.info("token : {}", token);
        Jws<Claims> claimsJws = JwtTokenUtils.decode(token);
        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();
        logger.info("header : {}, body : {}", header, body);
        LoginDetail login = new LoginDetail();
        login.setAuthenticated(true);
        return login;
    }
}
