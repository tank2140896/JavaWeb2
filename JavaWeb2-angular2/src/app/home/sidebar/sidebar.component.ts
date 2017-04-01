import {Component} from '@angular/core';

@Component({
    selector: 'home-sidebar',
    templateUrl: 'sidebar.html',
    styleUrls: ['sidebar.css']
})

export class SidebarComponent {

    constructor(){
        this.getMenuList(JSON.parse(window.sessionStorage.getItem('menuList')));
    }

    htmlContent:string = '';

    getMenuList(menuList:any):void{
        for(let i=0;i<menuList.length;i++){
            let each = menuList[i];
            if(each.list!=null&&each.list.length>0){
                this.htmlContent += '<div>'+each.modulename+'</div>';
                this.getMenuList(each.list);
            }else{
                this.htmlContent += '<div><a href="#/home/'+each.moduleurl+'">'+each.modulename+'</a></div>';
            }
        }
    }

}
