package com.solo_bank.Solo_Bank.entities;

import com.solo_bank.Solo_Bank.enums.TipoConta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(unique = true)
    private int numeroDaConta;

    private BigDecimal saldo = BigDecimal.valueOf(0);
    private TipoConta tipo;

    @OneToOne
    Cliente cliente;
}
