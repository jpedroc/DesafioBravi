import { Injectable } from '@angular/core';
import {TaskModel} from '../models/task.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {TaskListModel} from '../models/task-list.model';

@Injectable()
export class TaskServiceService {
  tarefaURL: string;

  constructor(private http: HttpClient) {
    this.tarefaURL = `/api/tarefas/`;
  }

  cadastrar(task: TaskModel): Observable<TaskModel> {
    return this.http.post<TaskModel>(this.tarefaURL, task);
  }

  editar(task: TaskModel): Observable<TaskModel> {
    return this.http.put<TaskModel>(this.tarefaURL, task);
  }

  listar(): Observable<TaskListModel[]> {
    return this.http.get<TaskListModel[]>(this.tarefaURL);
  }

  deletar(id: number): Observable<any> {
    return this.http.delete(this.tarefaURL + id);
  }

  buscarPorId(id: number): Observable<TaskModel> {
    return this.http.get<TaskModel>(this.tarefaURL + id);
  }
}
