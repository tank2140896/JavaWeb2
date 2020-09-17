package com.javaweb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.util.core.FileUtil;

/**
import java.io.File;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Test {
	
	public static void main(String[] args) throws Exception {
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setRetryHandler(new DefaultHttpRequestRetryHandler(3,false)).build();  
		HttpPost httpPost = new HttpPost("http://client:2002/file/uploadFile");
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        for(int i=1;i<=2;i++){
            multipartEntityBuilder.addBinaryBody("file",new File("D://"+i+".bmp"));
        }
        multipartEntityBuilder.addPart("uuid",new StringBody(UUID.randomUUID().toString(),ContentType.MULTIPART_FORM_DATA));
        httpPost.setEntity(multipartEntityBuilder.build());
        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
            String jsonData = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");  
            System.out.println(jsonData);  
        }else{
            System.out.println(EntityUtils.toString(httpResponse.getEntity(),"UTF-8"));
        }
	}

}
*/
@RestController
@RequestMapping("/file")
public class FileController {
	
	public final String currentFileRootPath = "E:\\";
	
	//可以设置的参数如：单个文件上传大小限制和总文件上传大小限制等
    @PostMapping("/uploadFile")
    public BaseResponseResult uploadFile(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,
    					      @RequestParam(value="token",required=true) String token,
    		                  @RequestParam(value="file",required=true) MultipartFile multipartFile[]) {
    	try{
    		System.out.println("获得的token为："+token);
    		if(multipartFile!=null&&multipartFile.length>0){
        		for(int i=0;i<multipartFile.length;i++){
        			if (multipartFile[i].getBytes().length > 1024 * 1024 *10) {//10MB
                        return new BaseResponseResult(200,"每个文件大小不能超过10MB");
                    }
        		}
        	}
    		List<String> list = new ArrayList<>();
    		if(multipartFile!=null&&multipartFile.length>0){
        		for(int i=0;i<multipartFile.length;i++){
        			String originFileName = multipartFile[i].getOriginalFilename();
        			String newFileName = UUID.randomUUID().toString()+"_"+originFileName.substring(originFileName.lastIndexOf("."),originFileName.length());
        			list.add(newFileName);
        			FileUtil.writeFile(multipartFile[i].getInputStream(),new byte[1024],new File(currentFileRootPath+newFileName));
        		}
        	}
    		return new BaseResponseResult(200,"文件上传成功",list);
    	}catch(Exception e){
    		return new BaseResponseResult(200,"文件上传失败");
    	}
    }
    
    @GetMapping("/downloadFile/{fileName}")
    public void downloadFile(@PathVariable(value="fileName",required=true)String fileName,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
    	try{
			FileUtil.downloadFile(httpServletResponse.getOutputStream(),new byte[1024],new File(currentFileRootPath+fileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
    }

}
