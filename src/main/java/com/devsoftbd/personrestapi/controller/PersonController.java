package com.devsoftbd.personrestapi.controller;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsoftbd.personrestapi.model.PersonModel;
import com.devsoftbd.personrestapi.repository.PersonRepository;
import com.devsoftbd.personrestapi.service.PersonService;

@RestController
@RequestMapping(value = "/person-service/api/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@PostMapping("/persons")
	public PersonModel createPerson(@Valid @RequestBody PersonModel person) {
		if (person.getHobby() != null && person.getHobby().length > 0)
			person = personService.setPersonHobbyListFromHobbyStringArray(person, person.getHobby());
		person.setCreatedAt(new Date());
		return personRepository.save(person);
	}

	@GetMapping("/persons")
	public List<PersonModel> getAllPersons() {
		List<PersonModel> list = personRepository.findAll();
		personService.setHobbyArrayFromHobbyList(list);
		return personRepository.findAll();
	}

	@GetMapping("/persons/{id}")
	public ResponseEntity<PersonModel> getByPersonId(@PathVariable(value = "id") Long personId)
			throws InvalidParameterException {
		return null;
	}

	@PutMapping("/persons/{id}")
	public ResponseEntity<PersonModel> updatePerson(@PathVariable(value = "id") Long personId,
			@Valid @RequestBody PersonModel personDetails) throws InvalidParameterException {
		return null;
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deletePersonById(@PathVariable(value = "id") Long personId) {
		return null;
	}

}
