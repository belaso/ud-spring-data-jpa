package com.example.demo.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Person {

	@Id
	@GeneratedValue
	@Getter
	private Long id;

	@Getter
	@Setter
	private String vorname;

	@Getter
	@Setter
	private String nachname;

}
