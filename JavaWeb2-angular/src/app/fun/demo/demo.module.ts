import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {DemoComponent} from './demo.components';
import {Demo1Module} from './demo1/demo1.module';
import {Demo2Module} from './demo2/demo2.module';
import {DemoListModule} from './demoList/demoList.module';


@NgModule({
    imports:[
      CommonModule,RouterModule,FormsModule,
      DemoListModule,Demo1Module,Demo2Module
    ],
    declarations:[DemoComponent],
    exports:[DemoComponent]
})

export class DemoModule{

}
