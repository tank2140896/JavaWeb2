import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {MenuModify} from "../../../models/menu/menu.modify";

@Component({
    selector: 'menu-modify',
    templateUrl: './menu.modify.html',
    styleUrls: ['./menu.modify.scss']
})

export class MenuModifyComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){
        this.moduleId = activatedRoute.snapshot.queryParams['moduleId'];
        this.moduleList = sessionService.getSessionData().moduleList;
        this.menuList = sessionService.getSessionData().menuList;
        this.authOperateList = sessionService.getSessionData().authOperateList;
    }

    private moduleId:string;//模块ID
    private showList:any;//显示的列表
    private moduleList:any;//模块列表
    private menuList:any;//菜单列表
    private authOperateList:any;//操作列表

    //初始化
    ngOnInit(): void {
        this.menuModify = new MenuModify();
        this.showList = this.moduleList;
        this.detail();//获取模块详细信息
    }

    public moduleTypeList:any = [
        {'moduleTypeKey':'0','moduleTypeValue':'未定义'},
        {'moduleTypeKey':'1','moduleTypeValue':'菜单'},
        {'moduleTypeKey':'2','moduleTypeValue':'操作'},
    ];//初始化模块类型下拉列表
    private menuModify:MenuModify;//模块修改请求参数

    //取消
    public cancel():void{
        this.router.navigate(['../list'],{relativeTo: this.activatedRoute});
    }

    //修改
    public modify():void{
        this.httpService.putJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_MODULE_MODIFY,true),JSON.stringify(this.menuModify),this.sessionService.getHeadToken()).subscribe(
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
    public detail():void{
        this.httpService.getJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_MODULE_DETAIL+'/'+this.moduleId,true),this.sessionService.getHeadToken()).subscribe(
        result=>{
                if(result.code==200){
                    let data = result.data;
                    if(data!=null){
                        this.menuModify = data;
                    }
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
        let getModuleType = this.menuModify.moduleType;//模块类型(0:未定义模块类型；1：菜单；2：操作)
        if(getModuleType=='0'){//未定义模块类型
            this.menuModify.icon = '';
            this.disableStyle = true;
            this.showList = this.moduleList;
            this.menuModify.parentId = undefined;
        }else if(getModuleType=='1'){//菜单
            this.disableStyle = false;
            this.showList = this.menuList;
        }else if(getModuleType=='2'){//操作
            this.menuModify.icon = '';
            this.disableStyle = true;
            this.showList = this.authOperateList;
        }
    }

}
