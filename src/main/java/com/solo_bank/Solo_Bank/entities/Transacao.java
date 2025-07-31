package com.solo_bank.Solo_Bank.entities;

import com.solo_bank.Solo_Bank.enums.TipoTransacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conta_pagador_id")
    private Conta pagador;

    @ManyToOne
    @JoinColumn(name = "conta_recebedor_id")
    private Conta recebedor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transacao")
    private TipoTransacao tipo;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_transacao")
    private LocalDateTime dataTransacao = LocalDateTime.now();
}
