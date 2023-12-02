package com.example.dnfapistudy.api;


import lombok.Getter;

@Getter
public class ServerInform {
    private Server[] rows;

    @Getter
    static class Server{
        private String serverId;
        private String serverName;
    }
}



