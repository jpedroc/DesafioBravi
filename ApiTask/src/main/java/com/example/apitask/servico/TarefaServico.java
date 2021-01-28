package com.example.apitask.servico;

import com.example.apitask.servico.dto.TarefaDTO;
import com.example.apitask.servico.dto.TarefaListagemDTO;

import java.util.List;

public interface TarefaServico {

    public List<TarefaListagemDTO> listar();

    public TarefaListagemDTO salvar(TarefaDTO tarefaDTO);

    public void deletar(Long id);

    public TarefaDTO obterPorId(Long id);
}
