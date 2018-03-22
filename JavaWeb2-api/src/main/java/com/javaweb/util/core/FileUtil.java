package com.javaweb.util.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {
	
	private static final String FILE = "file";
	
	private static final String FOLDER = "folder";
	
	public enum FileType{
		
		FILE(FileUtil.FILE),
		FOLDER(FileUtil.FOLDER);
		
		private FileType(String fileType){
			this.fileType = fileType;
		}
		
		private String fileType;
		
		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
		
	}
	
	//递归获得所有文件名称
	public static List<File> getAllFilesName(File file,List<File> fileList,boolean recursion){
		if(file.isDirectory()){
			//listFiles只列出文件;list列出所有(文件和目录)
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if(recursion){
					getAllFilesName(files[i],fileList,recursion);
				}
				fileList.add(files[i]);
			}
		}else{
			if(file.exists()){
				fileList.add(file);
			}
		}
		return fileList;
	}
	
    //创建文件或文件夹
    public static void makeFileOrFolder(File file,FileType fileType) throws IOException{
    	if(!file.exists()){
    		if(FileType.FILE==fileType){//文件
    			file.createNewFile();
    		}else{//文件夹
    			file.mkdirs();
    		}
    	}
    }
    
    //创建文件夹
    public static void makeFolder(File file){
    	if(!file.isFile()){
			if(!file.exists()){
				file.mkdirs();
			}
		}
    }
    
    //写文件
    public static void writeFile(InputStream inputStream,byte[] buffer,File file) throws IOException {
		try(OutputStream outputStream = new FileOutputStream(file)) {
			//byte[] buffer = new byte[1024];//1KB
			while (inputStream.read(buffer) != -1) {
				outputStream.write(buffer);
			}
		} catch (IOException e) {
			throw new IOException(e);
		} 
    }
  	
  	//写文件
  	public static void writeFile(InputStream inputStream,byte[] buffer,Path path) throws IOException {
		try(OutputStream outputStream = Files.newOutputStream(path)) {
			//byte[] buffer = new byte[1024];//1KB
			while (inputStream.read(buffer) != -1) {
				outputStream.write(buffer);
			}
		} catch (IOException e) {
			throw new IOException(e);
		}
  	}
  	
	//下文件
	public static void downloadFile(OutputStream outputStream,byte[] buffer,File file) throws IOException{
		try(InputStream inputStream = new FileInputStream(file)) {
			//byte[] buffer = new byte[1024];//1KB
			while (inputStream.read(buffer) != -1) {
				outputStream.write(buffer);
			}
		} catch (IOException e) {
			throw new IOException(e);
		} 
	}
	
	//下文件
	public static void downloadFile(OutputStream outputStream,byte[] buffer,Path path) throws IOException{
		try(InputStream inputStream = Files.newInputStream(path)) {
			//byte[] buffer = new byte[1024];//1KB
			while (inputStream.read(buffer) != -1) {
				outputStream.write(buffer);
			}
		} catch (IOException e) {
			throw new IOException(e);
		} 
	}
	
	//字节流追加文件并设置编码
	public static void writeFileAppend(File file,String context) throws IOException{
		//字符流
		//Writer out = new FileWriter(f,true);//加true的话,不会替换原来文件的内容,直接追加
 	   	//Reader in = new FileReader(f);
		try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"))) {
			bufferedWriter.write(context);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
	
	//字节流追加文件并设置编码
	public static void writeFileAppend(Path path,String context) throws IOException{
		//字符流
		//Writer out = new FileWriter(f,true);//加true的话,不会替换原来文件的内容,直接追加
 	   	//Reader in = new FileReader(f);
		try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.toFile(),true),"UTF-8"))) {
			bufferedWriter.write(context);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
	
	//一行一行读取
	public static List<String> readFileLineByLine(File file,List<String> lineList) throws IOException{
		try(LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))) {
			String eachLine = null;
			while((eachLine=lineNumberReader.readLine())!=null){
				lineList.add(eachLine);
			}
		}catch(IOException e){
			throw new IOException(e);
		}
		return lineList;
	}
	
	//序列化输出
	public static void writeSerialize(String filePath,Object obj) throws IOException{
		Path path = Paths.get(filePath);
		if(Files.isDirectory(path)){
			return;
		}
		if(!Files.exists(path)){
			Files.createFile(path);
		}
		try(BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(path));
			ObjectOutputStream oos = new ObjectOutputStream(bos);) {
			oos.writeObject(obj);
			oos.flush();
			bos.flush();
		} catch (Exception e) {
			throw new IOException(e);
		}
	}
	
	//序列化读取
	public static Object readSerialize(String filePath) throws IOException,ClassNotFoundException{
		Path path = Paths.get(filePath);
		if(Files.isReadable(path)){
			try(BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path));
				ObjectInputStream ois = new ObjectInputStream(bis);){
				Object obj = ois.readObject();
				return obj;
			}catch (IOException e) {
				throw new IOException(e);
			}
		}
		return null;
	}
	
	//深克隆
	public static Object deepClone(Object object) throws IOException,ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		return ois.readObject();
	}
	
	//文件转字节流
	public static byte[] fileToBytes(String filePath,byte[] buffer) throws IOException{
		//final byte b[] = new byte[1024];
		Path path = Paths.get(filePath);
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
			FileInputStream fis = new FileInputStream(path.toFile());){
			int n = 0;
			while((n=fis.read(buffer))!=-1){
				baos.write(buffer,0,n);
			}
			return baos.toByteArray();
		}catch (IOException e) {
			return null;
		}
	}
	
}
