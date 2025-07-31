package com.solo_bank.Solo_Bank.repositories;

import com.solo_bank.Solo_Bank.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
}
