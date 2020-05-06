import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {DbTablesListComponent} from './db.tables.list.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[DbTablesListComponent],
    exports:[DbTablesListComponent]
})

export class DbTablesListModule {

}
