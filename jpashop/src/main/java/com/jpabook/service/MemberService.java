package com.jpabook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.domain.Member;
import com.jpabook.repository.MemberRepository;

@Service
@Transactional(readOnly = true) 
public class MemberService {
	
private final MemberRepository memberRepository;


public MemberService(MemberRepository memberRepository) {
	this.memberRepository=memberRepository;
}

@Transactional
public Long join(Member member) {
	validateDuplicateMember(member);
	memberRepository.save(member);
	return member.getId();
}

private void validateDuplicateMember(Member member) {
	List<Member> findMembers=memberRepository.findByName(member.getName());
	if(!findMembers.isEmpty()) {
		throw new IllegalStateException("이미 존재하는 회원입니다.");
	}
	
}

public List<Member> findMembers(){
	return memberRepository.findAll();
} 


public Member findOne(Long memberId) {
	return memberRepository.findOne(memberId);
}
}
