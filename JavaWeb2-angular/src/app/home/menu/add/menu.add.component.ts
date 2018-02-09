import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {MenuAdd} from "../../../models/menu/menu.add";

@Component({
    selector: 'menu-add',
    templateUrl: './menu.add.html',
    styleUrls: ['./menu.add.scss']
})

export class MenuAddComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){
        this.moduleList = sessionService.getSessionData().moduleList;
        this.menuList = sessionService.getSessionData().menuList;
        this.authOperateList = sessionService.getSessionData().authOperateList;
    }

    private showList:any;//显示的列表
    private moduleList:any;//模块列表
    private menuList:any;//菜单列表
    private authOperateList:any;//操作列表


    //初始化
    ngOnInit(): void {
        this.showList = this.moduleList;
    }

    public moduleTypeList:any = [
        {'moduleTypeKey':'0','moduleTypeValue':'未定义'},
        {'moduleTypeKey':'1','moduleTypeValue':'菜单'},
        {'moduleTypeKey':'2','moduleTypeValue':'操作'},
    ];//初始化模块类型下拉列表
    private menuAdd:MenuAdd = new MenuAdd();//模块新增请求参数

    //重置
    public reset():void{
        this.showList = this.moduleList;
        this.menuAdd = new MenuAdd();
    }

    //取消
    public cancel():void{
        this.router.navigate(['../list'],{relativeTo: this.activatedRoute});
    }

    //保存
    public save():void{
        this.httpService.postJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_MODULE_ADD,true),JSON.stringify(this.menuAdd),this.sessionService.getHeadToken()).subscribe(
            result=>{
                if(result.code==200){
                    this.cancel();
                }else{
                    this.router.navigate(['login']);
                }
            },
            error=>{
                this.router.navigate(['login']);
            }
        );
    }

    //控制图标框是否能填
    private disableStyle:boolean = true;
    //模块类型切换
    public moduleTypeChange():void{
        let getModuleType = this.menuAdd.moduleType;//模块类型(0:未定义模块类型；1：菜单；2：操作)
        if(getModuleType=='0'){//未定义模块类型
            this.menuAdd.icon = '';
            this.disableStyle = true;
            this.showList = this.moduleList;
            this.menuAdd.parentId = undefined;
        }else if(getModuleType=='1'){//菜单
            this.disableStyle = false;
            this.showList = this.menuList;
        }else if(getModuleType=='2'){//操作
            this.menuAdd.icon = '';
            this.disableStyle = true;
            this.showList = this.authOperateList;
        }
    }

}
