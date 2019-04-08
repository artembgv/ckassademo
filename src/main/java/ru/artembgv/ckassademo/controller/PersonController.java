package ru.artembgv.ckassademo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.artembgv.ckassademo.dao.PersonRepository;
import ru.artembgv.ckassademo.entity.Person;
import ru.artembgv.ckassademo.exception.PersonNotFoundException;

@RestController
public class PersonController {
	Logger log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/getPerson")
	public Person getPerson(@RequestParam(value = "id", required = true) int personId) {
		log.info("Вызов getPerson, id=" + personId);
		Optional<Person> person = personRepository.findById(personId);
		if (!person.isPresent())
			throw new PersonNotFoundException(personId);
		log.debug(person.get().toString());
		return person.get();
	}

	@GetMapping("/getPersonByInn")
	public Person getPersonByInn(@RequestParam(value = "inn", required = true) long inn) {
		log.info("Вызов getPersonByInn, inn=" + inn);
		Optional<Person> person = personRepository.findByInn(inn);
		if (!person.isPresent())
			throw new PersonNotFoundException(inn);
		log.debug(person.get().toString());
		return person.get();
	}

	@RequestMapping("/getAllPersons")
	public Iterable<Person> getAllPersons() {
		log.info("Вызов getAllPersons");
		return personRepository.findAll();
	}

	@PostMapping("/createPerson")
	public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
		log.info("Вызов createPerson " + person);
		Person savedPerson = personRepository.save(person);
		return new ResponseEntity<Person>(savedPerson, HttpStatus.CREATED);
	}

	@DeleteMapping("/deletePerson")
	public Person deletePerson(@RequestParam(value = "id", required = true) int personId) {
		log.info("Вызов deletePerson, id=" + personId);
		Optional<Person> person = personRepository.findById(personId);
		if (!person.isPresent())
			throw new PersonNotFoundException(personId);
		personRepository.deleteById(personId);
		return person.get();
	}
}