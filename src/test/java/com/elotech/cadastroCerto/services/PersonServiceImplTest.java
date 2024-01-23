package com.elotech.cadastroCerto.services;

import com.elotech.cadastroCerto.domains.Contact;
import com.elotech.cadastroCerto.domains.Person;
import com.elotech.cadastroCerto.exceptions.CpfAlreadyExistException;
import com.elotech.cadastroCerto.exceptions.NotFoundException;
import com.elotech.cadastroCerto.repositories.PersonRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;


    @Test
    @DisplayName("Should create Person successfully")
    void createPersonCase1() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();
        Person newPerson = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                120,
                contacts);


        when(personRepository.save(newPerson)).thenReturn(newPerson);
        personService.createPerson(newPerson);
        verify(personRepository, times(1)).save(newPerson);

    }

    @Test
    @DisplayName("Should throw Exception when Person has an existing CPF on DB")
    void createPersonCase2() {

        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();
        Person newPerson = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                120,
                contacts);

        when(personRepository.findPersonByCpf(newPerson.getCpf())).thenReturn(Optional.of(newPerson));

        Assertions.assertThrows(CpfAlreadyExistException.class, () -> {
            personService.createPerson(newPerson);
        });



    }

    @Test
    @DisplayName("Should get Person successfully")
    void getPersonByIdCase1() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();
        Person newPerson = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                120,
                contacts);

        when(personRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(newPerson));

        Person foundPerson = personService.getPersonById(UUID.fromString(id));

        assertEquals(newPerson, foundPerson);

    }


    @Test
    @DisplayName("Should throw exception by ID NOT FOUND")
    void getPersonByIdCase2() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";

        when(personRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> {
            personService.getPersonById(UUID.fromString(id));
        });



    }

    @Test
    @DisplayName("Should update successfully")
    void updatePersonByIdCase1() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();
        Person personOnDB = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                120,
                contacts);
        Person personUpdate = new Person(
                UUID.fromString(id),
                "Joao",
                "39638521783",
                120,
                contacts);

        when(personRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(personOnDB));

        personService.updatePersonById(UUID.fromString(id), personUpdate);

        verify(personRepository, times(1)).save(personOnDB);

    }

    @Test
    @DisplayName("Should throw exception ID NOT FOUND")
    void updatePersonByIdCase2() {

        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();
        Person personUpdate = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                120,
                contacts);

        when(personRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> {
            personService.updatePersonById(UUID.fromString(id), personUpdate);
        });


    }

    @Test
    @DisplayName("Should found person by cpf successfully")
    void getPersonByCpf() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();
        Person newPerson = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                120,
                contacts);

        when(personRepository.findPersonByCpf("39638521783")).thenReturn(Optional.of(newPerson));
        boolean cpfFound = personService.getPersonByCpf("39638521783");
        assertTrue(cpfFound);

    }



    @Test
    @DisplayName("Should delete person successfully")
    void deletePerson() {
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";

        personService.deletePerson(UUID.fromString(id));
        verify(personRepository).deleteById(UUID.fromString(id));
    }

}