import {CommonConstant} from "../../constant/common.constant";

export class UserList {
    userName:string = CommonConstant.EMPTY;//用户名
    personName:string = CommonConstant.EMPTY;//用户姓名
    createStartDate:string =  CommonConstant.EMPTY;//用户创建的开始日期
    createEndDate:string =  CommonConstant.EMPTY;//用户创建的结束日期
    currentPage:number = 1;//当前页数
    pageSize:number = 10;//每页显示多少条
}
