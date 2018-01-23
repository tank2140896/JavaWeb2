export class ResultPage {

    constructor(ret:any){
        this.data = ret.data;
        this.currentPage = ret.currentPage;
        this.totalPage = ret.totalPage;
        this.pageSize = ret.pageSize;
        this.totalSize = ret.totalSize;
        this.pageList = ret.pageList;
    }

    data:any = null;

    currentPage:number;

    totalPage:number;

    pageSize:number;

    totalSize:number;

    pageList:number;

}
