package com.example.dnfapistudy.reposiotry;

import com.example.dnfapistudy.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {


    Optional<Character[]> findByCharacterNameAndServerId(String name, String serverId);

    Optional<Character> findByCharacterId(String charaId);
}
