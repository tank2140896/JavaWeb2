package com.javaweb.config.minio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

/**
下载：https://min.io/download#/linux
API参考：https://docs.min.io/docs/java-client-api-reference#getObject
使用示例参考：https://blog.csdn.net/qq_43230007/article/details/108701081?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~sobaiduend~default-1-108701081.nonecase&utm_term=minio%E6%96%87%E4%BB%B6%E6%B5%81&spm=1000.2123.3001.4430
可视化界面示例：http://10.131.0.50:9000/minio
*/
public class MinioDemo {
	
	public static void main(String[] args) throws Exception {
		uploadFile();
		downloadFile();
	}
	
	//上传文件
	public static void uploadFile() throws Exception {
		File file = new File("E:\\1.bmp");
		InputStream is = new FileInputStream(file);
		MinioClient minioClient = MinioClient.builder().endpoint("http://10.131.0.50:9000").credentials("minioadmin","minioadmin").build();
		boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket("asiatrip").build());
        if(isExist){
        	System.out.println("Bucket already exists.");
        }else{
        	minioClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
        }
        PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket("asiatrip").object("1.bmp").stream(is,-1,5*1024*1024).contentType("image/bmp").build();//JPEG/BMP/TIF/PNG
        /*ObjectWriteResponse owr = */minioClient.putObject(putObjectArgs);
	}
	
	//下载文件
	public static void downloadFile() throws Exception {
		MinioClient minioClient = MinioClient.builder().endpoint("http://10.131.0.50:9000").credentials("minioadmin","minioadmin").build();
		GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket("asiatrip").object("1.bmp").build();
		InputStream is = minioClient.getObject(getObjectArgs);
		File file = new File("E:\\2.bmp");
		OutputStream os = new FileOutputStream(file);
		byte[] bts = new byte[1];//1byte
        while(is.read(bts)!=-1){
            os.write(bts);
        }
		os.flush();
		os.close();
		is.close();
	}

}
