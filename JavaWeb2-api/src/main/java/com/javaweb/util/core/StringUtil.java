package com.javaweb.util.core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import com.javaweb.constant.CommonConstant;

public class StringUtil{
	
	//处理字符串的null值,如果为null返回空
	public static String handleNullString(String str) {
		Optional<String> optional = Optional.ofNullable(str);
		return optional.map(String::toString).orElse(CommonConstant.EMPTY_VALUE);
	}
	
	//判断是否含有中文字符
	public static boolean isContainChinese(String str){
		str = handleNullString(str);
		if(str.length()==str.getBytes().length){
			return false;
		}
		return true;
	}
	
	//或取某个类的绝对路径
	public static String getClassAbsolutePath(Class<?> c){
		//ArrayUtil.class.getClass().getResource("/").getPath();
		String filePath = c.getProtectionDomain().getCodeSource().getLocation().getFile();
		try {
			//结果如下->/C:/Users/admin/Desktop/util-collections/target/classes/
			return URLDecoder.decode(filePath,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	//字符串替换"\\{\\{(.+?)\\}\\}"
	public static String stringReplace(String template,Map<String,String> data,String regex){
		StringBuffer stringBuffer = new StringBuffer();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);
        while(matcher.find()){
            String name = matcher.group(1);//键名
            String value = data.get(name);//键值
            if(value==null){
            	value = CommonConstant.EMPTY_VALUE;
            }
            matcher.appendReplacement(stringBuffer,value);
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
	}
	
	//字符串SQL替换(如\\?)
	public static String stringReplaceForSql(String placeHolderRegex,String sql,Object eachValue[]){
		StringBuffer stringBuffer = new StringBuffer();
        Pattern pattern = Pattern.compile(placeHolderRegex);
        Matcher matcher = pattern.matcher(sql);
        int count = 0;
        Object get = null;
        while(matcher.find()){
            get = eachValue[count++];
            if(get instanceof Number){
            	matcher.appendReplacement(stringBuffer,get.toString());
            	continue;
            }
            if(get instanceof String){
            	matcher.appendReplacement(stringBuffer,String.join(get.toString(),"'","'"));
            	continue;
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
	}
	
	//设置敏感词(极简易实现)
	public static JSONObject setSensitiveWord(List<String> keyWordList) throws Exception {
		JSONObject jo = new JSONObject();
		for(String each:keyWordList){
			JSONObject tmp = jo;
			for(int i=0;i<each.length();i++){
				String s = String.valueOf(each.charAt(i));
				Object obj = tmp.get(s);
				if(obj==null){
					JSONObject joo = new JSONObject();
					joo.put("isEnd",i==each.length()-1?true:false);
					tmp.put(s,joo);
					/**tmp = (JSONObject)tmp.get(s);
				}else{
					tmp = (JSONObject)tmp.get(s);*/
				}
				tmp = (JSONObject)tmp.get(s);//对象引用精髓所在
			}
		}
		return jo;
	}
	
	//敏感词检查(极简易实现)
	public static boolean isContainSensitiveWord(String text,JSONObject sensitiveWordRule){
		for(int i=0;i<text.length();i++){
			String each = String.valueOf(text.charAt(i));
			JSONObject jo = sensitiveWordRule;
			while(true){
				jo = (JSONObject)jo.get(each);
				if(jo!=null){
					if(true==(boolean)jo.get("isEnd")){
						return true;
					}else{
						i++;
						each = String.valueOf(text.charAt(i));
					}
				}else{
					break;
				}
			}
		}
		return false;
	}
	
}
