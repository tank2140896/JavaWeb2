"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Rx_1 = require("rxjs/Rx");
var HttpService = /** @class */ (function () {
    function HttpService(http) {
        this.http = http;
    }
    HttpService.prototype.getJsonData = function (url, headToken) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        if (headToken != null) {
            headers.append('userId', headToken.userId);
            headers.append('token', headToken.token);
        }
        var requestOptions = new http_1.RequestOptions({ headers: headers, withCredentials: true });
        return this.http.get(url, requestOptions).map(function (response) { return response.json(); })["catch"](this.handleError);
    };
    HttpService.prototype.postJsonData = function (url, body, headToken) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        if (headToken != null) {
            headers.append('userId', headToken.userId);
            headers.append('token', headToken.token);
        }
        var requestOptions = new http_1.RequestOptions({ headers: headers, withCredentials: true });
        return this.http.post(url, body, requestOptions).map(function (response) { return response.json(); })["catch"](this.handleError);
    };
    HttpService.prototype.getData = function (url) {
        //this.httpService.getData(url).subscribe((data:Response)=>console.log(data.json()));
        return this.http.get(url).map(function (response) { return response.json(); })["catch"](this.handleError);
    };
    HttpService.prototype.postData = function (url, body, requestOptions) {
        return this.http.post(url, body, requestOptions).map(function (response) { return response.json(); })["catch"](this.handleError);
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