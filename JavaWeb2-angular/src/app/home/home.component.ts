import {Component} from '@angular/core';

@Component({
    selector: 'app-home',
    templateUrl: 'home.html',
    styleUrls: ['home.css']
})

export class HomeComponent {

    constructor(){
        /**
        https://www.npmjs.com/package/@types/ws
        npm install @types/ws
        import {Server} from 'ws';
        const websocketServe = new Server({port:8181});
        websocketServe.on("connection", websocket => {
            websocket.send('xxx');
        })
        */
    }

}