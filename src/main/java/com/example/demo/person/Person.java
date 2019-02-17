package com.example.demo.person;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private String name;

	@ManyToOne
	@Getter
	@Setter
	private Person parent;

	@OneToMany(mappedBy = "parent")
	@Getter
	@Setter
	private Set<Person> children;

}
