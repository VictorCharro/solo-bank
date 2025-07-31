package com.solo_bank.Solo_Bank.services;

import com.solo_bank.Solo_Bank.entities.Conta;
import com.solo_bank.Solo_Bank.exceptions.ContaNaoEncontrada;
import com.solo_bank.Solo_Bank.repositories.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    private int criarNumeroDaConta() {
        Random random = new Random();
        int numeroDaConta;

        do {
            numeroDaConta = 1000 + random.nextInt(9000);
        } while (contaRepository.existsByNumeroDaConta(numeroDaConta));
        return numeroDaConta;
    }

    public Conta criarConta(Conta conta){
        conta.setNumeroDaConta(criarNumeroDaConta());
        return contaRepository.save(conta);
    }

    public void deletarConta(Conta conta) {
        contaRepository.delete(conta);
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

}
