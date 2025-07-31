package com.solo_bank.Solo_Bank.entities;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Transacao {

    private Conta pagador;
    private Conta recebedor;
    private List<Transacao> transacoes;
    private BigDecimal valor;
}
