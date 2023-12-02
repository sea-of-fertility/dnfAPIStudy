package com.example.dnfapistudy.service;

import jakarta.annotation.Resource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Path;

@SpringBootTest
@AutoConfigureMockMvc
class FileSystemStorageServiceTest {

    @Autowired
    FileSystemStorageService  fileSystemStorageService;

    @Test
    public void file_upload() throws Exception {
        //given
        // 테스트용 이미지 파일 생성
        MockMultipartFile file = new MockMultipartFile(
                "file", // 파일 파라미터 이름
                "test-image.jpg", // 파일 이름
                "image/jpeg", // 파일 형식
                "some image data".getBytes() // 파일 데이터
        );

        fileSystemStorageService.store(file);
        //when

        //then
        Resource file1 = fileSystemStorageService.loadAsResource("file");
        System.out.println(file1);

//        Assertions.assertThat(file.getName()).isEqualTo(load.toString());
    }



}