import {CommonConstant} from "../../constant/common.constant";

export class MenuList {
    moduleName:string = CommonConstant.EMPTY;//模块名称
    pageUrl:string;//页面URL
    apiUrl:string;//API请求URL
    moduleType:string = '0';//模块类型
    alias:string;//别名
    createStartDate:any = null;//模块创建的开始日期
    createEndDate:any = null;//模块创建的结束日期
    currentPage:number = 1;//当前页数
    pageSize:number = 10;//每页显示多少条
}
