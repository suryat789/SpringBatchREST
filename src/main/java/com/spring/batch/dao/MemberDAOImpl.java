package com.spring.batch.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.batch.domain.Member;

@Repository("memberDao")
public class MemberDAOImpl extends AbstractDAO implements MemberDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addMember(Member member) throws Exception {
		//persist(member);
		jdbcTemplate.update("INSERT INTO MEMBER VALUES(?,?,?,?)", member.getId(), member.getFirstName(), member.getLastName(), member.getCity());
	}

	@SuppressWarnings("unchecked")
	public Member getMember(String id) {
		Member Member = null;
		Query query = getEntityManager().createQuery(" from Member mem WHERE mem.mid = :mid");
		query.setParameter("id", id);

		List<Member> Members = query.getResultList();

		if (Members != null && !Members.isEmpty()) {
			Member = Members.get(0);
		}
		return Member;
	}

}