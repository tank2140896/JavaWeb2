import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {ModuleComponent} from './module.component';
import {ModuleListModule} from './list/module.list.module';
import {ModuleAddModule} from './add/module.add.module';
import {ModuleModifyModule} from './modify/module.modify.module';
import {ModuleDetailModule} from './detail/module.detail.module';

@NgModule({
  imports:[
    CommonModule,RouterModule,
    ModuleListModule,ModuleAddModule,ModuleModifyModule,ModuleDetailModule
  ],
  declarations:[ModuleComponent],
  exports:[ModuleComponent]
})

export class ModuleModule{

}
