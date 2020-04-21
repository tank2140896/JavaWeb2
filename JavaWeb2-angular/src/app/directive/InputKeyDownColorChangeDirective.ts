import {Directive, HostBinding, HostListener} from '@angular/core';

//请在@NgModule中的declarations中引入InputKeyDownColorChangeDirective
@Directive({
  selector: '[appInputKeyDownColorChangeDirective]'
})
export class InputKeyDownColorChangeDirective {

  private rgbNum:any = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'];

  @HostBinding('style.color') color: string;
  @HostBinding('style.borderColor') borderColor: string;

  @HostListener('keydown') onKeydown(){
    let rgb = '#';
    for(let i=0;i<6;i++){
      let index = Math.floor(Math.random() * this.rgbNum.length);
      rgb += this.rgbNum[index];
    }
    this.color = this.borderColor = rgb;
  }

}
