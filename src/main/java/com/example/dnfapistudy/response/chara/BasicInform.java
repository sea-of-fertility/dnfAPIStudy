package com.example.dnfapistudy.response.chara;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BasicInform {

    private String imagePath;
    private String serverId;
    private String characterName;
    private String characterId;
    private int level;
    private String jobName;
    private String jobGrowName;
    private int fame;

    @Builder
    public BasicInform(String imagePath, String serverId, String characterName, String characterId, int level, String jobName, String jobGrowName, int fame) {
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
