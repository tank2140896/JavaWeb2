import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {Demo1Component} from './demo1.component';


@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[Demo1Component],
    exports:[Demo1Component]
})

export class Demo1Module {

}
