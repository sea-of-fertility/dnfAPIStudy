package com.example.dnfapistudy.reposiotry;

import com.example.dnfapistudy.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
