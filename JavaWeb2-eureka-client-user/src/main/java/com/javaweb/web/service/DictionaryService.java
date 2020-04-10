package com.javaweb.web.service;

import java.util.List;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.dictionary.DictionaryListRequest;
import com.javaweb.web.po.Dictionary;

public interface DictionaryService {
    
    public List<Dictionary> selectAll();
    
    public List<Dictionary> getDictionary(Dictionary dictionary);
    
    public void dictionaryAdd(Dictionary dictionary);
    
    public Page dictionaryList(DictionaryListRequest dictionaryListRequest);
    
    public void dictionaryModify(Dictionary dictionary);
    
    public Dictionary dictionaryDetail(String dictionaryId);
    
    public void dictionaryDelete(String dictionaryId);
	
}
