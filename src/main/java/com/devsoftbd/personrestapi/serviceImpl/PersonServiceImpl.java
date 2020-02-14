package com.devsoftbd.personrestapi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsoftbd.personrestapi.model.HobbyModel;
import com.devsoftbd.personrestapi.model.PersonModel;
import com.devsoftbd.personrestapi.service.HobbyService;
import com.devsoftbd.personrestapi.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private HobbyService hobbyService;

	/**
	 * This method will set person hobbylist from hobby array and will inject person
	 * object with each hobby object
	 */
	@Override
	public PersonModel setPersonHobbyListFromHobbyStringArray(PersonModel person, String[] hobby) {
		List<HobbyModel> hobbyList = hobbyService.getHobbyListFromHobbyStringArray(hobby);
		if (hobbyList != null && hobbyList.size() > 0) {
			person.setHobbyList(hobbyList);
			hobbyList.forEach(hby -> hby.setPerson(person));
		}
		return person;
	}

	@Override
	public void setHobbyArrayFromHobbyList(PersonModel person) {
				if (person.getHobbyList() != null) {
					String[] hobby = new String[person.getHobbyList().size()];
					for (int i = 0; i < person.getHobbyList().size(); i++) {
						hobby[i] = person.getHobbyList().get(i).getHobby();
					}
					person.setHobby(hobby);
				}
	}

}
