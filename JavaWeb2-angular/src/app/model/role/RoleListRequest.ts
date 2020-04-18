import {CommonConstant} from '../../constant/CommonConstant';

export class RoleListRequest {

  roleName:string = CommonConstant.EMPTY;//角色名

  createStartDate:any = null;//角色创建的开始日期

  createEndDate:any = null;//角色创建的结束日期

  currentPage = 1;//当前页数

  pageSize = 10;//每页显示多少条

}
