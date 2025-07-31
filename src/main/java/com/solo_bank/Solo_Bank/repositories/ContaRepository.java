package com.solo_bank.Solo_Bank.repositories;

import com.solo_bank.Solo_Bank.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository <Conta, Long> {
}
