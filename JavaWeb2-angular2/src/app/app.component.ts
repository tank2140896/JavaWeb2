import {Component} from '@angular/core';

@Component({
  selector: 'web-app',
  templateUrl: 'app/login/login.html',
  styleUrls: ['app/login/login.css']
})

export class AppComponent{

  /*------ 语言切换 start ------*/
  //初始化默认语言
  defaultLangue = {display:'中文',value:'CN'};
  //defaultLangue = {display:'请选择',value:'-1'};//默认空或请选择个人推荐这种写法

  //语言列表显示
  langues:any[] = [
    //{display:'请选择',value:'-1'},//默认空或请选择个人推荐这种写法
    {display:'中文',value:'CN'},
    {display:'英文',value:'EN'}
  ];

  //语言选择事件
  public langueChange(langueChoseEvent:any):void{
    let languesArray = this.langues;
    for(let i in languesArray){
      let each = languesArray[i];
      if(each.value==langueChoseEvent){
        console.log("你选择的语言为："+each.display);
        break;
      }
    }
  }
  /*------ 语言切换 end ------*/

  public login():void{
    console.log(1);
  }

}
