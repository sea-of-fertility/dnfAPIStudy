package com.example.dnfapistudy.request;


import lombok.Builder;
import lombok.Getter;

@Getter
public class CharaInform {
    private String name;
    private String serverId;

    @Builder
    public CharaInform(String name, String serverId) {
        this.name = name;
        this.serverId = serverId == null ? serverId = "all" : serverId;
    }
}