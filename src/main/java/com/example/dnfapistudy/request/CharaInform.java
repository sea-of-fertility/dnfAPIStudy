package com.example.dnfapistudy.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CharaInform {

    @NotEmpty
    private String name;

    private String serverId;

    @Builder
    public CharaInform(String name, String serverId) {
        this.name = name;
        this.serverId = Objects.equals(serverId, "") ? serverId = "all" : serverId;
    }
}