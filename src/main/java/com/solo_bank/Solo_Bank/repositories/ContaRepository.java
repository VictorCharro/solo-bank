package com.solo_bank.Solo_Bank.repositories;

import com.solo_bank.Solo_Bank.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository <Conta, Long> {

    public Optional<Conta> findByNumeroDaConta(int numeroDaConta);
    public boolean existsByNumeroDaConta(int numeroDaConta);
    public boolean existsByClienteId(Long clienteId);
}
