import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {InterfacesTestComponent} from './interfaces.test.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[InterfacesTestComponent],
    exports:[InterfacesTestComponent]
})

export class InterfacesTestModule {

}
