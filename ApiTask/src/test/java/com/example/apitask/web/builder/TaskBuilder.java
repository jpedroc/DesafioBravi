package com.example.apitask.web.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.apitask.dominio.Tarefa;
import com.example.apitask.repositorio.TarefaRepositorio;
import com.example.apitask.servico.Util.Constantes;

import java.time.LocalDate;
import java.util.Collection;

@Component
public class TaskBuilder extends ConstructorEntity<Tarefa> {

    @Autowired
    private TarefaRepositorio tarefaRepositorio;

    @Override
    public Tarefa buildEntity(){
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Cozinhar");
        tarefa.setDescricao("Preparar o almoço e a janta");
        tarefa.setData(LocalDate.now());
        tarefa.setStatus(Constantes.TAREFA_PENDENTE);

        return tarefa;
    }

    @Override
    public Tarefa persist(Tarefa entity) {
        return tarefaRepositorio.save(entity);
    }

    @Override
    public Tarefa findById(Long id) {
        return tarefaRepositorio.findById(id).orElse(null);
    }

    @Override
    public Collection<Tarefa> findAll() {
        return tarefaRepositorio.findAll();
    }
}
