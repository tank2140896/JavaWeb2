import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {ModuleAddComponent} from './module.add.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[ModuleAddComponent],
    exports:[ModuleAddComponent]
})

export class ModuleAddModule {

}
