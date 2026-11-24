package com.example.ProjetoCRUD.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjetoCRUD.model.Cliente;
import com.example.ProjetoCRUD.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com id: " + id));
    }

    @Transactional
    public Cliente criar(Cliente cliente) {
        validarCamposObrigatorios(cliente);

        String emailNormalizado = cliente.getEmail().trim().toLowerCase();
        String cpfLimpo = cliente.getCpf().replaceAll("\\D", "");

        if (clienteRepository.existsByEmail(emailNormalizado)) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este e-mail.");
        }

        if (clienteRepository.existsByCpf(cpfLimpo)) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }

        cliente.setNome(cliente.getNome().trim());
        cliente.setEmail(emailNormalizado);
        cliente.setCpf(cpfLimpo);

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente dadosAtualizados) {
        Cliente existente = buscarPorId(id);
        validarCamposObrigatorios(dadosAtualizados);

        String emailNormalizado = dadosAtualizados.getEmail().trim().toLowerCase();
        String cpfLimpo = dadosAtualizados.getCpf().replaceAll("\\D", "");

        clienteRepository.findByEmail(emailNormalizado)
                .filter(outro -> !outro.getId().equals(id))
                .ifPresent(outro -> {
                    throw new IllegalArgumentException("Este e-mail já está em uso por outro cliente.");
                });

        existente.setNome(dadosAtualizados.getNome().trim());
        existente.setEmail(emailNormalizado);
        existente.setCpf(cpfLimpo);

        return clienteRepository.save(existente);
    }

    @Transactional
    public void excluir(Long id) {
        Cliente existente = buscarPorId(id);
        clienteRepository.delete(existente);
    }

    private void validarCamposObrigatorios(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new IllegalArgumentException("O e-mail do cliente é obrigatório.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
    }
}