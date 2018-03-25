import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {ChatModule} from "./chat/chat.module";
import {OnlineChatComponent} from "./onlineChat.component";

@NgModule({
    imports:[CommonModule,RouterModule,ChatModule],
    declarations:[OnlineChatComponent],
    exports:[OnlineChatComponent]
})

export class OnlineChatModule{

}
