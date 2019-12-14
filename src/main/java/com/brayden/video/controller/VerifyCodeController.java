package com.brayden.video.controller;

import com.brayden.video.util.VerifyCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

@Controller
@RequestMapping("/verify")
public class VerifyCodeController {

    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);

    private static Random random = new Random();

    @ResponseBody
    @GetMapping("/getImg")
    public void getImg(HttpServletResponse response, HttpServletRequest request) throws IOException {
        HashMap<String,Object> hashMap= VerifyCodeUtil.createImg(6,3,300,500,18, Color.white,"/static/system/login/img/test.png");
        request.getSession().setAttribute("correctCheckCode",hashMap.get("correctCheckCode"));
        ImageIO.write((BufferedImage)hashMap.get("BufferedImage"), "PNG", response.getOutputStream()); //将图片输出
    }

//    @ResponseBody
//    @PostMapping("/checkImg")
//    public JsonResult checkImg(HttpServletRequest request,String imgCheckInfo) {
//        String correctCheckCode=(String)request.getSession().getAttribute("correctCheckCode");
//        if (TouchVerificationUtil.checkImg(correctCheckCode,imgCheckInfo)) {  //若前端上传的坐标在session中记录的坐标的一定范围内则验证成功
//            return new JsonResult("200","验证成功！");
//        } else {
//            return new JsonResult("200","验证失败，请重新刷新再次验证！");
//        }
//    }
}
