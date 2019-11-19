package com.brayden.video.controller;

import com.brayden.video.entity.FileInfo;
import com.brayden.video.service.interfaces.FileService;
import com.brayden.video.util.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;

@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ResponseBody
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

    @GetMapping("/download")
    public void download(HttpServletResponse response)  {
        File file = new File("E:\\temp\\wps.mp4");
        OutputStream os = null;
        BufferedInputStream br = null;
        try {
            br = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int len = 0;
            response.setHeader("Content-Disposition", "attachment; filename=" + "wps.MP4");
            response.setContentType("application/octet-stream");
            os = response.getOutputStream();
            while((len = br.read(buffer)) > 0){
                os.write(buffer, 0, len);
            }
            logger.info("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       // return new ResponseData(true, "下载成功");
    }
}
