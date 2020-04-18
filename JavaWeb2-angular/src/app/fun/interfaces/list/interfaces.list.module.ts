import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {InterfacesListComponent} from './interfaces.list.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
  declarations:[InterfacesListComponent],
  exports:[InterfacesListComponent]
})

export class InterfacesListModule {

}
