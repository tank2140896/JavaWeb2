"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var HttpRequestUrl_1 = require("../../../constant/HttpRequestUrl");
var common_constant_1 = require("../../../constant/common.constant");
var online_chat_1 = require("../../../models/chat/online.chat");
var ChatComponent = /** @class */ (function () {
    function ChatComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.messageShow = '';
        this.onlineChat = new online_chat_1.OnlineChat();
    }
    //初始化
    ChatComponent.prototype.ngOnInit = function () {
        this.webSocketInit();
    };
    //发送
    ChatComponent.prototype.send = function () {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.OTHER_ONLINE_CHAT, true), JSON.stringify(this.onlineChat), this.sessionService.getHeadToken()).subscribe(function (result) {
            var getResult = result;
            if (getResult.code == 200) {
                _this.onlineChat.message = common_constant_1.CommonConstant.EMPTY;
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    ChatComponent.prototype.webSocketInit = function () {
        var _this = this;
        var headToken = this.sessionService.getHeadToken();
        var chatInitUrl = HttpRequestUrl_1.HttpRequestUrl.HTTP_REQUEST_PREFIX.replace("http", "ws") + "/websocket/" + (headToken.userId + "," + headToken.type);
        this.websocket = new WebSocket(chatInitUrl);
        //连接成功后接收到的服务器返回信息的处理
        this.websocket.onopen = function (e) {
        };
        //从服务器端接收到的信息
        this.websocket.onmessage = function (e) {
            var data = JSON.parse(e.data);
            if (data.userId == headToken.userId) {
                _this.messageShow += ('我说：' + data.message + '\n');
            }
            else {
                _this.messageShow += (data.userName + '说：' + data.message + '\n');
            }
        };
        //错误异常
        this.websocket.onerror = function (e) {
            _this.websocket.close();
        };
        //关闭
        this.websocket.onclose = function (e) {
            _this.websocket.close();
        };
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
    };
    ChatComponent = __decorate([
        core_1.Component({
            selector: 'chat',
            templateUrl: './chat.html',
            styleUrls: ['./chat.scss']
        })
    ], ChatComponent);
    return ChatComponent;
}());
exports.ChatComponent = ChatComponent;
//# sourceMappingURL=chat.component.js.map