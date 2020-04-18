import {CommonConstant} from '../../constant/CommonConstant';

export class UserListRequest {

  userName:string = CommonConstant.EMPTY;//用户名

  personName:string = CommonConstant.EMPTY;//用户姓名

  roleName:string = CommonConstant.EMPTY;//角色名

  createStartDate:any = null;//用户创建的开始日期

  createEndDate:any = null;//用户创建的结束日期

  currentPage = 1;//当前页数

  pageSize = 10;//每页显示多少条

}
