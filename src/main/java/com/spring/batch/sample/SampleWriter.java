package com.spring.batch.sample;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.batch.dao.MemberDAO;
import com.spring.batch.domain.Member;

public class SampleWriter implements ItemWriter<Member> {

	@Autowired
	MemberDAO memberDAO;
	
	public void write(List<? extends Member> items) throws Exception {
		for (Member member : items) {
			memberDAO.addMember(member);
		}
	}

}