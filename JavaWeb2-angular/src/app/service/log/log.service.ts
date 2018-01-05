import { Injectable } from '@angular/core';

@Injectable()
export class LogService{

    constructor(){ }

    //输出日志的方法
    writeLog(logMessage:String) : void {
        console.log(logMessage);
    }

}
