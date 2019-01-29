package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.person.Person;
import com.example.demo.person.PersonRepository;

@Component
public class DemoApplicationRunner implements ApplicationRunner {

	private final static Logger logger = LoggerFactory.getLogger(DemoApplicationRunner.class);

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// Entity anlegen
		Person person = new Person();
		person.setVorname("Christian");
		person.setNachname("Trutz");
		personRepository.save(person);
		logger.info("Entity angelegt.");

		// Entity finden
		logger.info("Anzahl gefundene Objekte: {}.", personRepository.findAll().size());
	}

}
