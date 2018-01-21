import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {NgModule} from "@angular/core";

import {LoginComponent} from "./login.components";

@NgModule({
    imports:[CommonModule,FormsModule],
    declarations:[LoginComponent],
    exports:[LoginComponent]
})

export class LoginModule{

}
