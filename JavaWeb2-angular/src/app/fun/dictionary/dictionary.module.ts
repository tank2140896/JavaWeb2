import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {DictionaryComponent} from './dictionary.component';
import {DictionaryListModule} from './list/dictionary.list.module';
import {DictionaryModifyModule} from './modify/dictionary.modify.module';
import {DictionaryDetailModule} from './detail/dictionary.detail.module';
import {DictionaryAddModule} from './add/dictionary.add.module';

@NgModule({
  imports:[
    CommonModule,RouterModule,
    DictionaryListModule,DictionaryAddModule,DictionaryDetailModule,DictionaryModifyModule
  ],
  declarations:[DictionaryComponent],
  exports:[DictionaryComponent]
})

export class DictionaryModule{

}
