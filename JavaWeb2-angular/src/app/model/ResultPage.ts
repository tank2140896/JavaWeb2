import {CommonConstant} from '../constant/CommonConstant';

export class ResultPage {

  constructor(ret?:any){
    if(ret==undefined){
      this.data = CommonConstant.EMPTY;//空表示数据加载中
    }else{
      let getData = ret.data;
      if(getData==null||getData==''||getData.length==0){
        this.data = null;//null表示无数据或数据获取异常
      }else{
        this.data = ret.data;
        this.currentPage = ret.currentPage;
        this.totalPage = ret.totalPage;
        this.pageSize = ret.pageSize;
        this.totalSize = ret.totalSize;
        this.pageList = ret.pageList;
      }
    }
  }

  data:any;//数据

  currentPage:number;//当前页数

  totalPage:number;//总页数

  pageSize:number;//每页显示条数

  totalSize:number;//总条数

  pageList:number;//分页页数

  dataLoading = CommonConstant.EMPTY;

}
