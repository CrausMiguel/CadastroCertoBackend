package com.elotech.cadastroCerto.controllers;

import com.elotech.cadastroCerto.domains.Contact;
import com.elotech.cadastroCerto.dto.ContactPostRequest;
import com.elotech.cadastroCerto.dto.ContactPutRequest;
import com.elotech.cadastroCerto.exceptions.AttributeMissingException;
import com.elotech.cadastroCerto.services.ContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContactController {
    
    private final ContactService contactService;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(@PathVariable String id,
                             @Valid @RequestBody ContactPutRequest contactPutRequest,
                             BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            StringBuilder errorMessage = new StringBuilder();

            for (ObjectError error : errors) {
                errorMessage.append(error.getDefaultMessage());
            }
            throw new AttributeMissingException(errorMessage.toString());
        }
        contactService.updateContact(
                UUID.fromString(id), convertToEntityFromPutRequest(contactPutRequest));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@Valid @RequestBody ContactPostRequest contactPostRequest,
                       BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            StringBuilder errorMessage = new StringBuilder();

            for (ObjectError error : errors) {
                errorMessage.append(error.getDefaultMessage());
            }
            throw new AttributeMissingException(errorMessage.toString());
        }
        return contactService.createContact(
                convertToEntityFromPostRequest(contactPostRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContact(@PathVariable String id) {
        contactService.deleteContact(
                UUID.fromString(id));
    }


    private Contact convertToEntityFromPostRequest( ContactPostRequest contactPostRequest) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactPostRequest, contact);
        return contact;
    }

    private Contact convertToEntityFromPutRequest( ContactPutRequest contactPutRequest) {
        Contact contact = new Contact();
        contact.setEmail(contactPutRequest.getEmail());
        contact.setName(contactPutRequest.getName());
        contact.setPhoneNumber(contactPutRequest.getPhoneNumber());
        return contact;
    }

}
