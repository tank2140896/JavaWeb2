一、重要说明：
本项目采用前后端分离方式（开发分离和部署分离），前端采用Angular
二、项目目前结构简述：
1、JavaWeb2-api——主要为前端提供接口
2、JavaWeb2-angular——前端实现（angular、angular-cli、node、typescript）
三、Angular一些知识点个人简记：
1、package.json简要说明
1.1、devDependencies和dependencies的区别
devDependencies在开发中会依赖其一些功能（形如JUnit），但是在部署时不会添加使用进去；
dependencies是项目开发和部署所依赖的（形如node_modules里的文件）
2、个人理解的知识点
2.1、{{表达式}}及private test:String = "abc";{{test | uppercase}}
2.2、[]=>组件到模板；()=>模板到组件
2.3、<div [title="x"]>123</div>
import {Input} from '@angular/core';
@Input() x = 11;
<ul>
    <li *ngFor="let name of names">
        <app-user-item [name]="name"></app-user-item>
    </li>
</ul>
2.4、<img [src]="..."/> <img (click)="..."/> <div [ngClass]="..."></div> <div [ngSubmit]="..."></div>
2.5、component注入service
import service
@Component providers
constructor private service
2.8、URL带参数
path->'home/:id'
import  ActivatedRoute from '@angular/router'
constructor private activatedRoute:ActivatedRoute
activatedRoute.snapshot.queryParams['id']
页面写法：
this.router.navigate(['home']/*,{queryParams:{'myKey':100}}*/);
<a [routerLink]="['home']" [queryParams]="{'myKey':100}" >Home</a>
2.9、路径
方式一：
{path:'sysManager/userManger',component:UserComponent},
{path:'sysManager/roleManger',component:RoleComponent}
方式二：
{path:'sysManager/userManger',component:UserComponent,children:ROUTES_USER},
{path:'sysManager/roleManger',component:RoleComponent,children:ROUTES_ROLE}
方式三：
{
    path:'home',component:HomeComponent,children:[
        ...CenteralRoutes,...UserManageRoutes
    ]
}
方式四：
{path:'home',loadChildren:'app/home/home.module#HomeModule',canActivate:[AuthService]}
2.10、@HostBinding
@HostBinding('disabled') getDisabled(){
    return true;
}
@HostBinding('style.color') getColor(){
    return 'red';
}
2.11、构造方法和初始化方法的执行顺序
先执行构造方法（constructor）后执行初始化方法（ngOnInit）
3、在angular-cli自动生成范围外的内容
3.1、bootstrap
①package.json添加"ng2-bootstrap":"1.4.2"和"bootstrap":"3.3.7"
②.angular-cli.json添加"../node_modules/bootstrap/dist/css/bootstrap.min.css"
3.2、其它js文件
"scripts": [
	"../node_modules/jquery/dist/jquery.js",
        "../node_modules/bootstrap/dist/js/bootstrap.js"
]
declare var $: any;
4、待完善/解决/考虑的问题
4.1、使用第三方npm等考虑到angular-cli的版本、angular自身的版本、nodejs的版本及package.josn中各版本的兼容关系
4.2、多级联动、功能较为丰富的树形层次结构
4.3、table参考：ng-treetable
4.4、样式：NG-ZORRO
4.5、插件：ngxDatePicker

