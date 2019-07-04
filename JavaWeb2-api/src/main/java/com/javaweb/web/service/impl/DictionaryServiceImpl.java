package com.javaweb.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.base.BaseService;
import com.javaweb.web.po.Dictionary;
import com.javaweb.web.service.DictionaryService;

@Service("dictionaryServiceImpl")
public class DictionaryServiceImpl extends BaseService implements DictionaryService {

    public List<Dictionary> selectAll() {
         return dictionaryDao.selectAll();
    }
	
}
