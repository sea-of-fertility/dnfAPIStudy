package com.example.dnfapistudy.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

@SpringBootTest
@AutoConfigureMockMvc
class FileSystemStorageServiceTest {

    @Autowired
    FileSystemStorageService  fileSystemStorageService;

    @Test
    public void file_upload() throws Exception {
        //given

        //when


        //then
    }
}