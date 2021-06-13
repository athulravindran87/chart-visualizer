import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FileUploadComponent} from './file-upload/file-upload.component';
import {GanttUiComponent} from './gantt-ui/gantt-ui.component';

const routes: Routes = [

  {path: '', component: FileUploadComponent},
{path: 'gantt', component: GanttUiComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
