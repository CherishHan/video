package com.brayden.video.controller;

import com.brayden.video.util.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/upload")
    public ResponseData upload(MultipartFile file){
        long size = file.getSize();
        logger.info("size : {}", size);
        return new ResponseData(true, "上传成功");
    }
}
