package com.elotech.cadastroCerto.services;

import com.elotech.cadastroCerto.domains.Contact;
import com.elotech.cadastroCerto.domains.Person;
import com.elotech.cadastroCerto.exceptions.NotFoundException;
import com.elotech.cadastroCerto.repositories.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    @DisplayName("Should create Contact successfully")
    void createContactCase() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();
        Person person = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                120,
                contacts);

        Contact newContact = new Contact(
                UUID.fromString(id),
                "Joao",
                "4444-4444",
                "vitor@mail.com",
                person
        );


        when(contactRepository.save(newContact)).thenReturn(newContact);
        contactService.createContact(newContact);
        verify(contactRepository, times(1)).save(newContact);
    }

    @Test
    @DisplayName("Should get Contact by ID successfully")
    void getContactByIdCase1() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();
        Person person = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                120,
                contacts);

        Contact contactOnDB = new Contact(
                UUID.fromString(id),
                "Joao",
                "4444-4444",
                "vitor@mail.com",
                person
        );

        when(contactRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(contactOnDB));

        Contact foundContact = contactService.getContactById(UUID.fromString(id));

        assertEquals(contactOnDB, foundContact);
    }

    @Test
    @DisplayName("Should throw exception ID NOT FOUND")
    void getContactByIdCase2() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";

        when(contactRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> {
            contactService.getContactById(UUID.fromString(id));
        });
    }

    @Test
    @DisplayName("Should update contact successfully")
    void updateContactByIdCase1() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();
        Person person = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                120,
                contacts);

        Contact contactOnBD = new Contact(
                UUID.fromString(id),
                "Joao",
                "4444-4444",
                "vitor@mail.com",
                person
        );

        Contact contactUpdate = new Contact(
                UUID.fromString(id),
                "Joao",
                "5555-5555",
                "vitor@mail.com",
                person
        );


        when(contactRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(contactOnBD));
        contactService.updateContact(UUID.fromString(id), contactUpdate);
        verify(contactRepository, times(1)).save(contactOnBD);
    }

    @Test
    @DisplayName("Should throw exception ID NOT FOUND")
    void updateContactByIdCase2() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();
        Person person = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                120,
                contacts);

        Contact contactUpdate = new Contact(
                UUID.fromString(id),
                "Joao",
                "5555-5555",
                "vitor@mail.com",
                person
        );

        when(contactRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> {
            contactService.updateContact(UUID.fromString(id), contactUpdate);
        });


    }

    @Test
    @DisplayName("Should delete person successfully")
    void deleteContact() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";

        contactService.deleteContact(UUID.fromString(id));
        verify(contactRepository).deleteById(UUID.fromString(id));

    }
}