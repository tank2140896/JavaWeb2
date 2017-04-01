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

    htmlWrite:string = '';

    getMenuList(menuList:any):void{
        for(let i=0;i<menuList.length;i++){
            let each = menuList[i];
            if(each.list!=null&&each.list.length>0){
                this.htmlWrite += '<div>'+each.modulename+'</div>';
                this.getMenuList(each.list);
            }else{
                //http://stackoverflow.com/questions/36325212/angular-2-dynamic-tabs-with-user-click-chosen-components/36325468
                //NgComponentOutlet
                this.htmlWrite += '<div><a href="#" [routerLink]="'+each.moduleurl+'">'+each.modulename+'</a></div>';
            }
        }
    }

}
