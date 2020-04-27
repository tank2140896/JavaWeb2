import {CommonConstant} from '../../constant/CommonConstant';

export class OnlineUserListRequest {

  userId:string = CommonConstant.EMPTY;//用户ID

  currentPage = 1;//当前页数

  pageSize = 10;//每页显示多少条

}
