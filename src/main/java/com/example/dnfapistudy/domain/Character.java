package com.example.dnfapistudy.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Character {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagePath;

    @NotBlank
    private String serverId;

    private String characterName;

    @NotBlank
    private String characterId;

    private int level;

    private String jobName;

    private String jobGrowName;

    private int fame;

    @Builder
    public Character(String imagePath, String serverId, String characterName, String characterId, int level, String jobName, String jobGrowName, int fame) {
        this.imagePath = imagePath;
        this.serverId = serverId;
        this.characterName = characterName;
        this.characterId = characterId;
        this.level = level;
        this.jobName = jobName;
        this.jobGrowName = jobGrowName;
        this.fame = fame;
    }
}
