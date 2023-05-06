package com.example.Back;

import com.example.Back.domain.Member;
import com.example.Back.service.chatGPTService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(properties = {"hackaton.openai-api-key=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"})
//@Disabled("This test requires a valid OpenAI API key. Please change the below property to a valid key.")
public class chatgptTest {

    @Autowired
    private chatGPTService chatGPTservice;

    @Autowired EntityManager em;

    @Test
    @Transactional
    public void testCompleteChat()  {

        // Arrange
        String prompt =
                "나는 42세 여자 직장인이고 mbti는 ISTJ, 이름은 김똘똘이야.\n" +
                        "내 고민은 일을 열심히 해도 돈이 모이지않아.\n" +
                        "이순신 장군에게 이 내용을 상담받고싶어.\n" +
                        "첫째로, 이순신 장군의 삶의 태도가 많이 드러나야해.\n" +
                        "둘째로, 이순신 장군라면 어떻게 이 문제를 해결했을지 드러나야해.\n" +
                        "셋째로, 나의 특징에 맞는 맞춤형 대답을 받아야해.\n" +
                        "넷째로, 나와 이순신 장군이 번갈아가며 대화해야해.\n" +
                        "즉, 아래와같은 형식이어야해.\n" +
                        "\n" +
                        "김똘똘: 그렇군요. 하지만 저는 이미 다양한 직종을 경험해봤지만 그 중에서도 지금까지 가장 행복했던 일은 없었습니다.\n" +
                        "빌 게이츠: 그렇다면, 김똘똘님의 장점이 무엇인지 한번 생각해보세요. 자신이 잘하는 것, 좋아하는 것을 찾아서 그 분야에서 새로운 일을 시작해보는 것도 좋은 방법이 될 수 있습니다.\n" +
                        "\n" +
                        "다섯째로, 대화를 주고받는 횟수는 '나'가 6회, '이순신 장군'이 6회 총 12회여야해. 대화 횟수는 세지마. '1번째 대화', '첫 번째 대화', '1회' 이런거 출력하지마.\n" +
                        "'대화 종료', '대화 시작' 이런 것도 출력하지마.\n" +
                        "위 다섯가지 조건을 지켜서 대화 스크립트를 작성해줘.";

        // Act
        String completedChat = chatGPTservice.completeChat(prompt);
        System.out.println(completedChat);

        System.out.println("=================");
        String jsonOrder = completedChat + "의 내용을 JSON으로 변환해. 반드시 JSON으로만 변환할 것을 명심해."
                + "JSON의 형식은 아래와 따르도록해.\n"
                + "{'conversation'/*전체 대화*/ : +" +
                "[" +
                "{'member' : /*말하는 사람의 이름*/, 'message' : /*말한 내용*/}" +
                " /*'member', 'message' 반복*/" +
                "]";
        String toJson = chatGPTservice.completeChat(jsonOrder);
        System.out.println(toJson);

        // Assert
        Assertions.assertThat(toJson).isNotEmpty();
    }
}