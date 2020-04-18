import {CommonConstant} from '../../constant/CommonConstant';

export class ModuleListRequest {

  moduleName:string = CommonConstant.EMPTY;//模块名

  pageUrl:string = CommonConstant.EMPTY;//页面URL

  apiUrl:string = CommonConstant.EMPTY;//API请求URL

  moduleType:string = CommonConstant.EMPTY;//模块类型

  createStartDate:any = null;//模块创建的开始日期

  createEndDate:any = null;//模块创建的结束日期

  currentPage = 1;//当前页数

  pageSize = 10;//每页显示多少条

}
