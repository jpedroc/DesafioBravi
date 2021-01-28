package com.example.apitask.repositorio;

import com.example.apitask.dominio.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepositorio extends JpaRepository<Tarefa,Long> {
}
