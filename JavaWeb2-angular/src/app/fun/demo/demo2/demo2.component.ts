import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';


@Component({
  selector: 'app-web-demo-demo2',
  templateUrl: './demo2.html',
  styleUrls: ['./demo2.scss'],
  providers:[]
})

export class Demo2Component implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService){

  }

  ngOnInit(): void {

  }

}
