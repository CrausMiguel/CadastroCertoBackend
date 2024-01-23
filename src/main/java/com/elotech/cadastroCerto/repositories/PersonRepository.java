package com.elotech.cadastroCerto.repositories;

import com.elotech.cadastroCerto.domains.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    @Query
    public Optional<Person> findPersonByCpf(String cpf);
}
