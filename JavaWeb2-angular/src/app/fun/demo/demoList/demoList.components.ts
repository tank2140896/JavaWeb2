import {Component,OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiConstant} from '../../../constant/ApiConstant';

@Component({
  selector: 'app-web-demo-list',
  templateUrl: './demoList.component.html',
  styleUrls: ['./demoList.component.scss']
})

export class DemoListComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute) {

  }

  ngOnInit() {

  }

  public demoBack():void{
    this.router.navigate([ApiConstant.LOGIN],{relativeTo:this.activatedRoute});
  }

}
