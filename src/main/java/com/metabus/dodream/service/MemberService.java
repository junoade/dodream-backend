package com.metabus.dodream.service;

import com.metabus.dodream.domain.account.Member;
import com.metabus.dodream.dto.account.MemberDto;
import com.metabus.dodream.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public boolean isValidMember(String id, String pwd){
        Optional<Member> member = memberRepository.findById(id);
        return member.map(value -> value.getPwd().equals(pwd)).orElse(false);
        //return member.map(value -> value.getPwd().equals(pwd)).orElse(false);
    }
}
