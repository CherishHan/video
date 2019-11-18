package com.brayden.video;

import com.brayden.video.util.JwtTokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class VideoApplicationTests {

    @Test
    void contextLoads() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMzk3MTI2OTE3OSIsInJvbGUiOm51bGwsImlzcyI6IkdlbnQuTmkiLCJleHAiOjE1NzQwMTQ2MDMsImlhdCI6MTU3NDAxMTAwM30.VD7F2hAR4zV1tyxrgxT0eBfZkTeATAytke4z9qqJRiw";
        JwtTokenUtils.decode(token);
    }

}
