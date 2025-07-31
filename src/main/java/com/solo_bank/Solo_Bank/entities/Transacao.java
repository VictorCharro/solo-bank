package com.solo_bank.Solo_Bank.entities;

import com.solo_bank.Solo_Bank.enums.TipoTransacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Conta pagador;
    private Conta recebedor;
    private List<Transacao> transacoes;
    private TipoTransacao tipo;
    private BigDecimal valor;
}
