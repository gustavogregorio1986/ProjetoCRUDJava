package com.example.ProjetoCRUD.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjetoCRUD.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Optional<Cliente> findByEmail(String email);
}