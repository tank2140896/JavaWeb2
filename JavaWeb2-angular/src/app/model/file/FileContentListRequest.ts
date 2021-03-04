import { CommonConstant } from "src/app/constant/CommonConstant";

export class FileContentListRequest {

  folderPath = CommonConstant.EMPTY;//文件目录路径

  currentPage = 1;//当前页数

  pageSize = 10;//每页显示多少条

}
