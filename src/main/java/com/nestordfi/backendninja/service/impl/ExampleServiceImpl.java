package com.nestordfi.backendninja.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.nestordfi.backendninja.model.Person;
import com.nestordfi.backendninja.service.ExampleService;

@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {
	
	private static final Log LOGGER = LogFactory.getLog(ExampleServiceImpl.class);

	@Override
	public List<Person> getListPeople() {
		List<Person> people = new ArrayList<>();
		people.add(new Person("Nf7", 24));
		people.add(new Person("MIKEL", 30));
		people.add(new Person("EVA", 43));
		people.add(new Person("WALLY", 28));
		LOGGER.info("I CAME TO GET PEOPLE");
		return people;
	}

}
