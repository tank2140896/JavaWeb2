import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {NgModule} from "@angular/core";

import {DemoComponent} from "./demo.components";

@NgModule({
    imports:[CommonModule,FormsModule],
    declarations:[DemoComponent],
    exports:[DemoComponent]
})

export class DemoModule{

}
