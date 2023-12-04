package com.example.dnfapistudy.controller;

import com.example.dnfapistudy.request.CharaInform;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class CharaControllerTest {


    @Autowired
    MockMvc mockMvc;


    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void charaInform() throws Exception {
        //given
        String severId = "cain";
        String charaName = "양을찾는모험";
        
        CharaInform build = CharaInform.builder()
                .name(charaName)
                .serverId(severId)
                .build();
        
        String json = objectMapper.writeValueAsString(build);
        //when  then
        mockMvc.perform(MockMvcRequestBuilders.get("/character")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rows[0].serverId").value(severId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rows[0].characterName").value(charaName));
    }

}