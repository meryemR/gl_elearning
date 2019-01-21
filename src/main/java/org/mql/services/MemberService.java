package org.mql.services;

import org.mql.models.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService{
	Member findByEmail(String email);
}
