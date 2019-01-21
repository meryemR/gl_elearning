package org.mql.services;

import org.mql.dao.MemberRepository;
import org.mql.dao.RoleRepository;
import org.mql.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsService implements UserDetailsService{
	@Autowired MemberRepository memberRepository;
	
	@Autowired RoleRepository RoleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			return new org.springframework.security.core.userdetails.User("", "", true,true,true,true,null);
		}
		
		
		
		return null;
	}
}
