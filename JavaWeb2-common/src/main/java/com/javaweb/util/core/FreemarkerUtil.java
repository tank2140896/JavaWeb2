package com.javaweb.util.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import com.javaweb.constant.CommonConstant;
import com.javaweb.enums.CamelCaseEnum;
import com.javaweb.enums.JsonTypeEnum;
import com.javaweb.util.entity.JavaJsonFtl;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FreemarkerUtil {
	
	//outputFilePath的格式形如C:\\Users\\admin\\Desktop\\test\\
	//String jsonString = StringUtil.jsonFormatFileToJosnString(new File("C:\\Users\\admin\\Desktop\\test.txt"),"UTF-8");
	//FreemarkerUtil.freemarkerForJsonGenerate(jsonString,"json-entity.ftl",null,"C:\\Users\\admin\\Desktop\\test\\");
	@SuppressWarnings("unchecked")
	public static void freemarkerForJsonGenerate(String jsonString,String templateFileName,String fileName,final String outputFilePath) throws Exception {
		if(fileName==null||"".equals(fileName)) {
			fileName = "RootJson.java";
		}
		Map<String,String> importMap = new HashMap<>();
		Map<String,Object> map = new HashMap<>();
		map.put("className",fileName.split("\\.")[0]);
		map.put("templateFileName",templateFileName);
		List<JavaJsonFtl> list = new ArrayList<>();
		JSONObject jo = JSONObject.fromObject(jsonString);
		Iterator<String> it = jo.keys();
		while(it.hasNext()) {
			JavaJsonFtl jjf = new JavaJsonFtl();
			String key = it.next();
			String value = jo.get(key).toString();
			/** ↓↓↓↓↓↓↓↓↓↓未做更加细致的处理,JSON类型的key值请保持驼峰法命名规则↓↓↓↓↓↓↓↓↓↓ */
			jjf.setAttribute(key);
			jjf.setAttributeLowerCase(StringUtil.camelCaseConvert(key,CamelCaseEnum.FIRST_WORD_LOWER)/*key.substring(0,1).toLowerCase()+key.substring(1,key.length())*/);
			jjf.setAttributeUpperCase(StringUtil.camelCaseConvert(key,CamelCaseEnum.FIRST_WORD_UPPER)/*key.substring(0,1).toUpperCase()+key.substring(1,key.length())*/);
			/** ↑↑↑↑↑↑↑↑↑↑未做更加细致的处理,JSON类型的key值请保持驼峰法命名规则↑↑↑↑↑↑↑↑↑↑ */
			JsonTypeEnum jte = StringUtil.getJsonType(jo.get(key));
			if(JsonTypeEnum.STRING==jte||JsonTypeEnum.NULL==jte) {//字符串类型
				jjf.setJavaType("String");
			}else if(JsonTypeEnum.BOOLEAN==jte) {//布尔类型
				jjf.setJavaType("boolean");
			}else if(JsonTypeEnum.NUMBER==jte) {//默认要么int要么double
				if(value.contains(".")) {//非整数
					jjf.setJavaType("double");
				}else {//整数
					jjf.setJavaType("int");
				}
			}else if(JsonTypeEnum.OBJECT==jte) {//对象类型
				jjf.setJavaType(jjf.getAttributeUpperCase());
				//importMap.put("import "+jjf.getAttributeUpperCase()+";","import "+jjf.getAttributeUpperCase()+";");//同包中的类不需要引用
				freemarkerForJsonGenerate(value,templateFileName,jjf.getAttributeUpperCase()+".java",outputFilePath);
			}else {//数组类型
				if(value.startsWith("[{")) {
					jjf.setJavaType("List<"+jjf.getAttributeUpperCase()+">");
					//importMap.put("import "+jjf.getAttributeUpperCase()+";","import "+jjf.getAttributeUpperCase()+";");//同包中的类不需要引用
					freemarkerForJsonGenerate(JSONArray.fromObject(value).optString(0),templateFileName,jjf.getAttributeUpperCase()+".java",outputFilePath);
				}else if(value.startsWith("[\"")) {
					jjf.setJavaType("List<String>");
				}else {
					if(value.contains(".")) {//非整数
						jjf.setJavaType("List<Double>");
					}else {//整数
						jjf.setJavaType("List<Integer>");
					}
				}
				importMap.put("import java.util.List;","import java.util.List;");
			}
			list.add(jjf);
		}
		map.put("jsonList",list);
		map.put("fileName",fileName);
		String importString = CommonConstant.EMPTY_VALUE;
		Set<String> set = importMap.keySet();
		for(String str:set) {
			importString+=(str+",");
		}
		map.put("imports",importString.equals(CommonConstant.EMPTY_VALUE)?CommonConstant.EMPTY_VALUE:importString.substring(0,importString.length()-1));
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
		configuration.setDirectoryForTemplateLoading(new File(Thread.currentThread().getContextClassLoader().getResource("ftl/jsonEntityGenerate").getFile()));
		configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_28));
	    Template template = configuration.getTemplate(map.get("templateFileName").toString());
	    File file = new File(outputFilePath+map.get("fileName"));
	    FileWriter fileWriter = new FileWriter(file);
	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    template.process(map,bufferedWriter);
	    bufferedWriter.flush();
	    bufferedWriter.close();
	    fileWriter.close();
	}
	
	public static void freemarkerForDbTablesCodeGenerate(Map<String,Object> map,String templateFileName[],String outFileName,ZipOutputStream zipOutputStream) throws Exception {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
		configuration.setDirectoryForTemplateLoading(new File(URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource("ftl/dbTablesCodeGenerate").getPath(),"UTF-8")/*Thread.currentThread().getContextClassLoader().getResource("ftl/dbTablesCodeGenerate").getFile()//使用此方式需要没有中文路径*/));
		//configuration.setDirectoryForTemplateLoading(new File("D:/dbTablesCodeGenerate"));
		configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_28));
		String rootFileName = "E:/"+UUID.randomUUID();
		File fileRoot = new File(rootFileName);
	    for(int i=0;i<templateFileName.length;i++){
	    	Template template = configuration.getTemplate(templateFileName[i]);
	    	FileUtil.makeFolder(fileRoot);
	    	String temp = CommonConstant.EMPTY_VALUE;
	    	String className = map.get("className").toString();
	    	if(templateFileName[i].equals("dao.ftl")){
	    		temp += className+"Dao.java";
	    	}else if(templateFileName[i].equals("mapper.ftl")){
	    		temp += className+"Mapper.xml";
	    	}else if(templateFileName[i].equals("po.ftl")){
	    		temp += className+".java";
	    	}else if(templateFileName[i].equals("service.ftl")){
	    		temp += className+"Service.java";
	    	}else if(templateFileName[i].equals("serviceImpl.ftl")){
	    		temp += className+"ServiceImpl.java";
	    	}
		    File file = new File(rootFileName+"/"+temp);
		    FileWriter fileWriter = new FileWriter(file);
		    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    template.process(map,bufferedWriter);
		    bufferedWriter.flush();
		    bufferedWriter.close();
		    fileWriter.close();
	    }
		FileUtil.toZip(fileRoot,outFileName,zipOutputStream,new byte[1024]);
		zipOutputStream.close();
		File files[] = fileRoot.listFiles();
		for(File file:files){
			file.delete();
		}
		fileRoot.delete();
	}
	
	public static void freemarkerForEoCodeGenerate(String packageName,String className,List<String> columnNameList) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("packageName",packageName);
		map.put("className",className);
		for(int i=0;i<columnNameList.size();i++){
			columnNameList.set(i,StringUtil.camelCaseConvert(columnNameList.get(i),CamelCaseEnum.FIRST_WORD_LOWER));
		}
		map.put("propertyList",columnNameList);
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
		configuration.setDirectoryForTemplateLoading(new File(URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource("ftl/dbTablesCodeGenerate").getPath(),"UTF-8")/*Thread.currentThread().getContextClassLoader().getResource("ftl/dbTablesCodeGenerate").getFile()//使用此方式需要没有中文路径*/));
		//configuration.setDirectoryForTemplateLoading(new File("D:/dbTablesCodeGenerate"));
		configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_28));
		File file = new File("E:/"+className+".java");
		Template template = configuration.getTemplate("eo.ftl");
		FileWriter fileWriter = new FileWriter(file);
	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    template.process(map,bufferedWriter);
	    bufferedWriter.flush();
	    bufferedWriter.close();
	    fileWriter.close();
	}

}
