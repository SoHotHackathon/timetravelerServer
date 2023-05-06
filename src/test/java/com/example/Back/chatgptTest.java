package com.example.Back;

import com.example.Back.service.chatGPTService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(properties = {"hackaton.openai-api-key=example.key"})
@Disabled("This test requires a valid OpenAI API key. Please change the below property to a valid key.")
public class chatgptTest {

    @Autowired
    private chatGPTService chatGPTservice;

    @Test
    public void testCompleteChat() {
        // Arrange
        String prompt = "신한카드는 올해에도 시장점유율 1등할 수 있을까?";

        // Act
        String completedChat = chatGPTservice.completeChat(prompt);
        System.out.println(completedChat);

        // Assert
        Assertions.assertThat(completedChat).isNotEmpty();
    }
}