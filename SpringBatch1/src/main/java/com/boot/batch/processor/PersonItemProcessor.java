package com.boot.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.boot.batch.model.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

	@Override
	public Person process(final Person person) throws Exception {

		final String firstName = person.getFirstName().toLowerCase();
		final String lastName = person.getLastName().toLowerCase();
		final Person updatedPerson = new Person(firstName, lastName, lastName, person.getAge());
		log.info("transforming person..... {}", person.getFirstName());
		return updatedPerson ;
	}

}
