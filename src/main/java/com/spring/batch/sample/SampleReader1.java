package com.spring.batch.sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.spring.batch.domain.Person;

public class SampleReader1 implements ItemReader<Person> {

	private List<Person> persons = new ArrayList<Person>();
	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return persons.remove(0);
	}

}