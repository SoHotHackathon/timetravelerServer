package com.example.Back.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) @AllArgsConstructor @NoArgsConstructor @Setter
public class chatGPTRequest {

        public String model;
        public double temperature;
        public List<Message> messages;

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) @AllArgsConstructor @NoArgsConstructor
        public static class Message {
            public String role;
            public String content;

        }
}
