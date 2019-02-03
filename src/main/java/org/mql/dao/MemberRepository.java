package org.mql.dao;

import java.util.List;

import org.mql.models.Member;
import org.mql.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberRepository extends JpaRepository<Member, Integer>{
	@Query("select m from Member m where m.email like :x")
	public Member findByEmail(@Param("x")String email);
	public List<Member> findByRolesContaining(Role role);
}
