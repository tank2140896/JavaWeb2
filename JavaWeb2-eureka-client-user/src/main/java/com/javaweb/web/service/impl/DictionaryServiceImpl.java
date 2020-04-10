package com.javaweb.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.base.BaseService;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.dictionary.DictionaryListRequest;
import com.javaweb.web.po.Dictionary;
import com.javaweb.web.service.DictionaryService;

@Service("dictionaryServiceImpl")
public class DictionaryServiceImpl extends BaseService implements DictionaryService {

    public List<Dictionary> selectAll() {
         return dictionaryDao.selectAllForMySql();
    }

    public List<Dictionary> getDictionary(Dictionary dictionary) {
         return dictionaryDao.getDictionary(dictionary);
    }

    @Transactional
	public void dictionaryAdd(Dictionary dictionary) {
		dictionaryDao.insertForMySql(dictionary);
	}
	
	public Page dictionaryList(DictionaryListRequest dictionaryListRequest){
		List<Dictionary> list = dictionaryDao.dictionaryList(dictionaryListRequest);
		long count = dictionaryDao.dictionaryCount(dictionaryListRequest);
		Page page = new Page(dictionaryListRequest,list,count);
		return page;
	}

	@Transactional
	public void dictionaryModify(Dictionary dictionary) {
		dictionaryDao.updateForMySql(dictionary);
	}

	public Dictionary dictionaryDetail(String dictionaryId) {
		return dictionaryDao.selectByPkForMySql(dictionaryId);
	}

	@Transactional
	public void dictionaryDelete(String dictionaryId) {
		String dictionaryIds[] = dictionaryId.split(",");
		for(String id:dictionaryIds){
			dictionaryDao.deleteForMySql(id);
		}
	}
	
}
