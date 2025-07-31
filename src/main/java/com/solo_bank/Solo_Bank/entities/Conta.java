package com.solo_bank.Solo_Bank.entities;

import com.solo_bank.Solo_Bank.enums.TipoConta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final int numeroDaConta;
    private BigDecimal saldo;
    private TipoConta tipo;
}
