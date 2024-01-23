package com.elotech.cadastroCerto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Builder
public class PersonPostRequest {


    @NotBlank(message = "Name must be inserted")
    private String name;

    @CPF(message = "CPF must be valid")
    private String cpf;

    @NotNull(message = "Birthday must be inserted")
    private LocalDateTime birthDay;

}
