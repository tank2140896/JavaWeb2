import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";

import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {MenuDetail} from "../../../models/menu/menu.detail";

@Component({
    selector: 'menu-detail',
    templateUrl: './menu.detail.html',
    styleUrls: ['./menu.detail.scss']
})

export class MenuDetailComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){
        this.moduleId = activatedRoute.snapshot.queryParams['moduleId'];
        this.moduleList = sessionService.getSessionData().moduleList;
    }

    private moduleId:string;//模块ID
    private showList:any;//显示的列表
    private moduleList:any;//模块列表

    //初始化
    ngOnInit(): void {
        this.menuDetail = new MenuDetail();
        this.showList = this.moduleList;
        this.detail();//获取模块详细信息
    }

    public moduleTypeList:any = [
        {'moduleTypeKey':'0','moduleTypeValue':'未定义'},
        {'moduleTypeKey':'1','moduleTypeValue':'菜单'},
        {'moduleTypeKey':'2','moduleTypeValue':'操作'},
    ];//初始化模块类型下拉列表
    private menuDetail:MenuDetail;//模块详情请求参数

    //返回
    public back():void{
        this.router.navigate(['../list'],{relativeTo: this.activatedRoute});
    }

    //详情
    public detail():void{
        this.httpService.getJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_MODULE_DETAIL+'/'+this.moduleId,true),this.sessionService.getHeadToken()).subscribe(
        result=>{
                if(result.code==200){
                    let data = result.data;
                    if(data!=null){
                        this.menuDetail = data;
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

}
