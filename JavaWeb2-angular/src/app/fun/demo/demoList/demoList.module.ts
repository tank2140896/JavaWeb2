import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {DemoListComponent} from './demoList.components';

@NgModule({
    imports:[CommonModule,RouterModule,FormsModule],
    declarations:[DemoListComponent],
    exports:[DemoListComponent]
})

export class DemoListModule{

}
