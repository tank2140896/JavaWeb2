import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";

import {ChatComponent} from "./chat.component";

@NgModule({
    imports:[CommonModule,RouterModule,FormsModule],
    declarations:[ChatComponent],
    exports:[ChatComponent]
})

export class ChatModule {

}
