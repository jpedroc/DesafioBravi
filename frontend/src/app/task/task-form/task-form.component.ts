import { Component, OnInit } from '@angular/core';
import {SelectItem} from 'primeng-lts/api';
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
    private taskService: TaskServiceService) { }

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
          alert("Editou");
        },
        () => {
          alert("Erro");
        }
      );
    }
    else {
      this.taskService.cadastrar(this.formTask.value).subscribe(
        response => {
          alert("Cadastrou");
        },
        () => {
          alert("Erro");
        }
      );
    }
    this.listar();
    this.formTask.reset();
  }

  listar() {
    this.taskService.listar().subscribe(
      response => {
        this.tasks = response;
      },
      () => {
        alert("Erro ao listar");
      }
    );
  }

  deletar(id: number) {
    this.taskService.deletar(id).subscribe(
      response => {
        alert("Deletou");
        this.listar();
      },
      () => {
        alert("Erro ao deletar");
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
      () => {
        alert("Erro ao obter por id");
      }
    )
  }

  isPendente() {
    return this.formTask.controls['status'].value == "CONCLUÍDA";
  }

}
