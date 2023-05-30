package com.fafycom.trading.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fafycom.trading.entities.Contact;
import com.fafycom.trading.repositories.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{

	
	
@Autowired
    private ContactRepository contactRepository;

	@Override
	public Contact createContact(Contact contact) {
		return contactRepository.save(contact);
	}

}
