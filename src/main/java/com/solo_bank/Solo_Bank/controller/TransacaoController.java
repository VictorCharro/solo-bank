package com.solo_bank.Solo_Bank.controller;

import com.solo_bank.Solo_Bank.entities.Transacao;
import com.solo_bank.Solo_Bank.services.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public Transacao criarTransacao(@RequestBody Transacao transacao) {
        return transacaoService.criarTransacao(transacao);
    }

    @GetMapping
    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTransacoes();
    }

    @GetMapping("/{id}")
    public Optional<Transacao> buscarTransacaoPorId(@PathVariable Long id) {
        return transacaoService.buscarTransacaoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarTransacao(@PathVariable Long id) {
        transacaoService.deletarTransacao(id);
    }
}
