package com.elotech.cadastroCerto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Builder
public class ContactPutRequest {

    @NotBlank(message = "Name must be inserted")
    private String name;

    @NotBlank(message = "Phone Number must be inserted")
    private String phoneNumber;

    @Email(message = "Email must be valid")
    private String email;

}
