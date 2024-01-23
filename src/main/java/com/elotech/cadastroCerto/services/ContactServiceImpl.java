package com.elotech.cadastroCerto.services;

import com.elotech.cadastroCerto.domains.Contact;
import com.elotech.cadastroCerto.exceptions.NotFoundException;
import com.elotech.cadastroCerto.repositories.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor

@Service
public class ContactServiceImpl implements ContactService{

    private final ContactRepository contactRepository;

    @Override
    public Page<Contact> getContactsPaged(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Override
    public Contact getContactById(UUID id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID not found!"));
    }

    @Override
    public void updateContact(UUID id, Contact update) {
        Contact existingEntity = getContactById(id);
        BeanUtils.copyProperties(update, existingEntity, "id", "person");
        contactRepository.save(existingEntity);
    }

    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(UUID id) {
        try {
            contactRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundException("ID not found!");
        }
    }


}
