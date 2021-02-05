package com.javaweb.config.file;

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
		//HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/fileSystem/file/uploadFile");
		//HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/fs/file/uploadFile");
		HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/fs/file/uploadFile?&platform=admin");
		httpPost.setHeader("token_pass_key","ABCDEFG123456");
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		for(int i=1;i<=2;i++){
			multipartEntityBuilder.addBinaryBody("file",new File("E:\\\\"+i+".jpg"));
			//multipartEntityBuilder.addBinaryBody("file",files[i].getInputStream(),ContentType.MULTIPART_FORM_DATA,files[i].getOriginalFilename());
			/**
			URL url = new URL("http://www.url.com/1.jpg");
	        	InputStream is = url.openStream();
	        	File file = new File("/tmp/1.jpg");
	        	copyInputStreamToFile(is,file);
	        	multipartEntityBuilder.addBinaryBody("file",file);
			//再删除/tmp/1.jpg文件
			public void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        			try (FileOutputStream outputStream = new FileOutputStream(file)) {
            				int read;
            				byte[] bytes = new byte[1024];
            				while ((read = inputStream.read(bytes)) != -1) {
                				outputStream.write(bytes, 0, read);
            				}
        			}
			}
			*/
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
