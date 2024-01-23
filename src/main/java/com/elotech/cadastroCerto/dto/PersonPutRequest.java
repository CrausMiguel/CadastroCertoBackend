package com.elotech.cadastroCerto.dto;

import com.elotech.cadastroCerto.domains.Contact;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Builder
public class PersonPutRequest {


    @NotBlank(message = "Name must be inserted")
    private String name;

    @NotNull(message = "Birthday must be inserted")
    private long birthDay;

    @NotNull(message = "Must have at least one contact")
    private Set<Contact> contacts;
}
