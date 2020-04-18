import {CommonConstant} from '../../constant/CommonConstant';

export class DictionaryListRequest {

  dataType:string;//数据类型

  keyCode:string;//key值

  means:string;//含义

  createStartDate:any = null;//字典创建的开始日期

  createEndDate:any = null;//字典创建的结束日期

  currentPage = 1;//当前页数

  pageSize = 10;//每页显示多少条

}
