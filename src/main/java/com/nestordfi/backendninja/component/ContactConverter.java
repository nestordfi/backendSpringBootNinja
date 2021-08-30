package com.nestordfi.backendninja.component;

import org.springframework.stereotype.Component;

import com.nestordfi.backendninja.entity.Contact;
import com.nestordfi.backendninja.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {

	public Contact convertContactModel2Entity(ContactModel contactModel) {
		Contact contact = new Contact(contactModel.getId(),
				contactModel.getFirstname(),
				contactModel.getLastname(),
				contactModel.getTelephone(),
				contactModel.getCity());
		return contact;
	}
	
	public ContactModel convertEntity2ContactModel(Contact contact) {
		ContactModel contactModel = new ContactModel(contact.getId(),
				contact.getFirstname(),
				contact.getLastname(),
				contact.getTelephone(),
				contact.getCity());
		return contactModel;
	}
	
}
