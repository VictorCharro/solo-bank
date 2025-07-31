package com.solo_bank.Solo_Bank.controller;

import com.solo_bank.Solo_Bank.entities.Conta;
import com.solo_bank.Solo_Bank.services.ContaService;
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
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    @PostMapping
    public Conta criarConta(@RequestBody Conta conta) {
        return contaService.criarConta(conta);
    }

    @GetMapping
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

    @GetMapping("/{id}")
    public Optional<Conta> buscarContaPeloId(@PathVariable Long id) {
        return contaService.buscarContaPeloId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarContaPeloId(@PathVariable Long id) {
        contaService.deletarContaPeloId(id);
    }
}
