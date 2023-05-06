package com.example.Back.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) @Getter
public class chatGPTResponse {
        public String id;
        public String object;
        public long created;
        public String model;
        public chatGPTResponse.Usage usage;
        public List<chatGPTResponse.Choice> choices;

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)  @Getter
        public static class Usage {
            public int promptTokens;
            public int completionTokens;
            public int totalTokens;
        }

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) @Getter
        public static class Choice {
            public chatGPTResponse.Choice.Message message;
            public String finishReason;
            public int index;

            @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) @Getter @Setter
            public static class Message {
                public String role;
                public String content;
            }
        }


}
