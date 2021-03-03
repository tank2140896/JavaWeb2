import { CommonConstant } from "src/app/constant/CommonConstant";

export class FileListRequest {

  fileName = CommonConstant.EMPTY;//文件名称

  currentPage = 1;//当前页数

  pageSize = 10;//每页显示多少条

}
