package com.example.apitask.servico.mapper;

import com.example.apitask.servico.dto.TarefaDTO;
import com.example.apitask.dominio.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa> {
}
