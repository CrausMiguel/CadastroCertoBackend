package com.elotech.cadastroCerto.services;

import com.elotech.cadastroCerto.domains.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface ContactService{
    Page<Contact> getContactsPaged(Pageable pageable);
    Contact getContactById(UUID id);

    @Transactional
    void updateContact(UUID id, Contact update);

    @Transactional
    Contact createContact(Contact newPerson);

    @Transactional
    void deleteContact(UUID id);
}
