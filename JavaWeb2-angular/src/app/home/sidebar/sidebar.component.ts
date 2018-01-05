import {Component} from '@angular/core';
import {SessionService} from "../../service/session/SessionService";

@Component({
    selector: 'home-sidebar',
    templateUrl: 'sidebar.html',
    styleUrls: ['sidebar.css']
})

export class SidebarComponent {

    menuList;

    constructor(private sessionService:SessionService/*private domSanitizer:DomSanitizer*/){
        //获取sidebar菜单列表
        this.menuList = sessionService.getLoginSuccessData().getMenuList();
        //this.htmlWrite=this.domSanitizer.bypassSecurityTrustHtml(this.htmlContent);
    }

    //htmlWrite:SafeHtml;

    /**
    htmlContent:string = '';
    level:String='';
    getMenuList(menuList:any):void{
        for(let i=0;i<menuList.length;i++){
            let each = menuList[i];
            if(each.list!=null&&each.list.length>0){
                this.htmlContent += '<div>'+this.level+each.moduleName+'</div>';
                this.level+="——";
                this.getMenuList(each.list);
                this.level = this.level.substring(0,this.level.length-2);
            }else{
                this.htmlContent += '<div>'+this.level+'<a href="#/home/'+each.pageUrl+'">'+each.moduleName+'</a></div>';
            }
        }
    }
    */

}