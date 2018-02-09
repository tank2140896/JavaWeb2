import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {NgbDatepickerI18n, NgbModal} from "@ng-bootstrap/ng-bootstrap";

import {HttpService} from "../../../service/HttpService";
import {AuthService} from "../../../service/AuthService";
import {SessionService} from "../../../service/SessionService";
import {DatepickerI18nService} from "../../../service/DatepickerI18nService";

import {ResultPage} from "../../../models/result/result.page";
import {MenuList} from "../../../models/menu/menu.list";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {DateUtil} from "../../../util/DateUtil";

@Component({
    selector: 'menu-list',
    templateUrl: './menu.list.html',
    styleUrls: ['./menu.list.scss'],
    providers:[/*I18n,*/{provide:NgbDatepickerI18n,useClass:DatepickerI18nService}]
})

export class MenuListComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private ngbModal:NgbModal,
                private httpService:HttpService,
                private authService:AuthService,
                private sessionService:SessionService){
        //this.menuListZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_MODULE_LIST,false));
        this.menuDeleteZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_MODULE_DELETE,false));
        this.menuAddZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_MODULE_ADD,false));

    }

    /** 操作权限 start */
    //menuListZone:boolean;//模块列表
    menuDeleteZone:boolean;//删除模块
    menuAddZone:boolean;//新增模块
    /** 操作权限 end */

    public moduleTypeList:any = [
        {'moduleTypeKey':'0','moduleTypeValue':'所有'},
        {'moduleTypeKey':'1','moduleTypeValue':'菜单'},
        {'moduleTypeKey':'2','moduleTypeValue':'操作'},
    ];//初始化模块类型下拉列表
    private menuList:MenuList = new MenuList();//模块列表搜索条件
    private resultPage:ResultPage = new ResultPage();//分页结果初始化

    //初始化
    ngOnInit(): void {
        /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
         this.menuList.currentPage = 1;
         this.menuList.pageSize = 5;
         */
        this.menuListFunction(this.menuList);//初始化模块列表
    }

    //搜索按钮
    public menuSearch(currentPage):void{
        this.resultPage = new ResultPage();//对每次搜索进行初始化
        this.menuList.currentPage = currentPage;
        /** start 针对日期插件的特殊处理 */
        let createStartDate = this.menuList.createStartDate;
        let createEndDate = this.menuList.createEndDate;
        if(createStartDate!=null&&createStartDate!=''){
            this.menuList.createStartDate = DateUtil.formatDate(createStartDate);
        }
        if(createEndDate!=null&&createEndDate!=''){
            this.menuList.createEndDate = DateUtil.formatDate(createEndDate);
        }
        /** end 针对日期插件的特殊处理 */
        this.menuListFunction(this.menuList);
        /** start 针对日期插件的特殊处理 */
        this.menuList.createStartDate = createStartDate;
        this.menuList.createEndDate = createEndDate;
        /** end 针对日期插件的特殊处理 */
    }

    //重置
    public reset():void{
        this.menuList = new MenuList();
    }

    //模块搜索共通方法
    private menuListFunction(menuList:MenuList):void {
        this.httpService.postJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_MODULE_LIST,true),JSON.stringify(menuList),this.sessionService.getHeadToken()).subscribe(
            result=>{
                if(result.code==200){
                    let ret = result.data;
                    //console.log(ret);
                    this.resultPage = new ResultPage(ret);
                }else{
                    this.router.navigate(['login']);
                }
            },
            error=>{
                this.router.navigate(['login']);
            }
        );
    }

    //删除模块
    public deleteMenu(moduleId:string,content):void{
        this.ngbModal.open(content).result.then((result) => {
            if(result){
                this.httpService.deleteData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_MODULE_DELETE+'/'+moduleId,true),this.sessionService.getHeadToken()).subscribe(
                    result=>{
                        //删除即使失败这里也暂不做任何处理
                        /** 删除成功重新刷新列表 */
                        this.resultPage = new ResultPage();
                        this.menuListFunction(this.menuList);
                    },
                    error=>{
                        this.router.navigate(['login']);
                    }
                );
            }
        }, (reason) => {
            //主要是ModalDismissReasons.ESC和ModalDismissReasons.BACKDROP_CLICK
        });
    }

    //新增模块
    public addMenu():void{
        this.router.navigate(['../add'],{relativeTo:this.activatedRoute});
    }

}
