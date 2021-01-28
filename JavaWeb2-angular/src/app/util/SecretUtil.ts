const jsrsasign = require('jsrsasign');//使用参考：https://www.cnblogs.com/web-chuanfa/p/11096951.html
const crypto = require("crypto-js");//使用参考：https://cryptojs.gitbook.io/docs

export class SecretUtil {

    /** 
    let paramStr = 'abc123';
    let pk = "-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD3XSdz1MnzazBEN5KOfTx0IyVJZ5wb57isrCuHDhnYXwtmdhQalgII0fozeeFpMpAvlnmHC1kpW7XVGvZnLx3bWbCEbf+pMSW4kmQuI+5cxRUJbCl7sdaODBrINgERHPICVC18AJLThEVMHyjuR6Jn4zQmyYNbReSktY/BrFTvMQIDAQAB-----END PUBLIC KEY-----";
    var pub = jsrsasign.KEYUTIL.getKey(pk);
    var enc = jsrsasign.KJUR.crypto.Cipher.encrypt(paramStr,pub);
    console.log(enc);
    */

    public static cryptoAesEncrypt(data:string,secretKey:string):string {
        let iv = SecretUtil.getCryptoIV();
        let key = crypto.enc.Hex.parse(crypto.enc.Utf8.parse(secretKey).toString(crypto.enc.Hex));
        return crypto.AES.encrypt(data,key,{mode:crypto.mode.ECB,padding:crypto.pad.Pkcs7,iv:iv}).toString();
    }

    public static cryptoAesDecrypt(secretData:string,secretKey:string):string {
        let iv = SecretUtil.getCryptoIV();
        let key = crypto.enc.Hex.parse(crypto.enc.Utf8.parse(secretKey).toString(crypto.enc.Hex));
        return crypto.AES.decrypt(secretData,key,{mode:crypto.mode.ECB,padding:crypto.pad.Pkcs7,iv:iv}).toString(crypto.enc.Utf8);
    }

    //适配JAVA的[AES/ECB/PKCS5Padding]模式
    public static getCryptoIV():any {
        let out = '';
        let tmpArray = [1,2,3,4,5,6,7,8];
        for(let i=0;i<tmpArray.length;i++) {
            let one = tmpArray[i].toString(2),v = one.match(/^1+?(?=0)/);
            if(v && one.length == 8) {
                let bytesLength = v[0].length;
                let store = tmpArray[i].toString(2).slice(7 - bytesLength);
                for(let st=1;st<bytesLength;st++) {
                    store += tmpArray[st+i].toString(2).slice(2);
                }
                out += String.fromCharCode(parseInt(store,2));
                i += bytesLength-1;
            } else {
                out += String.fromCharCode(tmpArray[i]);
            }
        }
        return crypto.enc.Latin1.parse(out);
    }

}