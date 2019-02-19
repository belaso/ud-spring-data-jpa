package com.example.demo.person;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testSinglePerson() throws SQLException {

		// John Dow ist noch nicht in der DB gespeichert
		Person johndoe = new Person();
		assertNull(johndoe.getId());

		// jetzt schon, d.h. die Id ist nicht mehr null
		// save bedeutet hier ein SQL insert
		personRepository.save(johndoe);
		assertNotNull(johndoe.getId());

		// jetzt hat John Dow auch einen Namen
		johndoe.setName("John Doe");
		// save bedeutet hier ein SQL update
		personRepository.save(johndoe);

	}

}
