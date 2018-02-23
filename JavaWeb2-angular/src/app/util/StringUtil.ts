import {UUID} from 'angular2-uuid';

export class StringUtil{

    public static getUuid():string{
        return UUID.UUID();
    }

}
