import { Component, OnInit } from '@angular/core';
import {MessageService, SelectItem} from 'primeng-lts/api';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TaskServiceService} from '../../services/task-service.service';
import {TaskListModel} from '../../models/task-list.model';

@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent implements OnInit {

  formTask: FormGroup;
  status: SelectItem[] = [{label: "PENDENTE", value:"PENDENTE"}, {label: "CONCLUÍDA", value:"CONCLUÍDA"}];
  tasks: TaskListModel[];

  constructor(
    private formBuilder: FormBuilder,
    private taskService: TaskServiceService,
    private messageService: MessageService) { }

  ngOnInit(): void {
    this.initForm();
    this.listar();
  }

  initForm(): void {
    this.formTask = this.formBuilder.group({
      id: [null],
      titulo: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
      descricao: [null, [Validators.required, Validators.maxLength(400)]],
      data: [null, Validators.required],
      status: [null, Validators.required],
      tempoGasto: [null]
    });
  }

  validForm() {
    if(this.formTask.valid) {
      this.saveForm();
    }
  }

  saveForm() {
    if(this.formTask.controls["id"].value) {
      this.taskService.editar(this.formTask.value).subscribe(
        response => {
          this.messageService.add({severity: "success", detail: "Tarefa edita com sucesso"});
          this.listar();
        },
        erro => {
          this.messageService.add({severity: "error", detail: erro.message});
        }
      );
    }
    else {
      this.taskService.cadastrar(this.formTask.value).subscribe(
        response => {
          this.messageService.add({severity: "success", detail: "Tarefa cadastrada com sucesso"});
          this.listar();
        },
        erro => {
          this.messageService.add({severity: "error", detail: erro.message});
        }
      );
    }
    this.formTask.reset();
  }

  listar() {
    this.taskService.listar().subscribe(
      response => {
        this.tasks = response;
      },
      erro => {
        this.messageService.add({severity: "error", detail: erro.message});
      }
    );
  }

  deletar(id: number) {
    this.taskService.deletar(id).subscribe(
      response => {
        this.messageService.add({severity: "success", detail: "Tarefa deletada com sucesso"});
        this.listar();
      },
      erro => {
        this.messageService.add({severity: "error", detail: erro.message});
      }
    );
  }

  editar(id: number) {
    this.taskService.buscarPorId(id).subscribe(
      response => {
        this.formTask.patchValue(
          {id: response.id,
            titulo: response.titulo,
            descricao: response.descricao,
            status: response.status,
            data: response.data,
            tempoGasto: response.tempoGasto
          });
      },
      erro => {
        this.messageService.add({severity: "error", detail: erro.message});
      }
    )
  }

  isPendente() {
    return this.formTask.controls['status'].value == "CONCLUÍDA";
  }

}
