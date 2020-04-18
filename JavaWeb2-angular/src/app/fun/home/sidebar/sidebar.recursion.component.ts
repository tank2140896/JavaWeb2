import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-web-home-sidebar-recursion',
  templateUrl: './sidebar.recursion.html',
  styleUrls: ['./sidebar.recursion.scss']
})

export class SidebarRecursionComponent implements OnInit {

  constructor(){

  }

  @Input() icon:string;
  @Input() url:string;
  @Input() name:string;
  @Input() list:any[];

  @Input() showMenu = true;
  addExpandClass():void {
    if (this.showMenu) {
      this.showMenu = false;
    } else {
      this.showMenu = true;
    }
  }

  //初始化
  ngOnInit(): void {

  }

}
