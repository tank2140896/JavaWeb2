import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {CommonConstant} from "../../../constant/common.constant";
import {OnlineChat} from "../../../models/chat/online.chat";
import {HeadToken} from "../../../models/token/head.token";

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
        this.webSocketInit();
    }

    private websocket:any;
    private messageShow:any='';
    private onlineChat:OnlineChat = new OnlineChat();

    //发送
    public send():void{
        this.httpService.postJsonData(HttpRequestUrl.getPath(HttpRequestUrl.OTHER_ONLINE_CHAT,true),JSON.stringify(this.onlineChat),this.sessionService.getHeadToken()).subscribe(
            result=>{
                let getResult = (<any>result);
                if(getResult.code==200){
                    this.onlineChat.message = CommonConstant.EMPTY;
                }else{
                    this.router.navigate(['login']);
                }
            },
            error=>{
                this.router.navigate(['login']);
            }
        );
    }

    public webSocketInit():void{
        let headToken:HeadToken = this.sessionService.getHeadToken();
        let chatInitUrl = HttpRequestUrl.HTTP_REQUEST_PREFIX.replace("http","ws")+"/websocket/"+(headToken.userId+","+headToken.type);
        this.websocket = new WebSocket(chatInitUrl);
        //连接成功后接收到的服务器返回信息的处理
        this.websocket.onopen = (e) => {

        }
        //从服务器端接收到的信息
        this.websocket.onmessage = (e) => {
            let data = JSON.parse(e.data);
            if(data.userId==headToken.userId){
                this.messageShow += ('我说：'+data.message+'\n');
            }else{
                this.messageShow += (data.userName+'说：'+data.message+'\n');
            }
        }
        //错误异常
        this.websocket.onerror = (e) => {
            this.websocket.close();
        }
        //关闭
        this.websocket.onclose = (e) => {
            this.websocket.close();
        }
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常
        //window.onbeforeunload = function () {
        //    this.websocket.close();
        //};

        /**
        let headToken:HeadToken = this.sessionService.getHeadToken();
        let chatInitUrl = HttpRequestUrl.HTTP_REQUEST_PREFIX.replace("http","ws")+"/websocket/"+(headToken.userId+","+headToken.type);
        var ws = new WebSocket(chatInitUrl);
        //连接成功后接收到的服务器返回信息的处理
        ws.onopen = function(e){

        };
        //从服务器端接收到的信息
        ws.onmessage = function(e){
            var data = JSON.parse(e.data);
            console.log(data.message);
            console.log(data.userId);
            console.log(data.userName);
            //console.log(e.data);
            //areaMessage+=e.data;
            //document.getElementById('areaMessage').innerHTML = e.data;
        }
        //错误异常
        ws.onerror = function(e){
            ws.close();
        };
        //关闭
        ws.onclose = function(e){
            ws.close();
        };
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常
        window.onbeforeunload = function () {
            ws.close();
        };
        */
    }

}


