package com.example.dnfapistudy.controller;


import com.example.dnfapistudy.api.CharaBasicInform;
import com.example.dnfapistudy.request.FindCharacter;
import com.example.dnfapistudy.service.CharaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController()
@RequiredArgsConstructor
@Slf4j
public class CharaController {


    private final CharaService charaService;

    /**
     *
     * @param findCharacter 서버와 캐릭터 이름을 가지고 있다.
     * @return 캐릭터 이미지와 이름 명성 레벨을 반환 한다.
     * @throws IOException
     */
    @GetMapping("/character")
    public void chara(@RequestBody FindCharacter findCharacter) throws IOException {
        CharaBasicInform charaBasicInform = charaService.findCharacterId(findCharacter);
        charaService.setImageAndSave(charaBasicInform);
    }

}