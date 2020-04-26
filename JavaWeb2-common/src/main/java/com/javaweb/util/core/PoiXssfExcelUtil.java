package com.javaweb.util.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.javaweb.constant.CommonConstant;

//使用对象封装，对象的属性需要为封装类型
public class PoiXssfExcelUtil {
	
	//遍历所有sheet
	public static Object[] readAllExcelSheet(InputStream inputStream) throws IOException {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
		int sheets = xssfWorkbook.getNumberOfSheets();
		Object[] objects = new Object[sheets];
		for(int i=0;i<sheets;i++){
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
			objects[i] = readSheet(xssfSheet);
		}
		xssfWorkbook.close();
		return objects;
	}
	
	//根据sheet名字遍历单个sheet
	public static List<List<String>> readSingleExcelSheet(InputStream inputStream,String sheetName) throws IOException {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
		XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
		List<List<String>> list = readSheet(xssfSheet);
		xssfWorkbook.close();
		return list;
	}
	
	//根据sheet名字遍历单个sheet
	public static <T> List<T> readSingleExcelSheet(InputStream inputStream,String sheetName,Map<Integer,String> map,Class<T> objectClass) throws Exception {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
		XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
		List<T> list = readSheet(xssfSheet,map,objectClass);
		xssfWorkbook.close();
		return list;
	}
	
	//根据sheet序号遍历单个sheet
	public static List<List<String>> readSingleExcelSheet(InputStream inputStream,int sheetIndex) throws IOException {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetIndex);
		List<List<String>> list = readSheet(xssfSheet);
		xssfWorkbook.close();
		return list;
	}
	
	//根据sheet序号遍历单个sheet
	public static <T> List<T> readSingleExcelSheet(InputStream inputStream,int sheetIndex,Map<Integer,String> map,Class<T> objectClass) throws Exception {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetIndex);
		List<T> list = readSheet(xssfSheet,map,objectClass);
		xssfWorkbook.close();
		return list;
	}
	
	//写入Excel
	public static void writeExcelData(OutputStream outputStream,List<List<String>> data,String sheetName) throws IOException{
		XSSFWorkbook xssfWorkbook = writeSheetData(data,sheetName);
		xssfWorkbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		xssfWorkbook.close();
	}
	
	//写入Excel
	public static void writeExcelData(HttpServletResponse response,List<List<String>> data,String sheetName,String fileName) throws IOException{
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
		ServletOutputStream outputStream = response.getOutputStream();
		XSSFWorkbook xssfWorkbook = writeSheetData(data,sheetName);
		xssfWorkbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		response.flushBuffer();
		xssfWorkbook.close();
	}
	
	//写入Excel
	public static void writeExcelObject(OutputStream outputStream,List<Object> data,String sheetName) throws Exception{
		XSSFWorkbook xssfWorkbook = writeSheetObject(data,sheetName);
		xssfWorkbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		xssfWorkbook.close();
	}
	
	//写入Excel
	public static void writeExcelObject(HttpServletResponse response,List<Object> data,String sheetName,String fileName) throws Exception{
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
		ServletOutputStream outputStream = response.getOutputStream();
		XSSFWorkbook xssfWorkbook = writeSheetObject(data,sheetName);
		xssfWorkbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		response.flushBuffer();
		xssfWorkbook.close();
	}
	
	//读取每个sheet里的数据
	public static List<List<String>> readSheet(XSSFSheet xssfSheet){
		int rows = xssfSheet.getPhysicalNumberOfRows();
		List<List<String>> rowList = new ArrayList<>();
		for(int i=0;i<rows;i++){//遍历每一行
			XSSFRow row = xssfSheet.getRow(i);
			if(row==null){
				continue;
			}
			int cells = row.getPhysicalNumberOfCells();
			List<String> cellList = new ArrayList<>();
			for(int j=0;j<cells;j++){//遍历每一列
				XSSFCell cell = row.getCell(j);
				if(cell==null){
					cellList.add(CommonConstant.EMPTY_VALUE);
				}else{
					cell.setCellType(CellType.STRING);
					String cellValue = cell.getStringCellValue();
					cellList.add(cellValue);
				}
			}
			rowList.add(cellList);
		}
		return rowList;
	}
	
	//读取每个sheet里的数据
	public static <T> List<T> readSheet(XSSFSheet xssfSheet,Map<Integer,String> map,Class<T> objectClass) throws Exception{
		int rows = xssfSheet.getPhysicalNumberOfRows();
		List<T> rowList = new ArrayList<>();
		for(int i=0;i<rows;i++){//遍历每一行
			T target = objectClass.newInstance();
			XSSFRow row = xssfSheet.getRow(i);
			if(row==null){
				continue;
			}
			Set<Integer> set = map.keySet();
			for(Integer each:set){
				XSSFCell cell = row.getCell(each);
				if(cell==null){
					continue;
				}
				cell.setCellType(CellType.STRING);//无论什么类型都先转为String
				String fieldName = map.get(each);
				Class<?> fieldType = objectClass.getDeclaredField(fieldName).getType();
				fieldName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length());
				Method method = target.getClass().getDeclaredMethod("set"+fieldName,fieldType);
				if("java.lang.Integer".equals(fieldType.getName())){
					method.invoke(target,Integer.parseInt(cell.toString()));
				}else if("java.lang.Long".equals(fieldType.getName())){
					method.invoke(target,Long.parseLong(cell.toString()));
				}else if("java.lang.Float".equals(fieldType.getName())){
					method.invoke(target,Float.parseFloat(cell.toString()));
				}else if("java.lang.Double".equals(fieldType.getName())){
					method.invoke(target,Double.parseDouble(cell.toString()));
				}else{
					method.invoke(target,cell.toString());
				}
			}
			rowList.add(target);
		}
		return rowList;
	}
	
	//写入每个sheet里的数据
	public static XSSFWorkbook writeSheetData(List<List<String>> data,String sheetName){
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = xssfWorkbook.createSheet(sheetName);
		XSSFRow[] rows = new XSSFRow[data.size()];
		for(int i=0;i<data.size();i++){
			List<String> columns = data.get(i);
			rows[i] = xssfSheet.createRow(i);
			//xssfSheet.setDefaultColumnWidth(columnWidth);//设置列的长度
			XSSFCell[] cells = new XSSFCell[columns.size()];
			for(int j=0;j<columns.size();j++){
				cells[j] = rows[i].createCell(j);
				//cells[j].setCellStyle(XSSFCellStyle);
				cells[j].setCellValue(columns.get(j));
			}
		}
		return xssfWorkbook;
	}
	
	//写入每个sheet里的数据
	public static XSSFWorkbook writeSheetObject(List<Object> data,String sheetName) throws Exception {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = xssfWorkbook.createSheet(sheetName);
		XSSFRow[] rows = new XSSFRow[data.size()];
		for(int i=0;i<data.size();i++){
			Object classes = data.get(i);
			Object target = classes.getClass().newInstance();
			Field[] fields = target.getClass().getDeclaredFields();
			rows[i] = xssfSheet.createRow(i);
			//xssfSheet.setDefaultColumnWidth(columnWidth);//设置列的长度
			XSSFCell[] cells = new XSSFCell[fields.length];
			for(int j=0;j<fields.length;j++){
				String fieldName = fields[j].getName();
				fieldName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length());
				Method method = target.getClass().getDeclaredMethod("get"+fieldName,new Class[]{});
				cells[j] = rows[i].createCell(j);
				Object value = method.invoke(classes,new Object[]{});
				if(value instanceof Double){
					cells[j].setCellValue(new Double(value.toString()));
				}else if(value instanceof Integer){
					cells[j].setCellValue(new Integer(value.toString()));
				}else if(value instanceof Float){
					cells[j].setCellValue(new Float(value.toString()));
				}else{
					cells[j].setCellValue(value.toString());
				}
			}
		}
		return xssfWorkbook;
	}

}
