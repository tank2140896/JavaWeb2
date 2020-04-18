import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {DictionaryAddComponent} from './dictionary.add.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[DictionaryAddComponent],
    exports:[DictionaryAddComponent]
})

export class DictionaryAddModule {

}
