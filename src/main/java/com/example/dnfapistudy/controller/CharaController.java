package com.example.dnfapistudy.controller;


import com.example.dnfapistudy.api.CharacterInforms;
import com.example.dnfapistudy.api.CharacterInforms.CharacterInform;
import com.example.dnfapistudy.request.CharaInform;
import com.example.dnfapistudy.service.FileSystemStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;


@RestController()
@RequiredArgsConstructor
@Slf4j
public class CharaController {

    private final RestTemplate restTemplate;
    private final FileSystemStorageService fileSystemStorageService;


    @Value("${api-key}")
    private String apiKey;
    private final int imageSize = 1;
    private final String imageUrl = "https://img-api.neople.co.kr/df/servers/%s/characters/%s?zoom=%s";
    private final String charaSearch = "https://api.neople.co.kr/df/servers/%s/characters?characterName=%s&jobId=%s&jobGrowId=%s&isAllJobGrow=%s&limit=<limit>&wordType=match&apikey=%s";

    @GetMapping("/character")
    public CharacterInforms chara(@RequestBody CharaInform charaInform) throws IOException {
        CharacterInforms characterInforms = findCharacterId(charaInform);
        charaImage(characterInforms);
        return characterInforms;
    }

    private CharacterInforms findCharacterId(CharaInform charaInform) {
        return restTemplate.getForObject(
                String.format("https://api.neople.co.kr/df/servers/%s/characters?characterName=%s&apikey=%s",
                        charaInform.getServerId(), charaInform.getName(), apiKey),
                CharacterInforms.class);
    }

    private void charaImage(CharacterInforms characterInforms) throws IOException {
        CharacterInform[] rows = characterInforms.getRows();
        for (CharacterInform row : rows) {
            byte[] forObject = restTemplate.getForObject(String.format(imageUrl, row.getServerId(), row.getCharacterId(), imageSize), byte[].class);
            assert forObject != null;
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(forObject));
            fileSystemStorageService.store(img, row.getCharacterId());
        }
    }
}