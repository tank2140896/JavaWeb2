package com.javaweb.config.poi;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDemo {
	
	//模拟生成Excel数据
	public List<String> mokCreateData(){
		List<String> list = new ArrayList<String>();
		list.add("ID,用户名,密码,地址");
		for (int i = 0; i < 100; i++) {
			list.add("id"+(i+1)+",username"+(i+1)+",password"+(i+1)+",address"+(i+1));
		}
		return list;
	}
	
	//原生生成Excel
	public void writeExcelForLocal(List<String> list) throws Exception {
		OutputStream sos = new FileOutputStream(new File("D://file_"+UUID.randomUUID()+".xlsx"));
	    //创建一个新的excel(XSSFWorkbook)
		XSSFWorkbook wb = new XSSFWorkbook();
		//创建sheet页(输入sheet名)
		XSSFSheet sheet = wb.createSheet("sheet1");
		//创建行数
		XSSFRow[] row = new XSSFRow[list.size()];
		//插入数据
		for (int i = 0; i < row.length; i++) {
			row[i] = sheet.createRow(i);
			//设置列的长度
			sheet.setDefaultColumnWidth(50);
			String info[] = list.get(i).split(",");
			XSSFCell[] headerCell = new XSSFCell[info.length];
			for (int j = 0; j < headerCell.length; j++) {
				headerCell[j] = row[i].createCell(j);
				headerCell[j].setCellValue(new XSSFRichTextString(info[j]));
			}
		}
		wb.write(sos);
		wb.close();
	    sos.flush();
	    sos.close();
	}
	
	//原生生成Excel
	public void writeExcelForDownload(HttpServletResponse response,List<String> list) throws Exception {
		//文件格式,此处设置为excel
		response.setContentType("application/vnd.ms-excel");
		//此处设置了下载文件的默认名称
		response.setHeader("Content-Disposition","attachment;filename=file_"+UUID.randomUUID()+".xlsx");
		ServletOutputStream sos = response.getOutputStream();
	    //创建一个新的excel(XSSFWorkbook)
		XSSFWorkbook wb = new XSSFWorkbook();
		//创建sheet页(输入sheet名)
		XSSFSheet sheet = wb.createSheet("sheet1");
		//创建行数
		XSSFRow[] row = new XSSFRow[list.size()];
		//插入数据
		for (int i = 0; i < row.length; i++) {
			row[i] = sheet.createRow(i);
			//设置列的长度
			sheet.setDefaultColumnWidth(50);
			String info[] = list.get(i).split(",");
			XSSFCell[] headerCell = new XSSFCell[info.length];
			for (int j = 0; j < headerCell.length; j++) {
				headerCell[j] = row[i].createCell(j);
				headerCell[j].setCellValue(new XSSFRichTextString(info[j]));
			}
		}
		wb.write(sos);
		wb.close();
	    sos.flush();
	    sos.close();
	    response.flushBuffer();
	}
	
	//模板生成Excel
	public void writeExcelForTemplet(List<String> list) throws Exception {
		OutputStream sos = new FileOutputStream(new File("D://file_"+UUID.randomUUID()+".xlsx"));
	    //创建一个新的excel(XSSFWorkbook)
		XSSFWorkbook wb = new XSSFWorkbook();
		//创建sheet页(输入sheet名)
		XSSFSheet sheet = wb.createSheet("sheet1");
		//创建行数
		XSSFRow[] row = new XSSFRow[list.size()];
		//插入数据
		for (int i = 0; i < row.length; i++) {
			row[i] = sheet.createRow(i);
			//设置列的长度
			sheet.setDefaultColumnWidth(50);
			String info[] = list.get(i).split(",");
			XSSFCell[] headerCell = new XSSFCell[info.length];
			for (int j = 0; j < headerCell.length; j++) {
				headerCell[j] = row[i].createCell(j);
				headerCell[j].setCellValue(new XSSFRichTextString(info[j]));
				//设置模板样式
				headerCell[j].setCellStyle(setStyle(wb));
			}
		}
		wb.write(sos);
		wb.close();
	    sos.flush();
	    sos.close();
	}
	
	//模板生成Excel
	//用这种方式得先保证每个cell有值,不然会报空指针
	public void writeExcelForExcelTemplet(List<String> list,String templetFilePath) throws Exception {
		OutputStream sos = new FileOutputStream(new File("D://file_"+UUID.randomUUID()+".xlsx"));
	    //得到一个XSSFWorkbook模板
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(templetFilePath)));
		//得到sheet
		XSSFSheet sheet = wb.getSheet("Sheet1");//Sheet1
		//创建行数
		XSSFRow[] row = new XSSFRow[list.size()];
		//插入数据
		for (int i = 0; i < row.length; i++) {
			row[i] = sheet.getRow(i);
			String info[] = list.get(i).split(",");
			XSSFCell[] headerCell = new XSSFCell[info.length];
			for (int j = 0; j < headerCell.length; j++) {
				headerCell[j] = row[i].getCell(j);
				headerCell[j].setCellValue(new XSSFRichTextString(info[j]));
			}
		}
		wb.write(sos);
		wb.close();
	    sos.flush();
	    sos.close();
	}
	
	//测试指定模板的格式输出(包含合并的单元格)
	public static void main(String[] args) throws Exception{
		OutputStream sos = new FileOutputStream(new File("D://file_"+UUID.randomUUID()+".xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File("D://demo.xlsx")));
		XSSFSheet sheet = wb.getSheet("Sheet1");
		/**获取指定行和列*/
		//获得模板的第二行
		XSSFRow row2 = sheet.getRow(1);
		//对第二行的具体数据进行赋值操作
		Iterator<Cell> it2 = row2.cellIterator();
		int i = 0;
		while(it2.hasNext()){
			//获得模板的第2、4、6列
			Cell c = it2.next();
			if(i==1){
				c.setCellValue("张三");
			}
			if(i==3){
				c.setCellValue("男");
			}
			if(i==5){
				c.setCellValue("2001-05-01");
			}
			i++;
		}
		i=0;
		//获得模板的第三行
		XSSFRow row3 = sheet.getRow(2);
		//对第三行的具体数据进行赋值操作
		Iterator<Cell> it3 = row3.cellIterator();
		while(it3.hasNext()){
			//获得模板的第2、4、6列
			Cell c = it3.next();
			if(i==1){
				c.setCellValue("汉");
			}
			if(i==3){
				c.setCellValue("江苏");
			}
			if(i==5){
				c.setCellValue("苏州");
			}
			i++;
		}
		i=0;
		//获得模板的第四行
		XSSFRow row4 = sheet.getRow(3);
		//对第四行的具体数据进行赋值操作
		Iterator<Cell> it4 = row4.cellIterator();
		while(it4.hasNext()){
			//获得模板的第2、4、6列
			Cell c = it4.next();
			if(i==1){
				c.setCellValue("-");
			}
			if(i==3){
				c.setCellValue("2015-07-27");
			}
			if(i==5){
				c.setCellValue("良好");
			}
			i++;
		}
		i=0;
		//获得模板的第五行
		XSSFRow row5 = sheet.getRow(4);
		//对第五行的具体数据进行赋值操作
		Iterator<Cell> it5 = row5.cellIterator();
		while(it5.hasNext()){
			//获得模板的第2、5列
			Cell c = it5.next();
			if(i==1){
				c.setCellValue("无");
			}
			if(i==4){
				c.setCellValue("无");
			}
			i++;
		}
		i=0;
		//获得模板的第六行
		XSSFRow row6 = sheet.getRow(5);
		//对第六行的具体数据进行赋值操作
		Iterator<Cell> it6 = row6.cellIterator();
		while(it6.hasNext()){
			//获得模板的第3、5列
			Cell c = it6.next();
			if(i==2){
				c.setCellValue("本科");
			}
			if(i==4){
				c.setCellValue("计算机系");
			}
			i++;
		}
		i=0;
		//获得模板的第七行
		XSSFRow row7 = sheet.getRow(6);
		//对第七行的具体数据进行赋值操作
		Iterator<Cell> it7 = row7.cellIterator();
		while(it7.hasNext()){
			//获得模板的第3、5列
			Cell c = it7.next();
			if(i==2){
				c.setCellValue("-");
			}
			if(i==4){
				c.setCellValue("-");
			}
			i++;
		}
		i=0;
		//获得模板的第八行
		XSSFRow row8 = sheet.getRow(7);
		//对第八行的具体数据进行赋值操作
		Iterator<Cell> it8 = row8.cellIterator();
		while(it8.hasNext()){
			//获得模板的第2列
			Cell c = it8.next();
			if(i==1){
				c.setCellValue("-");
			}
			i++;
		}
		i=0;
		//获得模板的第十行
		XSSFRow row10 = sheet.getRow(9);
		//对第十行的具体数据进行赋值操作
		Iterator<Cell> it10 = row10.cellIterator();
		while(it10.hasNext()){
			//获得模板的第2列
			Cell c = it10.next();
			if(i==1){
				c.setCellValue("这是我的简历");
			}
			i++;
		}
		i=0;
		wb.write(sos);
		wb.close();
	    sos.flush();
	    sos.close();
	}
	
	//设置Excel模板
	public XSSFCellStyle setStyle(XSSFWorkbook workbook) {  
        //设置字体;  
        XSSFFont font = workbook.createFont();  
        //设置字体大小;  
        font.setFontHeightInPoints((short) 20);  
        //设置字体名字;  
        font.setFontName("Courier New");  
        //font.setItalic(true);  
        //font.setStrikeout(true);  
        //设置样式;  
        XSSFCellStyle style = workbook.createCellStyle();  
        //设置底边框;  
        style.setBorderBottom(BorderStyle.THIN);  
        //设置底边框颜色;  
        style.setBottomBorderColor(new XSSFColor(Color.BLACK,new DefaultIndexedColorMap()));  
        //设置左边框;  
        style.setBorderLeft(BorderStyle.THIN);  
        //设置左边框颜色;  
        style.setLeftBorderColor(new XSSFColor(Color.BLACK,new DefaultIndexedColorMap()));  
        //设置右边框;  
        style.setBorderRight(BorderStyle.THIN);  
        //设置右边框颜色;  
        style.setRightBorderColor(new XSSFColor(Color.BLACK,new DefaultIndexedColorMap()));  
        //设置顶边框;  
        style.setBorderTop(BorderStyle.THIN);  
        //设置顶边框颜色;  
        style.setTopBorderColor(new XSSFColor(Color.BLACK,new DefaultIndexedColorMap()));  
        //在样式用应用设置的字体;  
        style.setFont(font);  
        //设置自动换行;  
        style.setWrapText(false);  
        //设置水平对齐的样式为居中对齐;  
        style.setAlignment(HorizontalAlignment.CENTER);  
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);  
        return style;  
    }
	
	//读Excel
	public void readExcel(String filePth) throws Exception {
		InputStream is = new FileInputStream(filePth);
		//创建工作薄
		XSSFWorkbook hwb = new XSSFWorkbook(is);
		//得到sheet
		for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
			XSSFSheet sheet = hwb.getSheetAt(i);
			int rows = sheet.getPhysicalNumberOfRows();
			//遍历每一行
			for (int j = 0; j < rows; j++) {
				XSSFRow hr = sheet.getRow(j);
				Iterator<?> it = hr.iterator();
				while(it.hasNext()){
					String context = it.next().toString();
					System.out.println(context);
				}
			}
		}
		hwb.close();
	}
	
	//插入图片到excel（只是个例子）
	@Deprecated
	public static void insertPictureToExcel(File pictureFile,File excelFile,String sheetName) throws Exception {
		FileInputStream fis = new FileInputStream(pictureFile);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();
        OutputStream os = new FileOutputStream(excelFile);
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 
        		                                       0, 
        		                                       0, 
        		                                       0, 
        		                                (short)3,//横着数第3个单元格开始（左上角） 
        		                                       3,//竖着数第3个单元格开始（左上角）
        		                                (short)9,//横着数第9个单元格结束（右下角）  
        		                                       9);//横着数第9个单元格结束（右下角）  
        patriarch.createPicture(anchor, workbook.addPicture(bytes,HSSFWorkbook.PICTURE_TYPE_PNG)); 
        workbook.write(os);
        workbook.close();
        os.close();
	}
	
}
