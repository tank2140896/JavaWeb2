package com.javaweb.config.file;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/web")
public class FileController {
	
	//文件上传页面端和手机APP端都是通用的[produces={"multipart/form-data;charset=UTF-8"},这个是response的返回]
	//注意一点:String myData接收的是JSON格式的字符串,如果这里接收不到或报错,可以用最基本的request.getParameter(name)或request.getParameterMap()来接收
	@PostMapping(value="/fileUpload",produces=MediaType.APPLICATION_JSON_VALUE/*APPLICATION_JSON_UTF8_VALUE*/)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, 
					         HttpServletResponse response,
					         //如果面对N多个文件上传控件,似乎没有什么办法
					         //@RequestParam MultipartFile file1,
					         //@RequestParam MultipartFile file2,
					         @RequestParam(value="myFile"/*与前端统一*/) MultipartFile myFile,
							 String myData) {
		try{
			//System.out.println(myData);
			String uploadFileName = myFile.getOriginalFilename();//得到上传文件的文件名称
			String rootPath = "/user/file";//文件上传根目录
			File file = new File(rootPath);
			if(!file.exists()){
				//可以先创建目录
			}
			Path path = Paths.get(rootPath+"/"+uploadFileName);
			System.out.println(path);
			//写入文件操作
			//myFile.getInputStream(),path
			//myFile.transferTo(file);
			return "文件上传成功";
		}catch(Exception e){
			return "文件上传失败";
		}
	}
	
	//文件下载页面端和手机APP端都是通用的
	@GetMapping(value="/fileDownload")
	public void fileDownload(HttpServletRequest request,HttpServletResponse response) {
		try{
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("文件名.txt", "UTF-8"));  
	        response.setContentType("application/OCTET-STREAM;charset=UTF-8");
			OutputStream os = response.getOutputStream();
			String rootPath = "D:/file";
			File file = new File(rootPath);
			if(!file.exists()){
				//可以先创建目录
			}
			File newFile = new File(rootPath+"/a.txt");
			if(!newFile.exists()){
				newFile.createNewFile();
			}
			Path path = Paths.get(newFile.getAbsolutePath());
			System.out.println(path);
			//写入文件操作
			os.close();
			response.flushBuffer();
		}catch(Exception e){
			//do nothing
		}
	}
	
}
