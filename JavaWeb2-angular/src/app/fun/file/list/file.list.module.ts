import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {FileListComponent} from './file.list.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
  declarations:[FileListComponent],
  exports:[FileListComponent]
})

export class FileListModule {

}
