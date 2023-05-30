package com.fafycom.trading.restController;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fafycom.trading.entities.Contact;
import com.fafycom.trading.service.ContactService;

import lombok.AllArgsConstructor;


//@RestController
//@AllArgsConstructor
//@RequestMapping(path= "/api/v1/fafycom")
public class ContactRestController {
    private ContactService contactService;

    @PostMapping(path="/contacts")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.createContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }
	
}
