import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {DemoComponent} from './demo.components';
import {InputKeyDownColorChangeDirective} from '../../directive/InputKeyDownColorChangeDirective';
import {DemoChildComponent} from './demo.child.component';
import {FormsModule} from '@angular/forms';

@NgModule({
    imports:[CommonModule,RouterModule,FormsModule],
    declarations:[DemoComponent,DemoChildComponent,InputKeyDownColorChangeDirective],
    exports:[DemoComponent]
})

export class DemoModule{

}
