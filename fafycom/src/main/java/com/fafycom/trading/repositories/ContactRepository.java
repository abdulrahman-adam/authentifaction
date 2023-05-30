package com.fafycom.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafycom.trading.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
