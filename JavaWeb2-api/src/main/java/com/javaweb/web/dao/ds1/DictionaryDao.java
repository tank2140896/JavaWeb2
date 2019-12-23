package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.dao.DaoForMySql;
import com.javaweb.web.po.Dictionary;

@Mapper
public interface DictionaryDao extends DaoForMySql<Dictionary> {
    
    public List<Dictionary> getDictionary(Dictionary dictionary);
	
}