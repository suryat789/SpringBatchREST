package com.spring.batch.dao;

import com.spring.batch.domain.Member;


public interface MemberDAO {
	
	public void addMember(Member member) throws Exception;

	Member getMember(String id);
		
}