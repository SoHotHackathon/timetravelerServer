package com.example.Back.service;


import com.example.Back.controller.dto.memberReq;
import com.example.Back.domain.Member;
import com.example.Back.repository.MemberRepository;
import org.springframework.stereotype.Service;


@Service
public class MemberService {

    public static MemberRepository memberRepository;

    public Member getById(Long id){
        return memberRepository.getById(id);
    }

    public Long createMember(memberReq memberReq) {
        Member member = new Member();
        member.setJob(memberReq.getJob());
        member.setName(memberReq.getName());
        member.setGender(memberReq.getGender());
        member.setAge(memberReq.getAge());
        member.setMBTI(memberReq.getMbti());
        member.setConsulting(memberReq.getConsulting());
        memberRepository.save(member);
        return member.getId();
    }

}
