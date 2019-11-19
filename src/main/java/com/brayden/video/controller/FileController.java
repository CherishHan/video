package com.brayden.video.controller;

import com.brayden.video.entity.FileInfo;
import com.brayden.video.service.interfaces.FileService;
import com.brayden.video.util.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseData upload(MultipartFile file){
        long size = file.getSize();
        String filename = file.getOriginalFilename();
        logger.info("size : {}, filename : {}", size, filename);
        FileOutputStream os = null;
        String url = "E:\\temp" + File.separator + filename;
        try {
            os = new FileOutputStream(url);
            os.write(file.getBytes());
        } catch (IOException e) {
            logger.error("file not found");
            return new ResponseData(false, "上传失败");
        }finally {
            try {
                if(os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setUserId(2);
        fileInfo.setFilename(filename);
        fileInfo.setPath(url);
        fileInfo.setCreatedTime(LocalDateTime.now());
        fileService.addFile(fileInfo);
        return new ResponseData(true, "上传成功");
    }
}
