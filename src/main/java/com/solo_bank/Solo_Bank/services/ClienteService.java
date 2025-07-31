package com.solo_bank.Solo_Bank.services;

import com.solo_bank.Solo_Bank.entities.Cliente;
import com.solo_bank.Solo_Bank.exceptions.ContaExistente;
import com.solo_bank.Solo_Bank.exceptions.ContaNaoEncontrada;
import com.solo_bank.Solo_Bank.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());

        if (clienteExistente.isPresent()) {
            throw new ContaExistente("Cliente com o CPF: " + cliente.getCpf() + " já existe!");
        }

        return clienteRepository.save(cliente);
    }

    public void deletarClientePeloId(Long id) {
        clienteRepository.findById(id)
                        .orElseThrow(() -> new ContaNaoEncontrada("A conta com o ID: " + id + " não foi encontrada!"));

        clienteRepository.deleteById(id);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Cliente cliente) {
        Cliente atualizado = clienteRepository.findByCpf(cliente.getCpf())
                .orElseThrow(()-> new ContaNaoEncontrada("A conta com o CPF: " + cliente.getCpf() + " não foi encontrada"));

        atualizado.setNomeCompleto(cliente.getNomeCompleto());
        return clienteRepository.save(atualizado);
    }
}