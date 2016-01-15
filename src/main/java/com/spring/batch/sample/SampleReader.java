package com.spring.batch.sample;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;

import com.spring.batch.domain.Person;

public class SampleReader extends JpaPagingItemReader<Person> {

	private Long startId;

	private Long endId;

	public Long getStartId() {
		return startId;
	}

	public void setStartId(Long startId) {
		this.startId = startId;
	}

	public Long getEndId() {
		return endId;
	}

	public void setEndId(Long endId) {
		this.endId = endId;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//Prepare and set the Query
		String query = buildQuery();
		setQueryString(query);
		
		super.afterPropertiesSet();
	}
	
	private String buildQuery() {
		String query = null;
		query = "select p from Person p where p.id >= " + getStartId() + " and p.id <= " + getEndId();
		return query;
	}
}