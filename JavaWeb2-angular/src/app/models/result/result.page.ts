import {CommonConstant} from "../../constant/common.constant";

export class ResultPage {

    constructor(ret?:any){
        if(ret==undefined){
            this.data = CommonConstant.DATA_LOADING;
        }else{
            let getData = ret.data;
            if(getData==null||getData==''||getData.length==0){
                this.data = null;
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

    data:any;

    currentPage:number;

    totalPage:number;

    pageSize:number;

    totalSize:number;

    pageList:number;

    dataLoading:string = CommonConstant.DATA_LOADING;

}
