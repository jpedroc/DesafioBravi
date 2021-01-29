package com.example.apitask.web;

import com.example.apitask.TaskAPIApplication;
import com.example.apitask.dominio.Tarefa;
import com.example.apitask.servico.Util.Constantes;
import com.example.apitask.servico.dto.TarefaDTO;
import com.example.apitask.servico.mapper.TarefaMapper;
import com.example.apitask.util.BaseIntTest;
import com.example.apitask.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.example.apitask.web.builder.TaskBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = {TaskAPIApplication.class})
public class TarefaRecursoTest extends BaseIntTest {

    private static final String RESOURCE = "/api/tarefas";

    @Autowired
    private TaskBuilder taskBuilder;

    @Autowired
    private TarefaMapper tarefaMapper;

    @Before
    public void setup() {
        taskBuilder.setCustomize(null);
    }

    @Test
    public void criarTest() throws Exception {
        TarefaDTO tarefaDTO = tarefaMapper.toDto(taskBuilder.buildEntity());
        getMockMvc().perform(post(RESOURCE)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tarefaDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void listarTest() throws Exception {
        taskBuilder.buildAndSave();

        getMockMvc().perform(get(RESOURCE)
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void alterarTest() throws Exception {
        Tarefa tarefa = taskBuilder.buildEntity();
        tarefa.setDescricao("Devo fazer só a janta");


        getMockMvc().perform(put(RESOURCE)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tarefaMapper.toDto(tarefa))))
                .andExpect(status().isOk());
    }

    @Test
    public void buscarPorIdTest() throws Exception {
        Tarefa tarefa = taskBuilder.buildAndSave();

        getMockMvc().perform(get(RESOURCE + "/" + tarefa.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value(tarefa.getTitulo()))
                .andExpect(jsonPath("$.descricao").value(tarefa.getDescricao()));
    }

    @Test
    public void buscarPorIdNotFoundTest() throws Exception {
        getMockMvc().perform(get(RESOURCE + "/0")
        .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(header().stringValues("Tarefa não encontrada"));
    }

    @Test
    public void deletarTest() throws Exception {
        Tarefa tarefa = taskBuilder.buildAndSave();

        getMockMvc().perform(delete(RESOURCE + "/" + tarefa.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarTarefaConcluidaTest() throws Exception {
        Tarefa tarefa = taskBuilder.customize(entity -> entity.setStatus(Constantes.TAREFA_CONCLUIDA)).buildAndSave();

        getMockMvc().perform(delete(RESOURCE + "/" + tarefa.getId())
        .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(header().stringValues("Tarefas concluídas não podem ser excluídas."));
    }
}
