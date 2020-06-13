import {Routes,RouterModule} from '@angular/router';
import {LoginComponent} from './fun/login/login.components';
import {AuthService} from './service/AuthService';
import {HomeComponent} from './fun/home/home.component';
import {CenteralComponent} from './fun/home/centeral/centeral.component';
import {HeaderComponent} from './fun/home/header/header.component';
import {UserListComponent} from './fun/user/list/user.list.component';
import {UserComponent} from './fun/user/user.component';
import {UserAddComponent} from './fun/user/add/user.add.component';
import {UserDetailComponent} from './fun/user/detail/user.detail.component';
import {UserModifyComponent} from './fun/user/modify/user.modify.component';
import {UserRoleAssignmentComponent} from './fun/user/userRoleAssignment/user.userRoleAssignment.component';
import {UserModuleAssignmentComponent} from './fun/user/userModuleAssignment/user.userModuleAssignment.component';
import {RoleComponent} from './fun/role/role.component';
import {RoleListComponent} from './fun/role/list/role.list.component';
import {RoleAddComponent} from './fun/role/add/role.add.component';
import {RoleDetailComponent} from './fun/role/detail/role.detail.component';
import {RoleModifyComponent} from './fun/role/modify/role.modify.component';
import {RoleModuleAssignmentComponent} from './fun/role/roleModuleAssignment/role.roleModuleAssignment.component';
import {ModuleComponent} from './fun/module/module.component';
import {ModuleListComponent} from './fun/module/list/module.list.component';
import {ModuleAddComponent} from './fun/module/add/module.add.component';
import {ModuleModifyComponent} from './fun/module/modify/module.modify.component';
import {ModuleDetailComponent} from './fun/module/detail/module.detail.component';
import {DictionaryComponent} from './fun/dictionary/dictionary.component';
import {DictionaryListComponent} from './fun/dictionary/list/dictionary.list.component';
import {DictionaryAddComponent} from './fun/dictionary/add/dictionary.add.component';
import {DictionaryModifyComponent} from './fun/dictionary/modify/dictionary.modify.component';
import {DictionaryDetailComponent} from './fun/dictionary/detail/dictionary.detail.component';
import {OperationLogComponent} from './fun/operationLog/operation.log.component';
import {OperationLogListComponent} from './fun/operationLog/list/operation.log.list.component';
import {ScheduleComponent} from './fun/schedule/schedule.component';
import {ScheduleListComponent} from './fun/schedule/list/schedule.list.component';
import {InterfacesComponent} from './fun/interfaces/interfaces.component';
import {InterfacesListComponent} from './fun/interfaces/list/interfaces.list.component';
import {InterfacesModifyComponent} from './fun/interfaces/modify/interfaces.modify.component';
import {InterfacesDetailComponent} from './fun/interfaces/detail/interfaces.detail.component';
import {OnlineChatComponent} from './fun/onlineChat/online.chat.component';
import {DemoComponent} from './fun/demo/demo.components';
import {InterfacesDataPermissionAssignmentComponent} from './fun/interfaces/dataPermissionAssignment/interfaces.dataPermissionAssignment.component';
import {OnlineUserComponent} from './fun/onlineUser/online.user.component';
import {DbTablesComponent} from './fun/dbTables/db.tables.component';
import {DbTablesListComponent} from './fun/dbTables/list/db.tables.list.component';
import {DbTablesDetailComponent} from './fun/dbTables/detail/db.tables.detail.component';
import {InterfacesTestComponent} from './fun/interfaces/test/interfaces.test.component';

const routes: Routes = [
  {path:'',component:LoginComponent},//默认登录页面
  {path:'webLogin',component:LoginComponent},//登录页面
  {path:'demo',component:DemoComponent,children:[
    {path:'',component:DemoComponent},//示例列表页面
    {path:'**',redirectTo:'/demo',pathMatch:'full'}
  ]},
  {path:'web',component:HomeComponent,children:[
    {path:'',component:CenteralComponent,canActivate:[AuthService]},
    {path:'loginAccess/logout',component:HeaderComponent},//用户登出接口
    {path:'sys/user',component:UserComponent,children:[
      {path:'',component:UserListComponent,canActivate:[AuthService]},//默认用户列表页面
      {path:'list',component:UserListComponent,canActivate:[AuthService]},//用户列表页面
      {path:'add',component:UserAddComponent,canActivate:[AuthService]},//新增用户页面
      {path:'detail',component:UserDetailComponent,canActivate:[AuthService]},//用户详情页面
      {path:'modify',component:UserModifyComponent,canActivate:[AuthService]},//用户修改页面
      {path:'userRoleAssignment',component:UserRoleAssignmentComponent,canActivate:[AuthService]},//用户角色分配页面
      {path:'userModuleAssignment',component:UserModuleAssignmentComponent,canActivate:[AuthService]},//用户模块分配页面
      {path:'**',redirectTo:'/web',pathMatch:'full'}
    ],canActivate:[AuthService]},
    {path:'sys/role',component:RoleComponent,children:[
      {path:'',component:RoleListComponent,canActivate:[AuthService]},//默认角色列表页面
      {path:'list',component:RoleListComponent,canActivate:[AuthService]},//角色列表页面
      {path:'add',component:RoleAddComponent,canActivate:[AuthService]},//新增角色页面
      {path:'detail',component:RoleDetailComponent,canActivate:[AuthService]},//角色详情页面
      {path:'modify',component:RoleModifyComponent,canActivate:[AuthService]},//修改角色页面
      {path:'roleModuleAssignment',component:RoleModuleAssignmentComponent,canActivate:[AuthService]},//角色模块分配页面
      {path:'**',redirectTo:'/web',pathMatch:'full'}
    ],canActivate:[AuthService]},
    {path:'sys/module',component:ModuleComponent,children:[
      {path:'',component:ModuleListComponent,canActivate:[AuthService]},//模块列表页面
      {path:'list',component:ModuleListComponent,canActivate:[AuthService]},//模块列表页面
      {path:'add',component:ModuleAddComponent,canActivate:[AuthService]},//新增模块页面
      {path:'modify',component:ModuleModifyComponent,canActivate:[AuthService]},//修改模块页面
      {path:'detail',component:ModuleDetailComponent,canActivate:[AuthService]},//模块详情页面
      {path:'**',redirectTo:'/web',pathMatch:'full'}
    ],canActivate:[AuthService]},
    {path:'sys/dictionary',component:DictionaryComponent,children:[
      {path:'',component:DictionaryListComponent,canActivate:[AuthService]},//字典列表页面
      {path:'list',component:DictionaryListComponent,canActivate:[AuthService]},//字典列表页面
      {path:'add',component:DictionaryAddComponent,canActivate:[AuthService]},//新增字典页面
      {path:'modify',component:DictionaryModifyComponent,canActivate:[AuthService]},//修改字典页面
      {path:'detail',component:DictionaryDetailComponent,canActivate:[AuthService]},//字典详情页面
      {path:'**',redirectTo:'/web',pathMatch:'full'}
    ],canActivate:[AuthService]},
    {path:'sys/operationLog',component:OperationLogComponent,children:[
      {path:'',component:OperationLogListComponent,canActivate:[AuthService]},//操作日志列表页面
      {path:'list',component:OperationLogListComponent,canActivate:[AuthService]},//操作日志列表页面
      {path:'**',redirectTo:'/web',pathMatch:'full'}
    ],canActivate:[AuthService]},
    {path:'sys/schedule',component:ScheduleComponent,children:[
      {path:'',component:ScheduleListComponent},//操作日志列表页面
      {path:'list',component:ScheduleListComponent},//操作日志列表页面
      {path:'**',redirectTo:'/web',pathMatch:'full'}
    ],canActivate:[AuthService]},
    {path:'sys/interfaces',component:InterfacesComponent,children:[
      {path:'',component:InterfacesListComponent},//接口列表页面
      {path:'list',component:InterfacesListComponent},//接口列表页面
      {path:'modify',component:InterfacesModifyComponent},//接口修改页面
      {path:'detail',component:InterfacesDetailComponent},//接口详情页面
      {path:'dataPermissionAssignment',component:InterfacesDataPermissionAssignmentComponent},//数据权限分配页面
      {path:'test',component:InterfacesTestComponent},//接口测试页面
      {path:'**',redirectTo:'/web',pathMatch:'full'}
    ],canActivate:[AuthService]},
    {path:'sys/onlineChat',component:OnlineChatComponent,children:[
      {path:'',component:OnlineChatComponent,canActivate:[AuthService]},//在线聊天室页面
      {path:'**',redirectTo:'/web',pathMatch:'full'}
    ],canActivate:[AuthService]},
    {path:'sys/onlineUser',component:OnlineUserComponent,children:[
      {path:'',component:OnlineUserComponent,canActivate:[AuthService]},//在线用户列表
      {path:'list',component:OnlineUserComponent,canActivate:[AuthService]},//在线用户列表
      {path:'**',redirectTo:'/web',pathMatch:'full'}
    ],canActivate:[AuthService]},
    {path:'sys/dbTables',component:DbTablesComponent,children:[
      {path:'',component:DbTablesListComponent,canActivate:[AuthService]},//数据库表列表
      {path:'list',component:DbTablesListComponent,canActivate:[AuthService]},//数据库表列表
      {path:'detail',component:DbTablesDetailComponent,canActivate:[AuthService]},//数据库表详情
      {path:'**',redirectTo:'/web',pathMatch:'full'}
    ],canActivate:[AuthService]},
    {path:'**',redirectTo: '/web', pathMatch: 'full'}
  ],canActivate:[AuthService]},
  {path:'**',redirectTo: '/webLogin', pathMatch: 'full'}//访问任何不存在的URL都将跳回登录页面
];

export const AppRoutingModule = RouterModule.forRoot(routes,{useHash:true});
