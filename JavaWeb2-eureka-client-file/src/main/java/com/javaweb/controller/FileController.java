package com.javaweb.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.dao.ds1.FileDao;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.FileUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.core.SystemUtil;

/**
import java.io.File;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Test {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setRetryHandler(new DefaultHttpRequestRetryHandler(3,false)).build();  
		HttpPost httpPost = new HttpPost("http://client2:2002/file/uploadFile");
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        for(int i=1;i<=2;i++){
            multipartEntityBuilder.addBinaryBody("file",new File("E:\\\\"+i+".bmp"));
        }
        //multipartEntityBuilder.addPart("uuid",new StringBody(UUID.randomUUID().toString(),ContentType.MULTIPART_FORM_DATA));
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
	
	@Autowired
	private FileDao fileDao;
	
    @PostMapping("/uploadFile")
    public BaseResponseResult uploadFile(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,
    		                  @RequestParam(value="file",required=true) MultipartFile multipartFile[]) {
    	try{
    		//1、上传文件可以根据token进行权限处理，此处省略
    		System.out.println("获得的token为："+httpServletRequest.getHeader(SystemConstant.HEAD_TOKEN));
    		//2、上传文件大小校验，还可以校验总上传文件的大小
    		if(multipartFile!=null&&multipartFile.length>0){
        		for(int i=0;i<multipartFile.length;i++){
        			if (multipartFile[i].getBytes().length > 1024 * 1024 *10) {//10MB
                        return new BaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"每个文件大小不能超过10MB");
                    }
        		}
        	}
    		//3、获得文件根路径
    		String rootPath = CommonConstant.EMPTY_VALUE;
    		String yearMonthDay = DateUtil.getDefaultDate(DateUtil.DATE_PATTERN_TYPE1);//年月日
    		int random = (int)(Math.random()*10);//0-9
    		if(SystemUtil.isLinux()) {//linux路径
                rootPath = "/tmp/file/";//这里可以从数据库或常量类里去读取
                rootPath += (random+"/"+yearMonthDay+"/");
            } else {//windows路径
                rootPath = "E:\\\\file\\\\";//这里可以从数据库或常量类里去读取
                rootPath += (random+"\\\\"+yearMonthDay+"\\\\");
            }
    		FileUtil.makeFolder(new File(rootPath));
    		//4、上传文件并保存相关文件信息到数据库
    		final String fileSerNo = UUID.randomUUID().toString();//文件批次号（同一批次上传的多个文件的批次号应该相同）
    		List<String> list = new ArrayList<>();
    		if(multipartFile!=null&&multipartFile.length>0){
        		for(int i=0;i<multipartFile.length;i++){
        			String fileId = SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO);//文件唯一ID
        			String originFileName = multipartFile[i].getOriginalFilename();//原上传文件名称
        			String newFileName = UUID.randomUUID().toString()+"_"+originFileName;//新文件名称
        			String filePath = rootPath+newFileName;//文件全路径
        			FileUtil.writeFile(multipartFile[i].getInputStream(),new byte[1024],new File(filePath));
        			com.javaweb.po.File fileEntity = new com.javaweb.po.File();
        			fileEntity.setFileId(fileId);
        			//fileEntity.setUserId("可以根据token获得");
        			fileEntity.setMicroSysNo(SystemConstant.SYSTEM_NO);
        			fileEntity.setFileName(newFileName);
        			fileEntity.setFilePath(rootPath);
        			fileEntity.setFileFullPath(filePath);
        			fileEntity.setFileSize(String.valueOf(multipartFile[i].getSize()));
        			fileEntity.setFileUnit("byte");
        			fileEntity.setFileSerNo(fileSerNo);
        			fileDao.insertForMySql(fileEntity);
        			list.add(fileId);
        		}
        	}
    		return new BaseResponseResult(HttpCodeEnum.SUCCESS,"文件上传成功",list);
    	}catch(Exception e){
    		return new BaseResponseResult(HttpCodeEnum.SUCCESS,"文件上传失败",e.getMessage());
    	}
    }
    
    @GetMapping("/downloadFile/{fileId}")
    public void downloadFile(@PathVariable(value="fileId",required=true)String fileId,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
    	try{
    		//下载文件可以根据token进行权限处理，此处省略
    		System.out.println("获得的token为："+httpServletRequest.getHeader(SystemConstant.HEAD_TOKEN));
    		com.javaweb.po.File file = fileDao.selectByPkForMySql(fileId);
			FileUtil.downloadFile(httpServletResponse.getOutputStream(),new byte[1024],new File(file.getFileFullPath()));
		}catch(Exception e) {
			e.printStackTrace();
		}
    }

}
