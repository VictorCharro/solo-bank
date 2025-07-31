package com.solo_bank.Solo_Bank.services;

import com.solo_bank.Solo_Bank.entities.Conta;
import com.solo_bank.Solo_Bank.entities.Transacao;
import com.solo_bank.Solo_Bank.enums.TipoTransacao;
import com.solo_bank.Solo_Bank.exceptions.ContaNaoEncontrada;
import com.solo_bank.Solo_Bank.exceptions.SaldoInsuficiente;
import com.solo_bank.Solo_Bank.exceptions.ValorNegativo;
import com.solo_bank.Solo_Bank.repositories.ContaRepository;
import com.solo_bank.Solo_Bank.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;

    public List<Transacao> listarTransacoes() {
        return transacaoRepository.findAll();
    }

    @Transactional
    public Transacao criarTransacao(Transacao transacao) {
        TipoTransacao tipo = transacao.getTipo();
        Conta pagador = transacao.getPagador();
        Conta recebedor = transacao.getRecebedor();

        switch (tipo) {

            case DEPOSITO:
                recebedor = contaRepository.findByNumeroDaConta(transacao.getRecebedor().getNumeroDaConta())
                        .orElseThrow(() -> new ContaNaoEncontrada("A conta de numero: " + transacao.getRecebedor().getNumeroDaConta() + " não foi encontrada!"));

                if (transacao.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new ValorNegativo("O valor a ser depositado deverá ser maior que zero");
                }
                recebedor.setSaldo(recebedor.getSaldo().add(transacao.getValor()));
                contaRepository.save(recebedor);
                break;

            case SAQUE:
                if (transacao.getValor().compareTo(pagador.getSaldo()) >= 0) {
                    throw new SaldoInsuficiente("O saldo atual é insuficiente para realizar a transação!");
                }
                pagador.setSaldo(pagador.getSaldo().subtract(transacao.getValor()));
                contaRepository.save(pagador);
                break;

            case TRANSFERENCIA:
                recebedor = contaRepository.findByNumeroDaConta(transacao.getRecebedor().getNumeroDaConta())
                        .orElseThrow(() -> new ContaNaoEncontrada("A conta de numero: " + transacao.getRecebedor().getNumeroDaConta() + " não foi encontrada!"));

                pagador = contaRepository.findByNumeroDaConta(transacao.getPagador().getNumeroDaConta())
                        .orElseThrow(() -> new ContaNaoEncontrada("A conta de numero: " + transacao.getPagador().getNumeroDaConta() + " não foi encontrada!"));

                if (transacao.getValor().compareTo(pagador.getSaldo()) >= 0) {
                    throw new SaldoInsuficiente("O saldo atual é insuficiente para realizar a transação!");
                }

                pagador.setSaldo(pagador.getSaldo().subtract(transacao.getValor()));
                contaRepository.save(pagador);

                recebedor.setSaldo(recebedor.getSaldo().add(transacao.getValor()));
                contaRepository.save(recebedor);
                break;

        }

        return transacaoRepository.save(transacao);
    }

    public Optional<Transacao> buscarTransacaoPorId (Long id) {
        return transacaoRepository.findById(id);
    }

    public void deletarTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }
}
