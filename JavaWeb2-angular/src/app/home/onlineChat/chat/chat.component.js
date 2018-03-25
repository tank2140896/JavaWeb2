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
var ChatComponent = /** @class */ (function () {
    function ChatComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.message = common_constant_1.CommonConstant.EMPTY;
    }
    //初始化
    ChatComponent.prototype.ngOnInit = function () {
        this.webSocketObj = this.webSocketInit();
    };
    //发送
    ChatComponent.prototype.send = function () {
        this.webSocketObj.send(this.message);
    };
    ChatComponent.prototype.webSocketInit = function () {
        //let headToken = this.sessionService.getHeadToken();
        var chatUrl = HttpRequestUrl_1.HttpRequestUrl.HTTP_REQUEST_PREFIX.replace("http", "ws") + HttpRequestUrl_1.HttpRequestUrl.OTHER_ONLINE_CHAT;
        var ws = new WebSocket(chatUrl);
        //发送给服务器端的信息
        ws.onopen = function (e) {
            //ws.send(messageInfo);
        };
        //从服务器端接收到的信息
        ws.onmessage = function (e) {
            //var data = JSON.parse(e.data);
            //console.log(data);
            //console.log(e.data);
            //areaMessage+=e.data;
            document.getElementById('areaMessage').innerHTML = e.data;
        };
        //错误异常
        ws.onerror = function (e) {
            console.log(e);
        };
        //关闭
        ws.onclose = function (e) {
            ws.close();
        };
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常
        window.onbeforeunload = function () {
            ws.close();
        };
        return ws;
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