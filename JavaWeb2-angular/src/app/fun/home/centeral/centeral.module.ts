import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {CenteralComponent} from './centeral.component';

@NgModule({
    imports:[CommonModule,RouterModule],
    declarations:[CenteralComponent],
    exports:[CenteralComponent]
})

export class CenteralModule{

}
