import {Component, inject, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-web-user-user-module-assignment-module-recursion',
  templateUrl: './user.userModuleAssignmentModuleRecursion.html',
  styleUrls: ['./user.userModuleAssignmentModuleRecursion.scss'],
  providers:[]
})

export class UserModuleAssignmentModuleRecursionComponent implements OnInit {

  constructor(){

  }

  public static idList:Array<string> = new Array<string>();//若不使用静态变量的话每次递归都会重新实例化数组，而这里不需要重新实例化数组，所以用到了静态变量

  @Input() checkFlag:boolean;
  @Input() id:string;
  @Input() name:string;
  @Input() list:any[];

  //初始化
  ngOnInit(): void {
    //获得所有选中状态
    if(this.checkFlag){
      UserModuleAssignmentModuleRecursionComponent.idList.push(this.id);
    }
  }

  //勾选事件
  public moduleIdCheckedChange($event,id):void{
    //console.log($event.target.checked,',',id);
    //console.log('变化前：',UserModuleAssignmentModuleRecursionComponent.idList);
    if($event.target.checked){
      if(UserModuleAssignmentModuleRecursionComponent.idList.filter(each=>each==id).length==0){//选中且列表中不存在
        UserModuleAssignmentModuleRecursionComponent.idList.push(id);
      }
    }else{
      if(UserModuleAssignmentModuleRecursionComponent.idList.filter(each=>each==id).length>0){//取消选中且列表中存在
        const index = UserModuleAssignmentModuleRecursionComponent.idList.indexOf(id);//找到要删除的元素对应的下标，从0开始
        UserModuleAssignmentModuleRecursionComponent.idList.splice(index, 1);//splice为从要删除的元素开始，删除一个，刚好就是删除那个元素
      }
    }
    //console.log('变化后：',UserModuleAssignmentModuleRecursionComponent.idList);
  }

}
