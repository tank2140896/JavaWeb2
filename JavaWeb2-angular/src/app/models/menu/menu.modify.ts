export class MenuModify {
    moduleId:string;//模块ID
    moduleName:string;//模块名称
    pageUrl:string;//页面URL
    apiUrl:string;//API的URL
    parentId:string;//模块的上级ID
    moduleType:string = '0';//模块类型(0:未定义模块类型；1：菜单；2：操作)
    alias:string;//别名
    remark:string;//备注
    icon:string;//图标
}
