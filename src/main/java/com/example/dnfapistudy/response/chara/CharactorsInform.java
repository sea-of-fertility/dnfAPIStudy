package com.example.dnfapistudy.response.chara;

import com.example.dnfapistudy.api.CharacterInforms;
import lombok.Getter;

public class CharactorsInform {

    private CharacterInforms.CharacterInform[] rows;

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
