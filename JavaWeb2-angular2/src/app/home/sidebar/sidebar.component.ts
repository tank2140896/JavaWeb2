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

    htmlWrite:string = '';//TODO @Input()试下
    
    getMenuList(menuList:any):void{
        for(let i=0;i<menuList.length;i++){
            let each = menuList[i];
            if(each.list!=null&&each.list.length>0){
                this.htmlWrite += '<div>'+each.modulename+'</div>';
                this.getMenuList(each.list);
            }else{
                //TODO 此时routerLink是不起作用的，如果解决，参考：（未完待续）
                //TODO https://angular.io/docs/ts/latest/cookbook/dynamic-component-loader.html
                //TODO http://stackoverflow.com/questions/32340641/angular-2-equivalent-of-ng-bind-html-sce-trustashtml-and-compile
                //TODO http://stackoverflow.com/questions/35556344/angular2-dynamiccomponentloader-with-parameters
                this.htmlWrite += '<div><a href="#" [routerLink]="'+each.moduleurl+'">'+each.modulename+'</a></div>';
            }
        }
    }

}
