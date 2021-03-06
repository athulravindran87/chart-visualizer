import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { NgGanttEditorModule } from 'ng-gantt';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GanttUiComponent } from './gantt-ui/gantt-ui.component';
import { FileUploadComponent } from './file-upload/file-upload.component';


@NgModule({
  declarations: [
    AppComponent,
    GanttUiComponent,
    FileUploadComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgGanttEditorModule,
    HttpClientModule,
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
