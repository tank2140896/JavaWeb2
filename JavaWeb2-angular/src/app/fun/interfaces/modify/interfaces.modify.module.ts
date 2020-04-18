import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {InterfacesModifyComponent} from './interfaces.modify.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[InterfacesModifyComponent],
    exports:[InterfacesModifyComponent]
})

export class InterfacesModifyModule {

}
