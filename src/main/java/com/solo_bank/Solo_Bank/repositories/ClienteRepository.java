package com.solo_bank.Solo_Bank.repositories;

import com.solo_bank.Solo_Bank.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    public Optional<Cliente> findById(Long id);
    public Optional<Cliente> findByCpf(String cpf);
}
