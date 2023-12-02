package com.example.dnfapistudy.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties
@Getter
public class CharacterInforms {

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
    }
}
