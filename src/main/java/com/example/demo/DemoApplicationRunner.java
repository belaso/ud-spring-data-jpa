package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.person.Person;
import com.example.demo.person.PersonRepository;

@Component
public class DemoApplicationRunner implements ApplicationRunner {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Person person = new Person();
		person.setVorname("Christian");
		person.setNachname("Trutz");
		personRepository.save(person);
	}

}
