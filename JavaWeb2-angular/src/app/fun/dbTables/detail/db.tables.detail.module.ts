import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {DbTablesDetailComponent} from './db.tables.detail.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[DbTablesDetailComponent],
    exports:[DbTablesDetailComponent]
})

export class DbTablesDetailModule {

}
