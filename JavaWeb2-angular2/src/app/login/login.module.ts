import {CommonModule} from "@angular/common";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {NgModule} from "@angular/core";

import {LoginComponent} from "./login.components";

@NgModule({
    imports:[CommonModule, HttpModule, FormsModule],
    declarations:[LoginComponent],
    exports:[LoginComponent]
})

export class LoginModule{

}
