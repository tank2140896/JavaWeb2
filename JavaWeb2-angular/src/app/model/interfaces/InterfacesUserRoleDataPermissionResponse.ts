export class InterfacesUserRoleDataPermissionResponse {

  interfacesId:string;//接口ID

  userName:string;//用户名

  roleName:string;//角色名

  type:number;//1：用户列表获取；2：角色列表获取

  currentPage = 1;//当前页数

  pageSize = 3;//每页显示多少条

}
