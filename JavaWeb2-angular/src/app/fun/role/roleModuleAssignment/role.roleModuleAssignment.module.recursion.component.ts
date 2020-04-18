import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-web-role-role-module-assignment-module-recursion',
  templateUrl: './role.roleModuleAssignmentModuleRecursion.html',
  styleUrls: ['./role.roleModuleAssignmentModuleRecursion.scss'],
  providers:[]
})

export class RoleModuleAssignmentModuleRecursionComponent implements OnInit {

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
      RoleModuleAssignmentModuleRecursionComponent.idList.push(this.id);
    }
  }

  //勾选事件
  public moduleIdCheckedChange($event,id):void{
    //console.log($event.target.checked,',',id);
    //console.log('变化前：',RoleModuleAssignmentModuleRecursionComponent.idList);
    if($event.target.checked){
      if(RoleModuleAssignmentModuleRecursionComponent.idList.filter(each=>each==id).length==0){//选中且列表中不存在
        RoleModuleAssignmentModuleRecursionComponent.idList.push(id);
      }
    }else{
      if(RoleModuleAssignmentModuleRecursionComponent.idList.filter(each=>each==id).length>0){//取消选中且列表中存在
        const index = RoleModuleAssignmentModuleRecursionComponent.idList.indexOf(id);//找到要删除的元素对应的下标，从0开始
        RoleModuleAssignmentModuleRecursionComponent.idList.splice(index, 1);//splice为从要删除的元素开始，删除一个，刚好就是删除那个元素
      }
    }
    //console.log('变化后：',RoleModuleAssignmentModuleRecursionComponent.idList);
  }

}
