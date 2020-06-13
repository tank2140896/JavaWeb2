import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {InterfacesComponent} from './interfaces.component';
import {InterfacesListModule} from './list/interfaces.list.module';
import {InterfacesDetailModule} from './detail/interfaces.detail.module';
import {InterfacesModifyModule} from './modify/interfaces.modify.module';
import {InterfacesDataPermissionAssignmentModule} from './dataPermissionAssignment/interfaces.dataPermissionAssignment.module';
import {InterfacesTestModule} from './test/interfaces.test.module';

@NgModule({
  imports:[
    CommonModule,RouterModule,
    InterfacesListModule,InterfacesDetailModule,InterfacesModifyModule,InterfacesDataPermissionAssignmentModule,InterfacesTestModule
  ],
  declarations:[InterfacesComponent],
  exports:[InterfacesComponent]
})

export class InterfacesModule {

}
