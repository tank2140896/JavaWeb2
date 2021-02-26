package com.javaweb.web.eo.validate;

import com.javaweb.db.query.QueryWapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnsRepeatRequest<T> {
	
	public ColumnsRepeatRequest(){
		
	}
	
	public ColumnsRepeatRequest(QueryWapper<T> queryWapper,String message){
		this.queryWapper = queryWapper;
		this.message = message;
	}
	
	private QueryWapper<T> queryWapper;
	
	private String message;

}
