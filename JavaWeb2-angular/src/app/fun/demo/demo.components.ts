import {Component,OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpService} from '../../service/HttpService';
import {AuthService} from '../../service/AuthService';
import {DemoEntityComponent} from './demo.entity.component';

@Component({
  selector: 'app-web-demo',
  templateUrl: './demo.html',
  styleUrls: ['./demo.scss'],
  providers:[]
})

export class DemoComponent implements OnInit {

  private demo1_1:string;
  private demo1_2:any[];
  private demo1_3:string;
  private demo1_6:DemoEntityComponent = new DemoEntityComponent('张三',30);
  private demo1_8 = 'red';
  private demo1_10 = '我是[(ngModel)]';

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService){

  }

  ngOnInit(): void {
    this.demo1_1 = '{{内容}}';
    this.demo1_2 = ['a',1,'b',2,'c',3];
    this.demo1_3 = '';
  }

  public demo1_4(v1,v2):void {
    alert(`v1=${v1.value},v2=${v2.value}`)
  }

  //用于接收子组件的数据
  public wordsClick($event):void {
    console.log($event);
  }

  public demo1_7():boolean {
    return true;
  }

}
