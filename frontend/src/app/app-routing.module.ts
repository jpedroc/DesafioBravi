import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TaskFormComponent} from './task/task-form/task-form.component';

const routes: Routes = [
  {
    path: '',
    component: TaskFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
