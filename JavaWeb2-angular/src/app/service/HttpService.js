"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var http_1 = require("@angular/common/http");
var Rx_1 = require("rxjs/Rx");
var head_token_1 = require("../models/token/head.token");
var HttpService = /** @class */ (function () {
    function HttpService(httpClient) {
        this.httpClient = httpClient;
    }
    HttpService.prototype.ngOnInit = function () {
    };
    HttpService.prototype.getOptions = function (headToken) {
        if (headToken == null) {
            headToken = new head_token_1.HeadToken();
            headToken.userId = '';
            headToken.token = '';
        }
        var headers = new http_1.HttpHeaders({
            'Content-Type': 'application/json',
            'Access-Control-Allow-Headers': 'Authorization',
            'userId': headToken.userId,
            'token': headToken.token
        });
        var options = { 'headers': headers, withCredentials: true };
        return options;
    };
    HttpService.prototype.getJsonData = function (url, headToken) {
        return this.httpClient.get(url, this.getOptions(headToken)).map(function (data) { return data; })["do"](this.handleError);
    };
    HttpService.prototype.postJsonData = function (url, body, headToken) {
        return this.httpClient.post(url, body, this.getOptions(headToken)).map(function (data) { return data; })["do"](this.handleError);
    };
    HttpService.prototype.getSingleJsonData = function (url) {
        return this.httpClient.get(url).map(function (data) { return data; })["do"](this.handleError);
    };
    HttpService.prototype.postSingleJsonData = function (url, body, options) {
        return this.httpClient.post(url, body, options).map(function (data) { return data; })["do"](this.handleError);
    };
    HttpService.prototype.handleError = function (error) {
        //console.log(error);
        return Rx_1.Observable["throw"](error);
    };
    HttpService = __decorate([
        core_1.Injectable()
    ], HttpService);
    return HttpService;
}());
exports.HttpService = HttpService;
//# sourceMappingURL=HttpService.js.map