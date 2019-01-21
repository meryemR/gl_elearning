package org.mql.services;

import org.mql.dao.MemberRepository;
import org.mql.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired 
	private MemberRepository memberRepository;
	
	@Override
	public Member findByEmail(String email) {	
		return memberRepository.findByEmail(email);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// load member - throw exception if not found
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			throw new UsernameNotFoundException("Member not found");
			
		}
		// return the found member
		return member;
	}
}
