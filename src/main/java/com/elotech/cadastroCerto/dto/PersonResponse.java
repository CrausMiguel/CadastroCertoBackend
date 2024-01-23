package com.elotech.cadastroCerto.dto;

import com.elotech.cadastroCerto.domains.Contact;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Builder
public class PersonResponse {

    private UUID id;

    private String name;

    private String cpf;

    private LocalDateTime birthDay;

    private Set<Contact> contacts;
}
