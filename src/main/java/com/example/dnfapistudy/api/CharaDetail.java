package com.example.dnfapistudy.api;

import com.example.dnfapistudy.domain.Character;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties
@Getter
public class CharaDetail {

    private CharacterInform[] rows;

    @Getter
    public  static  class CharacterInform{
        private String serverId;
        private String characterName;
        private String characterId;
        private int level;
        private String jobName;
        private String jobGrowName;
        private int fame;


        public Character toEntity(CharacterInform this, String path) {

            return Character.builder()
                    .serverId(this.serverId)
                    .characterName(this.characterName)
                    .characterId(this.characterId)
                    .level(this.level)
                    .jobName(this.jobName)
                    .imagePath(path)
                    .jobName(this.jobName)
                    .jobGrowName(this.jobGrowName)
                    .fame(this.fame)
                    .build();
        }
    }
}
