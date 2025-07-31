package com.solo_bank.Solo_Bank.services;

import com.solo_bank.Solo_Bank.entities.Cliente;
import com.solo_bank.Solo_Bank.exceptions.ContaExistente;
import com.solo_bank.Solo_Bank.exceptions.ContaNaoEncontrada;
import com.solo_bank.Solo_Bank.repositories.ClienteRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        Cliente procurado = clienteRepository.findByCpf(cliente.getCpf())
                .orElseThrow(() -> new ContaExistente("A Conta com o CPF: " + cliente.getCpf() + " já existe!"));

        return clienteRepository.save(cliente);
    }

    public void deletarCliente(Cliente cliente) {
        Cliente procurado = clienteRepository.findByCpf(cliente.getCpf())
                .orElseThrow(()-> new ContaNaoEncontrada("A conta com o CPF: " + cliente.getCpf() + " não foi encontrada"));

        clienteRepository.delete(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente atualizarCliente(Cliente cliente) {
        Cliente atualizado = clienteRepository.findByCpf(cliente.getCpf())
                .orElseThrow(()-> new ContaNaoEncontrada("A conta com o CPF: " + cliente.getCpf() + " não foi encontrada"));

        atualizado.setNomeCompleto(cliente.getNomeCompleto());
        return atualizado;
    }
}