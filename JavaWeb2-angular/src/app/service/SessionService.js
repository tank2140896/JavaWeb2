"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var head_token_1 = require("../models/token/head.token");
var SessionService = /** @class */ (function () {
    function SessionService() {
    }
    SessionService.prototype.setSessionValueBykey = function (key, value) {
        window.sessionStorage.setItem(key, value);
    };
    SessionService.prototype.getSessionValueByKey = function (key) {
        return window.sessionStorage.getItem(key);
    };
    SessionService.prototype.clearSessionValueByKey = function (key) {
        window.sessionStorage.removeItem(key);
    };
    SessionService.prototype.setSessionData = function (sessionData) {
        window.sessionStorage.setItem('sessionData', sessionData);
    };
    SessionService.prototype.getSessionData = function () {
        var getData = window.sessionStorage.getItem('sessionData');
        if (getData == null || getData == '') {
            return null;
        }
        getData = JSON.parse(getData);
        return getData;
    };
    SessionService.prototype.clearSessionData = function () {
        window.sessionStorage.removeItem('sessionData');
    };
    SessionService.prototype.getHeadToken = function () {
        var sessionData = this.getSessionData();
        var headToken = new head_token_1.HeadToken();
        headToken.userId = sessionData.user.userId;
        headToken.token = sessionData.token;
        headToken.type = sessionData.type;
        return headToken;
    };
    SessionService = __decorate([
        core_1.Injectable()
    ], SessionService);
    return SessionService;
}());
exports.SessionService = SessionService;
//# sourceMappingURL=SessionService.js.map