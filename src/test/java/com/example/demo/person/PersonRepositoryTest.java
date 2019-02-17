package com.example.demo.person;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

	private static final Logger logger = LoggerFactory.getLogger(PersonRepositoryTest.class);

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	DataSource datasource;

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

	@Test
	@Transactional
	public void testParent() throws SQLException {

		// Transaktion: Adam und Kain werden gespeichert
		{
			assertTrue(TestTransaction.isActive());

			// Adam
			Person adam = new Person();
			adam.setName("Adam");
			personRepository.save(adam);

			// Kain
			Person kain = new Person();
			kain.setName("Kain");
			kain.setParent(adam);
			personRepository.save(kain);

			TestTransaction.flagForCommit();
			TestTransaction.end();
		}

		// Transaktion: Person#children werden gelesen
		{
			TestTransaction.start();

			Long idAdam = 1L;
			personRepository.findById(idAdam).ifPresent(entity -> {
				entity.getChildren().forEach(child -> {
					assertNotNull(child);
					logger.info("Child named {} with parent {}.", child.getName(), child.getParent().getName());
				});
			});

			TestTransaction.flagForCommit();
			TestTransaction.end();
		}

		// Server.startWebServer(datasource.getConnection());

	}

}
