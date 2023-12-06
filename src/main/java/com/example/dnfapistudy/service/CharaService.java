package com.example.dnfapistudy.service;

import com.example.dnfapistudy.domain.Character;
import com.example.dnfapistudy.reposiotry.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharaService {

    private final CharacterRepository characterRepository;




    public void save(Character character){
        characterRepository.save(character);
    }
}
