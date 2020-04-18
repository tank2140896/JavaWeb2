import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {ModuleModifyComponent} from './module.modify.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[ModuleModifyComponent],
    exports:[ModuleModifyComponent]
})

export class ModuleModifyModule {

}
