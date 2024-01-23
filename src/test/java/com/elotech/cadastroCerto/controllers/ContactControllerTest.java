package com.elotech.cadastroCerto.controllers;

import com.elotech.cadastroCerto.domains.Contact;
import com.elotech.cadastroCerto.domains.Person;
import com.elotech.cadastroCerto.dto.ContactPostRequest;
import com.elotech.cadastroCerto.dto.ContactPutRequest;
import com.elotech.cadastroCerto.services.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.time.Instant.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ContactController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

    @MockBean
    private ContactService contactService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private Person person;

    private Contact contact;

    private ContactPostRequest contactPostRequest;

    private ContactPutRequest contactPutRequest;

    @BeforeEach
    public void setup(){
        String id = "521f14ef-73ec-40d0-9766-c6bd80b3a695";
        Set<Contact> contacts = new HashSet<>();

        person = new Person(
                UUID.fromString(id),
                "Pedro",
                "39638521783",
                1705994044508L,
                contacts);

        contact = new Contact(
                UUID.fromString(id),
                "Pedro",
                "vitor@gmail.com",
                "1231212312",
                person);

        contactPostRequest = ContactPostRequest.builder()
                .name("Pedro")
                .email("vitor@gmail.com")
                .phoneNumber("1231212312")
                .person(person)
                .build();
    }

    @Test
    void updateContact() {
    }

    @Test
    void createContact() throws Exception {



        given(contactService.createContact(ArgumentMatchers.any())).willAnswer((InvocationOnMock::getArguments));

        ResultActions response = mockMvc.perform(post("/api/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactPostRequest)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());



    }

    @Test
    void deleteContact() {
    }
}