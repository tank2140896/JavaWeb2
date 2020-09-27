package com.javaweb.util.core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.javaweb.constant.CommonConstant;
import com.javaweb.util.entity.ImageInfo;

public class FileUtil {
	
	public static final String FILE = "file";
	
	public static final String FOLDER = "folder";
	
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
	
	//递归获得某个文件夹下的所有文件夹和文件名称
	public static List<File> getAllFilesName(File file,List<File> fileList){
		if(file.isDirectory()){
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				fileList.add(files[i]);
				getAllFilesName(files[i],fileList);
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
    	/** 
    	 * 多文件合并写入                  
    	 * FileOutputStream fos = new FileOutputStream(new File("test.zip"));
    	 * fos.write(new byte[1]);
    	 * fos.write(new byte[2]);
    	 * fos.write(new byte[3]);
    	 * fos.close();
    	 */
		try(OutputStream outputStream = new FileOutputStream(file)) {
			int n = 0;
			while ((n=inputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0,n);
			}
			inputStream.close();
		} catch (IOException e) {
			throw new IOException(e);
		} 
    }
  	
  	//写文件
  	public static void writeFile(InputStream inputStream,byte[] buffer,Path path) throws IOException {
		try(OutputStream outputStream = Files.newOutputStream(path)) {
			int n= 0;
			while ((n=inputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0,n);
			}
			inputStream.close();
		} catch (IOException e) {
			throw new IOException(e);
		}
  	}
  	
	//下文件
	public static void downloadFile(OutputStream outputStream,byte[] buffer,File file) throws IOException{
		/**
    	 * 多文件内容读取
    	 * FileInputStream fis = new FileInputStream(new File("test.zip"));
    	 * fis.read(new byte[1]);
    	 * fis.read(new byte[2]);
    	 * fis.read(new byte[3]);
    	 * fis.close();
    	 */
		try(InputStream inputStream = new FileInputStream(file)) {
			int n=0;
			while ((n=inputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0,n);
			}
			outputStream.close();
		} catch (IOException e) {
			throw new IOException(e);
		} 
	}
	
	//下文件
	public static void downloadFile(OutputStream outputStream,byte[] buffer,Path path) throws IOException{
		try(InputStream inputStream = Files.newInputStream(path)) {
			int n=0;
			while ((n=inputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0,n);
			}
			outputStream.close();
		} catch (IOException e) {
			throw new IOException(e);
		} 
	}
	
	//字节流追加文件并设置编码
	public static void writeFileAppend(File file,String context) throws IOException{
		//字符流
		//Writer out = new FileWriter(f,true);//加true的话,不会替换原来文件的内容,直接追加
 	   	//Reader in = new FileReader(f);
		//try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"))) {
		try(BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath(),StandardCharsets.UTF_8,StandardOpenOption.APPEND)) {
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
		//try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.toFile(),true),"UTF-8"))) {
		try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path,StandardCharsets.UTF_8,StandardOpenOption.APPEND)) {
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
	
	//获取文件MD5值[最简单的方法:DigestUtils.md5Hex(new FileInputStream(path))]
	public static String getFileMD5(String filePath,byte[] buffer) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		Path path = Paths.get(filePath);
		try(InputStream inputStream = Files.newInputStream(path)){
			int n = 0;
			while((n=inputStream.read(buffer))!=-1){
				messageDigest.update(buffer,0,n);
			}
			BigInteger bigInt = new BigInteger(1,messageDigest.digest());
			return bigInt.toString(16);
		 }catch(Exception e){
			throw new Exception();
		 }
	}
	
	/**
	 * try(ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("F:\\1.zip"))){
	 *     File file = new File("F:\\1");
	 *	   toZip(file,file.getName(),zos,new byte[1024]);//文件目录可以写成F:/1或F:\\1
	 * }catch(IOException e){
	 *	   throw new IOException(e);
	 * }
	 */
	//压缩文件或目录
	public static void toZip(File file,String name,ZipOutputStream zos,byte buffer[]) throws IOException {
		if(file.isFile()){//文件
			zos.putNextEntry(new ZipEntry(name));
			int n=0;
			InputStream inputStream = Files.newInputStream(Paths.get(file.getPath()));
			while((n=inputStream.read(buffer))!=-1){
				zos.write(buffer,0,n);
			}
			inputStream.close();
		}else{//目录
			 File files[] = file.listFiles();
			 if(files==null||files.length==0){//空文件夹
				 zos.putNextEntry(new ZipEntry(name+File.separator));
				 zos.closeEntry();
			 }else{//非空
				 for(File f:files){
					 toZip(f,name+File.separator+f.getName(),zos,buffer);
				 }
			 }
		}
	}
	
	/**
	 * try(ZipInputStream zis = new ZipInputStream(new FileInputStream("F:\\1.zip"))){
	 *     unZip("F:\\1",zis,new byte[1024]);
	 * }catch(IOException e){
	 *	   throw new IOException(e);
	 * }
	 */
	//解压文件
	public static void unZip(String unZipRootFilePath,ZipInputStream zis,byte buffer[]) throws IOException {
		ZipEntry zipEntry = null;
		int n=0;
		while((zipEntry=zis.getNextEntry())!=null){
			String filePath = unZipRootFilePath+File.separator+zipEntry.getName();
			String fileFolder = filePath.substring(0,filePath.lastIndexOf(File.separator));
			File file = new File(fileFolder);
			if(!file.exists()){
				file.mkdirs();
			}
			file = new File(filePath);
			if(!file.isDirectory()){
				OutputStream outputStream = Files.newOutputStream(file.toPath());
				while((n=zis.read(buffer))!=-1){
					outputStream.write(buffer,0,n);
				}
				outputStream.close();
			}
		}
	}
	
	//图片转Base64字符串
	public static String imageSrcToBase64String(BufferedImage image) {
	    byte[] data = null;
	    try{
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
	    	ImageIO.write(image,"jpeg",ios);
	    	InputStream input = new ByteArrayInputStream(baos.toByteArray());
	    	data = new byte[input.available()];
	    	input.read(data);
	    } catch (IOException e) {
	    	data = null;
	    }
	    if(data==null){
	    	return null;
	    }else{
	    	data = Base64.getEncoder().encode(data);
	    	return new String(data);
	    }
	}
	
	//图片转Base64字符串
	public static String imageSrcToBase64String(String src) {
	    byte[] data = null;
	    try(InputStream inputStream = new FileInputStream(new File(src))) {
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	    } catch (IOException e) {
	        //do nothing
	    }
	    if(data==null){
	    	return null;
	    }else{
	    	data = Base64.getEncoder().encode(data);
	    	return new String(data);
	    }
	}
	
	//Base64字符串解码
	public static byte[] getBase64Byte(String imageBase64String) {
		try{
			//为了去除data:image/png;base64,
			imageBase64String = imageBase64String.split(",")[1];
		}catch(Exception e){
			//do nothing
		}
		byte[] data = null;
		try{
			data = Base64.getDecoder().decode(imageBase64String.getBytes());
			for(int i=0;i<data.length;++i) {
				if(data[i]<0){
					data[i] += 256;
				}
			}
			//OutputStream out = new FileOutputStream(path);
			//out.write(data);
			//out.close();
		} catch (Exception e){
			//do nothing
		}
		return data;
	}
	
	//按字节读取
	public static List<String> readFileByByte(String fileName,byte number) throws Exception {
		List<String> out = new ArrayList<>();
		RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"r");
		long position = 0;
		List<Byte> list = new ArrayList<>();
		while(true){
			try{
				randomAccessFile.seek(position++);
				byte[] bytes = new byte[1];
				bytes[0] = randomAccessFile.readByte();
				list.add(bytes[0]);
				if(bytes[0]==number){//例如125=}
					byte b[] = new byte[list.size()];
					for(int i=0;i<list.size();i++){
						b[i] = list.get(i);
					}
					out.add(new String(b,"utf-8"));
					list = new ArrayList<>();
				}
			}catch(Exception e){
				break;
			}
		}
		randomAccessFile.close();
		return out;
	}
	
	//按行数读取
	public static String readFileByLineNumber(String fileName,long lineNumber) throws Exception {
	    return Files.lines(Paths.get(fileName),Charset.forName("UTF-8")).skip(lineNumber-1).limit(1).iterator().next();
	}
	
	//读取某一行数据并修改该行数据
	@Deprecated
    public static void readAndModifyFile(String fileName,String newFileName,long lineNumber,String context) throws Exception {
        try(LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"))) {
            String eachLine = null;
            long position = 1;
            while((eachLine=lineNumberReader.readLine())!=null){
                if(lineNumber==position) {
                    eachLine = context;
                }
                writeFileAppend(Paths.get(newFileName),eachLine+(SystemUtil.isLinux()?CommonConstant.ENTER_LINUX:CommonConstant.ENTER_WINDOWS));
                position++;
            }
        }catch(IOException e){
            throw new IOException(e);
        }
    }
	
	//旋转图片（更多图片操作参考：Thumbnailator）
	public static BufferedImage rotateImage(final BufferedImage bufferedimage,final int degree){
		int src_width = bufferedimage.getWidth(null);
        int src_height = bufferedimage.getHeight(null);
        //计算旋转后图片的尺寸
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(src_width,src_height)),degree);
        BufferedImage res = new BufferedImage(rect_des.width,rect_des.height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = res.createGraphics();
        //进行转换
        g2.translate((rect_des.width-src_width)/2,(rect_des.height-src_height)/2);
        g2.rotate(Math.toRadians(degree),src_width/2,src_height/2);
        g2.drawImage(bufferedimage,null,null);
        return res;
    }
	
	//计算旋转尺寸（更多图片操作参考：Thumbnailator）
	public static Rectangle calcRotatedSize(Rectangle src,int angel) {
        //如果旋转的角度大于90度做相应的转换
        if(angel>=90){
            if(angel/90%2==1){
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel%90;
        }
        double r = Math.sqrt(src.height*src.height+src.width*src.width)/2;
        double len = 2*Math.sin(Math.toRadians(angel)/2)*r;
        double angel_alpha = (Math.PI-Math.toRadians(angel))/2;
        double angel_dalta_width = Math.atan((double)src.height/src.width);
        double angel_dalta_height = Math.atan((double)src.width/src.height);
        int len_dalta_width = (int)(len*Math.cos(Math.PI-angel_alpha-angel_dalta_width));
        int len_dalta_height = (int)(len * Math.cos(Math.PI-angel_alpha-angel_dalta_height));
        int des_width = src.width+len_dalta_width*2;
        int des_height = src.height+len_dalta_height*2;
        return new Rectangle(new Dimension(des_width,des_height));
    }
	
	//图片转字节（format如png）（更多图片操作参考：Thumbnailator）
	public static byte[] imageToBytes(BufferedImage bufferedImage,String format) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage,format,baos);
		return baos.toByteArray();
	}
	
	//获取图片信息（更多图片操作参考：Thumbnailator）
	public static ImageInfo getImageInfo(File file) throws Exception {
		ImageInfo imageInfo = new ImageInfo();
		BufferedImage bufferedImage = ImageIO.read(new FileInputStream(file)); 
		imageInfo.setFileSize(String.format("%.1f",file.length()/1024.0));//图片大小
		imageInfo.setWidth(String.valueOf(bufferedImage.getWidth()));//图片宽度
		imageInfo.setHeight(String.valueOf(bufferedImage.getHeight()));//图片高度
		//还可以获得其他属性
		return imageInfo;
	}
	
}
