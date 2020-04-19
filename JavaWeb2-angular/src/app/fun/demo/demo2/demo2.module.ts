import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {Demo2Component} from './demo2.component';


@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[Demo2Component],
    exports:[Demo2Component]
})

export class Demo2Module {

}
