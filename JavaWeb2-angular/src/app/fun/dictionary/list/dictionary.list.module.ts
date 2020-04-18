import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {DictionaryListComponent} from './dictionary.list.component';


@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
  declarations:[DictionaryListComponent],
  exports:[DictionaryListComponent]
})

export class DictionaryListModule {

}
