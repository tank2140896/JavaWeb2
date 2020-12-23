import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FileComponent} from './file.component';
import { FileListModule } from './list/file.list.module';

@NgModule({
  imports:[
    CommonModule,RouterModule,
    FileListModule/**,UserAddModule,UserDetailModule,UserModifyModule,UserRoleAssignmentModule,UserModuleAssignmentModule*/
  ],
  declarations:[FileComponent],
  exports:[FileComponent]
})

export class FileModule{

}
