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
var HttpService = /** @class */ (function () {
    function HttpService(httpClient) {
        this.httpClient = httpClient;
    }
    HttpService.prototype.getJsonData = function (url, headToken) {
        var headers = new http_1.HttpHeaders();
        headers.set('Content-Type', 'application/json');
        if (headToken != null) {
            headers.set('userId', headToken.userId);
            headers.set('token', headToken.token);
        }
        var options = { headers: headers, withCredentials: true };
        return this.httpClient.get(url, options).map(function (data) { return data; })["do"](this.handleError);
    };
    HttpService.prototype.postJsonData = function (url, body, headToken) {
        var headers = new http_1.HttpHeaders();
        headers.set('Content-Type', 'application/json');
        if (headToken != null) {
            headers.set('userId', headToken.userId);
            headers.set('token', headToken.token);
        }
        var options = { headers: headers, withCredentials: true };
        return this.httpClient.post(url, body, options).map(function (data) { return data; })["do"](this.handleError);
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