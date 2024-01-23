package com.elotech.cadastroCerto.services;

import com.elotech.cadastroCerto.domains.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface PersonService {

    Page<Person> getPersonsPaged(int pageNo, int PageSize);
    Person getPersonById(UUID id);
    List<Person> getAllPersons();

    boolean getPersonByCpf(String cpf);

    @Transactional
    void updatePersonById(UUID id, Person update);

    @Transactional
    Person createPerson(Person newPerson);

    @Transactional
    void deletePerson(UUID id);
}
