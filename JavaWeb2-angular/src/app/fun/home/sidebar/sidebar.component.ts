import {Component,OnInit} from '@angular/core';
import {SessionService} from '../../../service/SessionService';

@Component({
  selector: 'app-web-home-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  private menuListForTree:any;

  constructor(public sessionService:SessionService) {

  }

  ngOnInit(): void {
    let tokenData:any = this.sessionService.getTokenData();
    this.menuListForTree = tokenData.menuListForTree;
    //console.log(this.menuListForTree);
  }

}
