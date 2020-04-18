import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {DictionaryModifyComponent} from './dictionary.modify.component';


@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[DictionaryModifyComponent],
    exports:[DictionaryModifyComponent]
})

export class DictionaryModifyModule {

}
