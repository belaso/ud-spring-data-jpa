package com.example.demo;

import java.util.LinkedList;
import java.util.List;

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
		logger.info("Entity angelegt mit Id: {}.", person.getId());

		// Entities anlegen (ineffizient), ca. 70ms für 100 Einträge (in-memory
		// Datenbank)
		for (int i = 0; i < 100000; i++) {
			Person testEntity = new Person();
			testEntity.setVorname("John");
			testEntity.setNachname("Doe " + i);
			personRepository.saveAndFlush(testEntity);
		}
		logger.info("Entities anlegen (ineffizient)");

		// Entities anlegen (effizient)
		List<Person> testEntities = new LinkedList<>();
		for (int i = 0; i < 100000; i++) {
			Person testEntity = new Person();
			testEntity.setVorname("John");
			testEntity.setNachname("Doe " + i);
			testEntities.add(testEntity);
		}
		personRepository.saveAll(testEntities);
		logger.info("Entities anlegen (effizient)");

		// Anzahl Entities ausgeben
		logger.info("Anzahl gefundene Objekte (ineffizient): {}.", personRepository.findAll().size());
		// und jetzt effizient
		logger.info("Anzahl gefundene Objekte (effizient): {}.", personRepository.count());
	}

}
