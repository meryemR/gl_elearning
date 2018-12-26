package org.mql.dao;

import org.mql.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Integer>{

}
