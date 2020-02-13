package com.devsoftbd.personrestapi.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.devsoftbd.personrestapi.PersonRestApiApplication;
import com.devsoftbd.personrestapi.model.HobbyModel;
import com.devsoftbd.personrestapi.model.PersonModel;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonRestApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testCreatePerson() {
		PersonModel personModel = new PersonModel();
		personModel.setFirstName("Palash Kumar");
		personModel.setLastName("Nath");
		personModel.setAge(31);
		personModel.setFavouriteColour("blue");
		personModel.setCreatedAt(new Date());

		List<HobbyModel> hobbyList = new ArrayList<>();
		HobbyModel shopping = new HobbyModel("shopping");
		HobbyModel football = new HobbyModel("football");
		hobbyList.add(shopping);
		hobbyList.add(football);
		personModel.setHobbyList(hobbyList);

		ResponseEntity<PersonModel> postResponse = restTemplate.postForEntity(getRootUrl() + "/person", personModel,
				PersonModel.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
}
