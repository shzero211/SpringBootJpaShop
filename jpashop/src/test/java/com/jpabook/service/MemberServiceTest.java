package com.jpabook.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.domain.Member;
import com.jpabook.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceTest {
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void 회원가입() throws Exception{
		Member member =new Member();
		member.setName("kim");
		
		Long savedId=memberService.join(member);
		
		org.junit.jupiter.api.Assertions.assertEquals(member,memberRepository.findOne(savedId));
	}

	@Test
	public void 중복확인() throws Exception{
		Member member1=new Member();
		member1.setName("kim");
		Member member2=new Member();
		member2.setName("kim");
		memberService.join(member1);
		try {
			memberService.join(member2);
		}catch (IllegalStateException e) {
			return;
		}
		
	}

}
