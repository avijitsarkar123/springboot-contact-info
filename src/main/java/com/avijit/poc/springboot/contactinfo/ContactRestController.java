package com.avijit.poc.springboot.contactinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactRestController {

	@Autowired
	private ContactRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public DetailedResponse getAll() {
		DetailedResponse response = new DetailedResponse();
		response.setContactList(repo.findAll());
		return response;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Contact create(@RequestBody Contact contact) {
		return repo.save(contact);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable String id) {
		repo.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Contact update(@PathVariable String id, @RequestBody Contact contact) {
		Contact update = repo.findOne(id);
		update.setFirstName(contact.getFirstName());
		update.setLastName(contact.getLastName());
		update.setAddress(contact.getAddress());
		update.setCity(contact.getCity());
		update.setState(contact.getState());
		update.setCountry(contact.getCountry());
		update.setZip(contact.getZip());
		return repo.save(update);
	}

}
