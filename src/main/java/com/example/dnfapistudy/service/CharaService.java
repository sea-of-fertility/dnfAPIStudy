package com.example.dnfapistudy.service;

import com.example.dnfapistudy.api.CharaDetail;
import com.example.dnfapistudy.domain.Character;
import com.example.dnfapistudy.exception.CharaNotFoundException;
import com.example.dnfapistudy.reposiotry.CharacterRepository;
import com.example.dnfapistudy.request.character.FindCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<Character> save(List<Character> characters){
        for (Character character : characters) {
            if (characterRepository.findByCharacterId(character.getCharacterId()).isEmpty()) {
                characterRepository.save(character);
            }
        }
        return characters;
    }


    /**
     *
     * @param findCharacter
     * @return 검색된 모든 캐릭어의 고유한 id값을 []형태로 받아온다..
     */
    public CharaDetail findCharacterId(FindCharacter findCharacter) {
        return restTemplate.getForObject(
                String.format("https://api.neople.co.kr/df/servers/%s/characters?characterName=%s&apikey=%s",
                        findCharacter.getServerId(), findCharacter.getName(), apiKey),
                CharaDetail.class);
    }

    /**
     * @param charaDetail
     * @return
     * @throws IOException
     */
    public List<Character> setImage(CharaDetail charaDetail) throws IOException {
        CharaDetail.CharacterInform[] rows = charaDetail.getRows();
        List<Character> characters = new ArrayList<>();
        for (CharaDetail.CharacterInform row : rows) {
            byte[] forObject = restTemplate.getForObject(String.format(imageUrl, row.getServerId(), row.getCharacterId(), imageSize), byte[].class);
            assert forObject != null;
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(forObject));
            String imagePath = fileSystemStorageService.store(img, row.getCharacterId()).toString();
            characters.add(row.toEntity(imagePath));
        }
        return characters;
    }

    public List<Character> basicInform(FindCharacter findCharacter) throws Throwable {
        return characterRepository
                .findByCharacterName(findCharacter.getName())
                .orElseThrow(CharaNotFoundException::new);
    }


}
