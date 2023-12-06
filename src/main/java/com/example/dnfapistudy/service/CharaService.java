package com.example.dnfapistudy.service;

import com.example.dnfapistudy.api.CharaBasicInform;
import com.example.dnfapistudy.domain.Character;
import com.example.dnfapistudy.reposiotry.CharacterRepository;
import com.example.dnfapistudy.request.FindCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CharaService {


    private final FileSystemStorageService fileSystemStorageService;
    private final RestTemplate restTemplate;
    private final CharacterRepository characterRepository;

    @Value("${api-key}")
    private String apiKey;
    private final int imageSize = 1;
    private final String imageUrl = "https://img-api.neople.co.kr/df/servers/%s/characters/%s?zoom=%s";
    private final String charaSearch = "https://api.neople.co.kr/df/servers/%s/characters?characterName=%s&jobId=%s&jobGrowId=%s&isAllJobGrow=%s&limit=<limit>&wordType=match&apikey=%s";

    public void save(Character character){
        characterRepository.save(character);
    }


    /**
     *
     * @param findCharacter
     * @return 검색된 모든 캐릭어의 고유한 id값을 []형태로 받아온다..
     */
    public CharaBasicInform findCharacterId(FindCharacter findCharacter) {
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
    public void setImageAndSave(CharaBasicInform charaBasicInform) throws IOException {
        CharaBasicInform.CharacterInform[] rows = charaBasicInform.getRows();
        for (CharaBasicInform.CharacterInform row : rows) {
            byte[] forObject = restTemplate.getForObject(String.format(imageUrl, row.getServerId(), row.getCharacterId(), imageSize), byte[].class);
            assert forObject != null;
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(forObject));
            String imagePath = fileSystemStorageService.store(img, row.getCharacterId()).toString();
            Character entity = row.toEntity(imagePath);
            save(entity);
        }
    }

}
