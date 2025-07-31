package com.solo_bank.Solo_Bank.entities;

import com.solo_bank.Solo_Bank.enums.TipoConta;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_conta", unique = true)
    private int numeroDaConta;

    @Column(name = "saldo")
    private BigDecimal saldo = BigDecimal.valueOf(0);

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta")
    private TipoConta tipo;

    @OneToOne(mappedBy = "conta")
    private Cliente cliente;
}