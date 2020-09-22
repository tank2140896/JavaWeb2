package com.javaweb.config.qrcode;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.javaweb.base.BaseInject;

@RestController
public class QrCodeController extends BaseInject {
	
    @GetMapping("/getQrCode")
    public void getQrCode(String code,HttpServletResponse response) throws Exception{
    	QRCodeWriter qrCodeWriter = new QRCodeWriter();
		String url = "https://www.???.???.com";
		//System.out.println(url);
		BitMatrix bitMatrix = qrCodeWriter.encode(url,BarcodeFormat.QR_CODE,180,180);
		MatrixToImageWriter.writeToStream(bitMatrix,"jpg",response.getOutputStream());
    }

}
