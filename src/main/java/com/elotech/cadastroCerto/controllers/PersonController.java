package com.elotech.cadastroCerto.controllers;

import com.elotech.cadastroCerto.domains.Person;
import com.elotech.cadastroCerto.dto.PersonPostRequest;
import com.elotech.cadastroCerto.dto.PersonPutRequest;
import com.elotech.cadastroCerto.dto.PersonResponse;
import com.elotech.cadastroCerto.dto.PersonResponsePageable;
import com.elotech.cadastroCerto.exceptions.AttributeMissingException;
import com.elotech.cadastroCerto.services.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.elotech.cadastroCerto.utils.ConversorDateTimeLong.localDateTimeToLong;
import static com.elotech.cadastroCerto.utils.ConversorDateTimeLong.longToLocalDateTime;

@AllArgsConstructor

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public PersonResponsePageable getPersonPaged(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
         PersonResponsePageable page = new PersonResponsePageable();
         Page<Person> pages = personService.getPersonsPaged(pageNo,pageSize);
         List<PersonResponse> personResponses = pages
                 .stream()
                 .map(this::convertToResponseFromEntity)
                 .toList();

         page.setContent(personResponses);
         page.setPageNo(pages.getNumber());
         page.setPageSize(pages.getSize());
         page.setLast(pages.isLast());
         page.setTotalElement(pages.getTotalElements());
         page.setTotalPages(pages.getTotalPages());

         return page;

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponse getPersonById(@PathVariable String id) {
        return convertToResponseFromEntity(
                        personService.getPersonById(UUID.fromString(id)));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> getAllPersons() {
        return personService.getAllPersons()
                .stream()
                .map(this::convertToResponseFromEntity)
                .toList();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePersonById(@PathVariable String id,
                       @Valid @RequestBody PersonPutRequest update) {
        personService.updatePersonById(UUID.fromString(id), convertToEntityFromPutRequest(update));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@Valid @RequestBody PersonPostRequest created, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            StringBuilder errorMessage = new StringBuilder();

            for (ObjectError error : errors) {
                errorMessage.append(error.getDefaultMessage());
            }
            throw new AttributeMissingException(errorMessage.toString());
        }

        return personService.createPerson(convertToEntityFromPostRequest(created));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePersonById(@PathVariable UUID id) {
        personService.deletePerson(id);
    }


    private Person convertToEntityFromPostRequest(PersonPostRequest personPostRequest) {
        Person person = new Person();
        person.setBirthDay(localDateTimeToLong(personPostRequest.getBirthDay()));
        BeanUtils.copyProperties(personPostRequest, person, "birthday");
        return person;
    }

    private Person convertToEntityFromPutRequest(PersonPutRequest personPutRequest) {
        Person person = new Person();
        person.setBirthDay(personPutRequest.getBirthDay());
        person.setName(personPutRequest.getName());
        person.setContacts(personPutRequest.getContacts());
        return person;
    }

    private PersonResponse convertToResponseFromEntity(Person person) {
        PersonResponse foundPerson = new PersonResponse();
        foundPerson.setBirthDay(longToLocalDateTime(person.getBirthDay()));
        BeanUtils.copyProperties(person, foundPerson,"birthday");
        return foundPerson;
    }

}
