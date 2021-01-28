package com.example.apitask.web;

import com.example.apitask.servico.TarefaServico;
import com.example.apitask.servico.dto.TarefaDTO;
import com.example.apitask.servico.dto.TarefaListagemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
public class TarefaRecurso {

    private final TarefaServico tarefaServico;

    @GetMapping
    public ResponseEntity<List<TarefaListagemDTO>> listar() {
        List<TarefaListagemDTO> tarefas = tarefaServico.listar();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("{id}")
    public ResponseEntity<TarefaDTO> buscarPorId(@PathVariable("id") Long id) {
        TarefaDTO tarefa = tarefaServico.obterPorId(id);
        return ResponseEntity.ok(tarefa);
    }

    @PostMapping
    public ResponseEntity<TarefaListagemDTO> cadastrar(@Valid @RequestBody TarefaDTO tarefaDTO) throws URISyntaxException {
        TarefaListagemDTO tarefa = tarefaServico.salvar(tarefaDTO);
        return ResponseEntity.created(new URI("/tarefas/" + tarefa.getId())).body(tarefa);
    }

    @PutMapping
    public ResponseEntity<TarefaListagemDTO> alterar(@Valid @RequestBody TarefaDTO tarefaDTO) {
        TarefaListagemDTO tarefa = tarefaServico.salvar(tarefaDTO);
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaServico.deletar(id);
        return ResponseEntity.ok(null);
    }

}
