package com.example.dnfapistudy.service;

import com.example.dnfapistudy.api.CharaDetail;
import com.example.dnfapistudy.domain.Character;
import com.example.dnfapistudy.reposiotry.CharacterRepository;
import com.example.dnfapistudy.request.character.FindCharacter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
class CharaServiceTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CharaService charaService;
    @Autowired
    CharacterRepository characterRepository;

    @Test
    @DisplayName("찾은 캐릭터의 이름이 맞는가?")
    public void charaBasicInform() throws Exception {

        //given
        FindCharacter build = FindCharacter.builder().name("유키")
                .serverId("all")
                .build();
        //when
        CharaDetail characters = charaService.findCharacterId(build);
        //then


        Arrays.stream(characters.getRows()).forEach(i -> System.out.println(i.getCharacterId()));
        Assertions.assertThat(Arrays.stream(characters.getRows()).allMatch(i -> i.getCharacterName().equals("유키"))).isEqualTo(true);
    }


    @Test
    @DisplayName("모든 entity에 경로가 들어갔는가")
    public void setImage() throws Exception {
        //given
        FindCharacter build = FindCharacter.builder().name("유키")
                .serverId("all")
                .build();
        CharaDetail characters = charaService.findCharacterId(build);
        //when
        charaService.setImage(characters);
        //then
        List<Character> all = characterRepository.findAll();
        Assertions.assertThat(all.stream().noneMatch(i -> i.getImagePath().isEmpty())).isEqualTo(true);
    }


    @Test
    @DisplayName("검색한 캐릭터를 반환하는가?")
    public void chara() throws Throwable {
        //given
        FindCharacter build = FindCharacter.builder().name("양을찾는모험")
                .serverId("all")
                .build();
        CharaDetail characters = charaService.findCharacterId(build);

        //when
        charaService.setImage(characters);

        //then
        Character[] characters1 = charaService.basicInform(build);
        System.out.println(characters1);

    }
}