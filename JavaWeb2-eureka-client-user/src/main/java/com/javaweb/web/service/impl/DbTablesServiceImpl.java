package com.javaweb.web.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.javaweb.base.BaseService;
import com.javaweb.constant.CommonConstant;
import com.javaweb.util.core.FreemarkerUtil;
import com.javaweb.util.core.PageUtil;
import com.javaweb.util.core.StringUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.dbTables.DbTablesCodeGenerateResponse;
import com.javaweb.web.eo.dbTables.DbTablesColumnListResponse;
import com.javaweb.web.eo.dbTables.DbTablesListRequest;
import com.javaweb.web.eo.dbTables.DbTablesListResponse;
import com.javaweb.web.service.DbTablesService;

@Service("dbTablesServiceImpl")
public class DbTablesServiceImpl extends BaseService implements DbTablesService {

	public Page dbTablesList(DbTablesListRequest dbTablesListRequest) {
		List<String> tablelist = dbTablesDao.getTableList();
		if(!(StringUtil.handleNullString(dbTablesListRequest.getTableName()).equals(CommonConstant.EMPTY_VALUE))){
			tablelist = tablelist.stream().filter(e->e.contains(dbTablesListRequest.getTableName())).collect(Collectors.toList());
		}
		long count = tablelist.size();
		tablelist = PageUtil.getSubList(tablelist,dbTablesListRequest.getPageSize(),dbTablesListRequest.getCurrentPage());
		List<DbTablesListResponse> list = new ArrayList<>();
		if(tablelist!=null&&tablelist.size()>0){
			list = dbTablesDao.getTableInfo(tablelist).stream().sorted(new Comparator<DbTablesListResponse>() {
				public int compare(DbTablesListResponse o1, DbTablesListResponse o2) {
					return o1.getTableName().hashCode()>o2.getTableName().hashCode()?0:1;
				}
			}).collect(Collectors.toList());
		}
		Page page = new Page(dbTablesListRequest,list,count);
		return page;
	}
	
	public List<DbTablesColumnListResponse> getTableColumnInfo(String tableName) {
		List<DbTablesColumnListResponse> result = new ArrayList<>();
		List<Map<String,Object>> list = dbTablesDao.getTableColumnInfo(tableName);
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			DbTablesColumnListResponse dbTablesColumnListResponse = new DbTablesColumnListResponse();
			dbTablesColumnListResponse.setField(map.get("COLUMN_NAME").toString());
			dbTablesColumnListResponse.setType(map.get("COLUMN_TYPE")==null?null:String.valueOf(map.get("COLUMN_TYPE")));
			dbTablesColumnListResponse.setIsNull(map.get("IS_NULLABLE")==null?null:String.valueOf(map.get("IS_NULLABLE")));
			dbTablesColumnListResponse.setComment(map.get("COLUMN_COMMENT")==null?null:String.valueOf(map.get("COLUMN_COMMENT")));
			dbTablesColumnListResponse.setDefaultValue(map.get("COLUMN_DEFAULT")==null?null:String.valueOf(map.get("COLUMN_DEFAULT")));
			dbTablesColumnListResponse.setIsKey(map.get("COLUMN_KEY")==null?null:String.valueOf(map.get("COLUMN_KEY")));
			result.add(dbTablesColumnListResponse);
		}
		return result;
	}

	public void codeGenerate(String tableName,HttpServletResponse httpServletResponse) {
		String tableNames[] = tableName.split(CommonConstant.UNDERLINE);
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0;i<tableNames.length;i++){
			stringBuilder.append(tableNames[i].substring(0,1).toUpperCase()).append(tableNames[i].substring(1,tableNames[i].length()));
		}
		String className = stringBuilder.toString();
		List<DbTablesCodeGenerateResponse> result = new ArrayList<>();
		List<Map<String,Object>> list = dbTablesDao.getTableColumnInfo(tableName);
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			DbTablesCodeGenerateResponse dbTablesCodeGenerateResponse = new DbTablesCodeGenerateResponse();
			dbTablesCodeGenerateResponse.setTableColumn(map.get("COLUMN_NAME").toString());
			dbTablesCodeGenerateResponse.setKey((map.get("COLUMN_KEY")!=null&&map.get("COLUMN_KEY").equals("PRI"))?true:false);
			stringBuilder = new StringBuilder();
			String fields[] = map.get("COLUMN_NAME").toString().split(CommonConstant.UNDERLINE);
			for(int j=0;j<fields.length;j++){
				if(j==0){
					stringBuilder.append(fields[j]);
				}else{
					stringBuilder.append(fields[j].substring(0,1).toUpperCase()).append(fields[j].substring(1,fields[j].length()));
				}
			}
			dbTablesCodeGenerateResponse.setJavaPropertyName(stringBuilder.toString());
			String type = map.get("COLUMN_TYPE")==null?null:String.valueOf(map.get("COLUMN_TYPE"));
			if(type==null){
				type = "String";
			}else{
				if(type.toLowerCase().contains("varchar")){
					type = "String";
				}else if(type.toLowerCase().contains("int")){
					type = "Integer";
				}else if(type.toLowerCase().contains("long")){
					type = "Long";
				}else{
					type = "String";
				}
			}
			dbTablesCodeGenerateResponse.setJavaType(type);
			result.add(dbTablesCodeGenerateResponse);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("tableName",tableName);
		map.put("className",className);
		map.put("propertyList",result);
		String templateFileName[] = {"dao.ftl","mapper.ftl","po.ftl","serviceImpl.ftl","service.ftl"};
		try{
			ZipOutputStream zipOutputStream = new ZipOutputStream(httpServletResponse.getOutputStream());
			FreemarkerUtil.freemarkerForDbTablesCodeGenerate(map,templateFileName,tableName,zipOutputStream);
		}catch(Exception e){
			//do nothing
		}
	}
	
}
