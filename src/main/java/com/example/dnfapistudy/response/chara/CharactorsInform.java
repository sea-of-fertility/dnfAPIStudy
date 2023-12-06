package com.example.dnfapistudy.response.chara;

import com.example.dnfapistudy.api.CharaBasicInform;
import lombok.Getter;

public class CharactorsInform {

    private CharaBasicInform.CharacterInform[] rows;

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
