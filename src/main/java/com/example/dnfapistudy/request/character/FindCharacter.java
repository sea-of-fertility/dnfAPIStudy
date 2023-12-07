package com.example.dnfapistudy.request.character;


import com.example.dnfapistudy.domain.Character;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class FindCharacter {

    @NotEmpty
    private String name;

    private String serverId;

    @Builder
    public FindCharacter(String name, String serverId) {
        this.name = name;
        this.serverId = Objects.equals(serverId, "") ? serverId = "all" : serverId;
    }

}