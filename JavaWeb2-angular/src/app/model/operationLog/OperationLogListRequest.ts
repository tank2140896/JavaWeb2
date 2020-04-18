export class OperationLogListRequest {

  userName:string;//用户名

  requestIpAddress:string;//请求IP地址

  createStartDate:any = null;//操作日志创建的开始日期

  createEndDate:any = null;//操作日志创建的结束日期

  currentPage = 1;//当前页数

  pageSize = 10;//每页显示多少条

}
