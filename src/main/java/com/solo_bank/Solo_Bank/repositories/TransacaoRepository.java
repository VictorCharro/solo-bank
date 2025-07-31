package com.solo_bank.Solo_Bank.repositories;

import com.solo_bank.Solo_Bank.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository <Transacao, Long> {

}
