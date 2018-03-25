import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {RoleAdd} from "../../../models/role/role.add";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {CommonConstant} from "../../../constant/common.constant";

@Component({
    selector: 'chat',
    templateUrl: './chat.html',
    styleUrls: ['./chat.scss']
})

export class ChatComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){

    }

    //初始化
    ngOnInit(): void {
        this.webSocketObj = this.webSocketInit();
    }

    private webSocketObj:any;
    private message:String = CommonConstant.EMPTY;

    //发送
    public send():void{
        this.webSocketObj.send(this.message);
    }

    public webSocketInit():any{
        //let headToken = this.sessionService.getHeadToken();
        let chatUrl = HttpRequestUrl.HTTP_REQUEST_PREFIX.replace("http","ws")+HttpRequestUrl.OTHER_ONLINE_CHAT;
        var ws = new WebSocket(chatUrl);
        //发送给服务器端的信息
        ws.onopen = function(e){
            //ws.send(messageInfo);
        };
        //从服务器端接收到的信息
        ws.onmessage = function(e){
            //var data = JSON.parse(e.data);
            //console.log(data);
            //console.log(e.data);
            //areaMessage+=e.data;
            document.getElementById('areaMessage').innerHTML = e.data;
        }
        //错误异常
        ws.onerror = function(e){
            console.log(e);
        };
        //关闭
        ws.onclose = function(e){
            ws.close();
        };
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常
        window.onbeforeunload = function () {
            ws.close();
        };
        return ws;
    }

}


