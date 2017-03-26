import 'core-js';
import 'zone.js';

import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import {enableProdMode} from '@angular/core';

import {AppModule} from './app.module';

enableProdMode();//生产环境
platformBrowserDynamic().bootstrapModule(AppModule).catch(err => console.error(err));