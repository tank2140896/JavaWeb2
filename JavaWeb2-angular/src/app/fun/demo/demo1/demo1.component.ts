import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';


@Component({
  selector: 'app-web-demo-demo1',
  templateUrl: './demo1.html',
  styleUrls: ['./demo1.scss'],
  providers:[]
})

export class Demo1Component implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService){

  }

  ngOnInit(): void {

  }

}
