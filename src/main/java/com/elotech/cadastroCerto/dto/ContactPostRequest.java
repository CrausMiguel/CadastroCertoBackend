package com.elotech.cadastroCerto.dto;

import com.elotech.cadastroCerto.domains.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContactPostRequest {

    @NotBlank(message = "Name must be inserted")
    private String name;

    @NotBlank(message = "Phone Number must be inserted")
    private String phoneNumber;

    @Email(message = "Email must be valid")
    private String email;

    @NotNull
    private Person person;
}
