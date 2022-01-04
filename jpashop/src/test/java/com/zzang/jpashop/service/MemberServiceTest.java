package com.zzang.jpashop.service;

import com.zzang.jpashop.domain.Member;
import com.zzang.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void join() {
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long saveId = memberService.join(member);

        //then
//        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void joinException() {
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");
        //when
        memberService.join(member1);
            memberService.join(member2); // 예외 발생해야 한다.
        //then
        fail("예외가 발생해야 합니다.");

    }

}