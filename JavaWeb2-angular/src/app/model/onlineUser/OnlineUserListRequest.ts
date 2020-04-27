import {CommonConstant} from '../../constant/CommonConstant';

export class OnlineUserListRequest {

  userName:string = CommonConstant.EMPTY;//用户名称

  currentPage = 1;//当前页数

  pageSize = 10;//每页显示多少条

}
