package com.elotech.cadastroCerto.services;

import com.elotech.cadastroCerto.domains.Person;
import com.elotech.cadastroCerto.exceptions.CpfAlreadyExistException;
import com.elotech.cadastroCerto.exceptions.NotFoundException;
import com.elotech.cadastroCerto.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    @Override
    public Person createPerson(Person newPerson) {
        if(getPersonByCpf(newPerson.getCpf())){
            throw new CpfAlreadyExistException("CPF already signup");
        }
        return personRepository.save(newPerson);
    }

    @Override
    public Page<Person> getPersonsPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return personRepository.findAll(pageable);
    }

    @Override
    public Person getPersonById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID not found!"));
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public void updatePersonById(UUID id, Person update) {
        Person existingEntity = getPersonById(id);
        existingEntity.setBirthDay(update.getBirthDay());
        existingEntity.setName(update.getName());
        existingEntity.setContacts(update.getContacts());
        personRepository.save(existingEntity);
    }

    @Override
    public boolean getPersonByCpf(String cpf){
        return personRepository.findPersonByCpf(cpf).isPresent();
    }

    @Override
    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }
}
