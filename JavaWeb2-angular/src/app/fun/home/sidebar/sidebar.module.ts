import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {SidebarComponent} from './sidebar.component';
import {SidebarRecursionComponent} from './sidebar.recursion.component';

@NgModule({
  imports:[CommonModule,RouterModule],
  declarations:[SidebarComponent,SidebarRecursionComponent],
  exports:[SidebarComponent]
})

export class SidebarModule{

}
