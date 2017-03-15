"use strict";
require("core-js");
require("zone.js");
var platform_browser_dynamic_1 = require("@angular/platform-browser-dynamic");
//import {enableProdMode} from '@angular/core';
var app_module_1 = require("./app.module");
//enableProdMode();//生产环境
platform_browser_dynamic_1.platformBrowserDynamic().bootstrapModule(app_module_1.AppModule).catch(function (err) { return console.error(err); });
//# sourceMappingURL=main.js.map