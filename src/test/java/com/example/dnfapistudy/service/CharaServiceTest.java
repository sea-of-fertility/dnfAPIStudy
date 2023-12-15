package com.example.dnfapistudy.service;

import com.example.dnfapistudy.api.CharaDetail;
import com.example.dnfapistudy.domain.Character;
import com.example.dnfapistudy.exception.CharaNotFoundException;
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


    String commonName = "유키";

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
        FindCharacter build = FindCharacter.builder().name("양을찾는모험")
                .serverId("")
                .build();
        //when
        CharaDetail characters = charaService.findCharacterId(build);
        //then
        Arrays.stream(characters.getRows()).forEach(i -> System.out.println(i.getCharacterId()));
        Assertions.assertThat(Arrays.stream(characters.getRows()).allMatch(i -> i.getCharacterName().equals("양을찾는모험"))).isEqualTo(true);
    }


    @Test
    @DisplayName("모든 entity에 사진 경로가 들어갔는가")
    public void setImage() throws Exception {
        //given
        FindCharacter build = FindCharacter.builder().name("양을찾는모험")
                .serverId("all")
                .build();
        CharaDetail characters = charaService.findCharacterId(build);
        //when
        List<Character> characters1 = charaService.setImage(characters);
        charaService.save(characters1);
        //then
        List<Character> all = characterRepository.findAll();
        all.stream().forEach(i -> System.out.println(i.getServerId()));
        Assertions.assertThat(all.stream().noneMatch(i -> i.getImagePath().isEmpty())).isEqualTo(true);
    }


    @Test
    @DisplayName("검색한 캐릭터를 반환하는가?")
    public void chara() throws Throwable {
        //given
        FindCharacter build = FindCharacter.builder().name(commonName)
                .serverId("all")
                .build();
        CharaDetail characters = charaService.findCharacterId(build);

        //when
        List<Character> characters2 = charaService.setImage(characters);
        charaService.save(characters2);
        //then
        List<Character> characters1 = charaService.basicInform(build);
        characters1.forEach(i -> System.out.println(i.getCharacterId()));
    }

    @Test
    @DisplayName("닉네임 검색된 갯수 0개")
    public void charaNotFound() throws Exception {
        //given
        FindCharacter build = FindCharacter.builder().name("겹치질않을 이름입니다.")
                .serverId("all")
                .build();
        Assertions.assertThatThrownBy(() -> charaService.findCharacterId(build))
                .isInstanceOf(CharaNotFoundException.class);
    }

}