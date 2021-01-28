import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskFormComponent } from './task-form/task-form.component';
import {InputTextModule} from 'primeng-lts/inputtext';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ButtonModule} from 'primeng-lts/button';
import {SharedModule} from 'primeng-lts/api';
import {InputTextareaModule} from 'primeng-lts/inputtextarea';
import {CalendarModule} from 'primeng-lts/calendar';
import {DropdownModule} from 'primeng-lts/dropdown';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {TableModule} from 'primeng-lts/table';

@NgModule({
  declarations: [TaskFormComponent],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    InputTextModule,
    ReactiveFormsModule,
    ButtonModule,
    SharedModule,
    InputTextareaModule,
    CalendarModule,
    DropdownModule,
    TableModule
  ]
})
export class TaskModule { }
