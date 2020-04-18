import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {DictionaryDetailComponent} from './dictionary.detail.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[DictionaryDetailComponent],
    exports:[DictionaryDetailComponent]
})

export class DictionaryDetailModule {

}
