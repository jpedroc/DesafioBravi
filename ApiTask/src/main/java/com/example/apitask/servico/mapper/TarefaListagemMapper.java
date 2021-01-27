package com.example.apitask.servico.mapper;

import com.example.apitask.dominio.Tarefa;
import com.example.apitask.servico.dto.TarefaListagemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaListagemMapper extends EntityMapper<TarefaListagemDTO, Tarefa> {
}
