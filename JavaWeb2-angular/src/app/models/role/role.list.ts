import {CommonConstant} from "../../constant/common.constant";

export class RoleList {
    roleName:string = CommonConstant.EMPTY;//角色名
    createStartDate:any = null;//角色创建的开始日期
    createEndDate:any = null;//角色创建的结束日期
    currentPage:number = 1;//当前页数
    pageSize:number = 10;//每页显示多少条
}
