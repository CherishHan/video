package com.brayden.video.service;

import com.brayden.video.entity.FileInfo;
import com.brayden.video.mapper.FileMapper;
import com.brayden.video.service.interfaces.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public void addFile(FileInfo fileInfo) {
        fileMapper.addFile(fileInfo);
    }
}
