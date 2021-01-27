package com.example.apitask.servico.impl;

import com.example.apitask.repositorio.TarefaRepositorio;
import com.example.apitask.servico.Exception.MyException;
import com.example.apitask.servico.TarefaServico;
import com.example.apitask.servico.Util.Constantes;
import com.example.apitask.servico.dto.TarefaDTO;
import com.example.apitask.servico.dto.TarefaListagemDTO;
import com.example.apitask.servico.mapper.TarefaListagemMapper;
import com.example.apitask.servico.mapper.TarefaMapper;
import com.example.apitask.dominio.Tarefa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TarefaServicoImpl implements TarefaServico {

    private final TarefaListagemMapper tarefaListagemMapper;
    private final TarefaMapper tarefaMapper;
    private final TarefaRepositorio tarefaRepositorio;

    @Override
    public List<TarefaListagemDTO> listar() {
        return  tarefaListagemMapper.toDto(tarefaRepositorio.findAll());
    }

    @Override
    public TarefaListagemDTO salvar(TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefaRepositorio.save(tarefa);
        return tarefaListagemMapper.toDto(tarefa);
    }

    @Override
    public void deletar(Long id) {
        Tarefa tarefa = tarefaRepositorio.findById(id).orElseThrow(() -> new MyException("Tarefa não encontrada"));
        if(tarefa.getStatus().equals(Constantes.TAREFA_CONCLUIDA)) {
            throw new MyException("Tarefas concluídas não podem ser excluídas.");
        }
        tarefaRepositorio.delete(tarefa);
    }
}
