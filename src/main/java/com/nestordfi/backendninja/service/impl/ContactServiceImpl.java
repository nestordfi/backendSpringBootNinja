package com.nestordfi.backendninja.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nestordfi.backendninja.component.ContactConverter;
import com.nestordfi.backendninja.entity.Contact;
import com.nestordfi.backendninja.model.ContactModel;
import com.nestordfi.backendninja.repository.ContactRepository;
import com.nestordfi.backendninja.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact =  contactRepository.save(contactConverter.convertContactModel2Entity(contactModel));
		return contactConverter.convertEntity2ContactModel(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactsModel = new ArrayList<ContactModel>();
		for (Contact contact : contacts) {
			ContactModel contactModel = contactConverter.convertEntity2ContactModel(contact);
			contactsModel.add(contactModel);
		}
		return contactsModel;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	@Override
	public ContactModel findContactModelById(int id) {
		return contactConverter.convertEntity2ContactModel(findContactById(id));
	}

	@Override
	public void removeContact(int id) {
		Contact contact = contactRepository.findById(id);
		if (null != contact) {
			contactRepository.delete(contact);
		}
	}

}
