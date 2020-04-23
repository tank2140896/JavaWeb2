import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {InterfacesDataPermissionAssignmentComponent} from './interfaces.dataPermissionAssignment.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[InterfacesDataPermissionAssignmentComponent],
    exports:[InterfacesDataPermissionAssignmentComponent]
})

export class InterfacesDataPermissionAssignmentModule {

}
