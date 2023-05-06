package com.example.Back.service;

import com.example.Back.controller.dto.conversationReq;
import com.example.Back.domain.Conversation;
import com.example.Back.domain.Member;
import com.example.Back.domain.Person;
import com.example.Back.repository.ConversationRepository;
import com.example.Back.repository.MemberRepository;
import com.example.Back.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private chatGPTService chatGPTService;
    @Autowired
    private EntityManager em;

    public String createConversation(Long memberId,Long personId) {


    public String createConversation(conversationReq requestDto)
    {

        Member member = new Member();

        member.setAge(requestDto.getAge());
        member.setMBTI(requestDto.getMbti());
        member.setJob(requestDto.getJob());
        member.setGender(requestDto.getGender());
        member.setConsulting(requestDto.getConsulting());


        Person person = personRepository.findOne(requestDto.getPerson_id());


        Conversation conversation = new Conversation();

        conversation.setMember(member);
        conversation.setPerson(person);
        conversation.setCreatedTime(LocalDateTime.now());

        final String prompt =
                "나는 " + member.getAge() + "세 " +
                        member.getGender() + "직장인이고 " +
                        "mbti는 " + member.getMBTI() +
                        ", 이름은 " + member.getName() + "이야.\n" +
                        "내 고민은 " + member.getConsulting() + "\n" +
                        person.getName() + "에게 이 내용을 상담받고싶어.\n" +
                        "첫째로, "+person.getName()+"의 삶의 태도가 많이 드러나야해.\n" +
                        "둘째로, "+person.getName()+"이라면 어떻게 이 문제를 해결했을지 드러나야해.\n" +
                        "셋째로, 나의 특징에 맞는 맞춤형 대답을 받아야해.\n" +
                        "넷째로, 나와 "+ person.getName()+"이 번갈아가며 대화해야해.\n" +
                        "즉, 아래와같은 형식이어야해.\n" +
                        "\n" +
                        member.getName()+": 그렇군요. 하지만 저는 이미 다양한 직종을 경험해봤지만 그 중에서도 지금까지 가장 행복했던 일은 없었습니다.\n" +
                        person.getName()+": 그렇다면, 당신의 장점이 무엇인지 한번 생각해보세요. 자신이 잘하는 것, 좋아하는 것을 찾아서 그 분야에서 새로운 일을 시작해보는 것도 좋은 방법이 될 수 있습니다.\n" +
                        "\n" +
                        "다섯째로, 대화를 주고받는 횟수는 '나'가 6회, '이순신 장군'이 6회 총 12회여야해. 대화 횟수는 세지마. '1번째 대화', '첫 번째 대화', '1회' 이런거 출력하지마.\n" +
                        "'대화 종료', '대화 시작' 이런 것도 출력하지마.\n" +
                        "위 다섯가지 조건을 지켜서 대화 스크립트를 작성해줘.";


        String toJson = "";


        try
        {
            String completedChat = chatGPTService.completeChat(prompt);
//            conversation.setScript(completedChat);

            String jsonOrder = completedChat + "의 내용을 JSON으로 변환해. 반드시 JSON으로만 변환할 것을 명심해."
                    + "JSON의 형식은 아래를 따르도록해.\n"
                    + "{'conversation'/*전체 대화*/ : +" +
                    "[" +
                    "{'member' : /*말하는 사람의 이름*/, 'message' : /*말한 내용*/}" +
                    " /*'member', 'message' 반복*/" +
                    "]";


            toJson = chatGPTService.completeChat(completedChat);
            

        } catch (IllegalArgumentException e)
        {
            System.out.println("Error during formatting to JSON");
        }

        conversation.setScript(toJson);
        conversationRepository.save(conversation);


        return prompt;
    }

    public List<Conversation> findAll()
    {
        return conversationRepository.findAll();
    }


}
