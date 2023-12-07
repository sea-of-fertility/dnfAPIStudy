package com.example.dnfapistudy.reposiotry;

import com.example.dnfapistudy.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {


    Optional<List<Character>> findByCharacterName(String name);

    Optional<Character> findByCharacterId(String charaId);
}
