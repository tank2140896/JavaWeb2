import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-web-demo-child',
  template: `{{inputValue}}`
})

export class DemoChildComponent implements OnInit {

  constructor(){

  }

  @Input() inputValue;

  @Output() outputValue:EventEmitter<any> = new EventEmitter();

  ngOnInit(): void {
    this.outputValue.subscribe((e:string)=>{
      this.inputValue = e;
    });
    this.outputValue.emit('我是@Input()和@Output，@Output是由子组件发送数据给父组件');//子组件发送数据给父组件
  }

}
