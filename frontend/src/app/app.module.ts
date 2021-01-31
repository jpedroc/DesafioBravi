import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {TaskModule} from './task/task.module';
import {TaskServiceService} from './services/task-service.service';
import {HttpClientModule} from '@angular/common/http';
import {MessageService} from "primeng-lts/api";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    AppRoutingModule,
    TaskModule,
    HttpClientModule
  ],
  providers: [TaskServiceService, MessageService  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
