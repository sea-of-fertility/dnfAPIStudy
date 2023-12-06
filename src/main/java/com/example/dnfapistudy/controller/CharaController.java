package com.example.dnfapistudy.controller;


import com.example.dnfapistudy.api.CharaBasicInform;
import com.example.dnfapistudy.api.CharaBasicInform.CharacterInform;
import com.example.dnfapistudy.domain.Character;
import com.example.dnfapistudy.request.FindCharacter;
import com.example.dnfapistudy.service.CharaService;
import com.example.dnfapistudy.service.FileSystemStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;


@RestController()
@RequiredArgsConstructor
@Slf4j
public class CharaController {

    private final RestTemplate restTemplate;
    private final FileSystemStorageService fileSystemStorageService;
    private final CharaService charaService;

    @Value("${api-key}")
    private String apiKey;
    private final int imageSize = 1;
    private final String imageUrl = "https://img-api.neople.co.kr/df/servers/%s/characters/%s?zoom=%s";
    private final String charaSearch = "https://api.neople.co.kr/df/servers/%s/characters?characterName=%s&jobId=%s&jobGrowId=%s&isAllJobGrow=%s&limit=<limit>&wordType=match&apikey=%s";


    /**
     *
     * @param findCharacter 서버와 캐릭터 이름을 가지고 있다.
     * @return 캐릭터 이미지와 이름 명성 레벨을 반환 한다.
     * @throws IOException
     */
    @GetMapping("/character")
    public void chara(@RequestBody FindCharacter findCharacter) throws IOException {
        CharaBasicInform charaBasicInform = findCharacterId(findCharacter);
        setImage(charaBasicInform);
//        return charaBasicInform;
    }

    /**
     *
     * @param findCharacter
     * @return 검색된 모든 캐릭어의 고유한 id값을 []형태로 받아온다..
     */
    private CharaBasicInform findCharacterId(FindCharacter findCharacter) {
        return restTemplate.getForObject(
                String.format("https://api.neople.co.kr/df/servers/%s/characters?characterName=%s&apikey=%s",
                        findCharacter.getServerId(), findCharacter.getName(), apiKey),
                CharaBasicInform.class);
    }


    /**
     *
     * @param charaBasicInform
     * @throws IOException
     */
    private void setImage(CharaBasicInform charaBasicInform) throws IOException {
        CharacterInform[] rows = charaBasicInform.getRows();
        for (CharacterInform row : rows) {
            byte[] forObject = restTemplate.getForObject(String.format(imageUrl, row.getServerId(), row.getCharacterId(), imageSize), byte[].class);
            assert forObject != null;
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(forObject));
            String store = fileSystemStorageService.store(img, row.getCharacterId()).toString();
            Character entity = row.toEntity(store);
            charaService.save(entity);
        }
    }


}