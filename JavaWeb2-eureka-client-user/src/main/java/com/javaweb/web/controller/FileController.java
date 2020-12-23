package com.javaweb.web.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseTool;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.FileUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.file.FileListRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
/**
 * 当文件服务作为通用服务时，可以与其它用到文件服务的系统进行对接，做的更加安全的话，微服务内部也可以采用token等形式进行鉴权
 * 除了file文件表还可以设计一张对接平台配置表，主要有如下字段：platform（平台、微服务系统编号等都可以）、url（微服务或远程调用的请求地址）、secret_key（秘钥）
 * 主要对接流程如下：
 * 一、我们约定：A：前后端分离的前端；B：前后端分离的后端；C：文件服务
 * 二、请求逻辑：A请求C，C请求B，B响应C，C响应A
 * 三、各端前置工作
 * 1、A：获取B提供的token和platform
 * 2、B：提供platform和url给C
 * 3、C：提供secret_key给B
 * 四、各端详细交互说明
 * 1、A拿到B给予的token和platform后，带token和platform请求C（兼容header和问号传参）
 * 2、C用POST方式请求B，传参：{"secretString":"加密字符串","radomString":"随机字符串","checkString":"校验字符串（如yyyyMMddHHmmss加密后的字符串）","token":"token（也可以放在header里）"}，秘钥就是secret_key，加密可以简单采用AES
 * 3、B校验token是否失效，然后加密radomString后的值应该等于secretString；解密checkString后的值应该为yyyyMMddHHmmss的日期格式，且与B的服务器时间正负差不能超过5分钟
 * 4、B都校验通过后，返回格式如：{"code":200,"data":data,"message":"成功"}，data又包括：{"isExpired":"是否失效（0：没有失效；1：失效）","systemCode":"platform（暂定）","userCode":"用户ID（暂定）","type":"11（外网、微信）"}
 */
@Api(tags=SwaggerConstant.SWAGGER_FILE_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_FILE_PREFIX)
public class FileController extends BaseController{
	
    @ApiOperation(value=SwaggerConstant.SWAGGER_FILE_UPLOAD)
	@PostMapping(ApiConstant.FILE_UPLOAD)
    public BaseResponseResult uploadFile(@TokenDataAnnotation TokenData tokenData,@RequestParam(value="file",required=true) MultipartFile multipartFile[]) {
    	try{
    		//上传文件大小校验，还可以校验总上传文件的大小
    		long total = 0;
    		if(multipartFile!=null&&multipartFile.length>0){
        		for(int i=0;i<multipartFile.length;i++){
        			if (multipartFile[i].getBytes().length > 1024 * 1024 *10) {//10MB
                        return new BaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"每个文件大小不能超过10MB");
                    }
        			total += multipartFile[i].getBytes().length;
        		}
        		if(total > 1024 * 1024 *50){
        			return new BaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"文件总大小不能超过50MB");
        		}
        	}
    		//获得文件根路径
    		String rootPath = BaseTool.getFileRootPath();
    		//上传文件并保存相关文件信息到数据库
    		List<String> list = fileService.uploadFile(multipartFile,rootPath,tokenData.getUser());
    		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"file.upload.success",list);
    	}catch(Exception e){
    		return getBaseResponseResult(HttpCodeEnum.INTERNAL_ERROR,"file.upload.fail");
    	}
    }
    
    @ApiOperation(value=SwaggerConstant.SWAGGER_FILE_DOWNLOAD)
    @GetMapping(ApiConstant.FILE_DOWNLOAD)
    public void downloadFile(@PathVariable(value="fileId",required=true)String fileId,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
    	try{
    		com.javaweb.web.po.File file = fileDao.selectByPk(fileId);
    		if(file!=null){
    			FileUtil.downloadFile(httpServletResponse.getOutputStream(),new byte[1024],new File(file.getFileFullPath()));
    		}/**else{
    			httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/file/fileNotExist");
        		httpServletResponse.flushBuffer();
        		return;
    		}*/
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @ApiOperation(value=SwaggerConstant.SWAGGER_FILE_LIST)
    @PostMapping(ApiConstant.FILE_LIST)
    public BaseResponseResult list(@RequestBody FileListRequest fileListRequest){
    	Page page = fileService.list(fileListRequest);
    	return getBaseResponseResult(HttpCodeEnum.SUCCESS,"file.list.success",page);
    }
    
    @ApiOperation(value=SwaggerConstant.SWAGGER_FILE_DETAIL)
    @GetMapping(ApiConstant.FILE_DETAIL)
    public BaseResponseResult detail(@PathVariable(value="fileId",required=true)String fileId){
    	com.javaweb.web.po.File file = fileService.fileDetail(fileId);
    	return getBaseResponseResult(HttpCodeEnum.SUCCESS,"file.detail.success",file);
    }
    
    @ApiOperation(value=SwaggerConstant.SWAGGER_FILE_DELETE)
    @GetMapping(ApiConstant.FILE_DELETE)
    public BaseResponseResult delete(@PathVariable(value="fileId",required=true)String fileId){
    	fileService.fileDelete(fileId);
    	return getBaseResponseResult(HttpCodeEnum.SUCCESS,"file.delete.success");
    }
    
}
