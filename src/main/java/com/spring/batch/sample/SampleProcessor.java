package com.spring.batch.sample;

import org.springframework.batch.item.ItemProcessor;

import com.spring.batch.domain.Member;
import com.spring.batch.domain.Person;

public class SampleProcessor<T> implements ItemProcessor<Person, Member> {
	public Member process(Person item) throws Exception {
		
		Member member = new Member(item.getId(), item.getFirstName(), item.getLastName(), item.getCity());
		return member;
	}
}
