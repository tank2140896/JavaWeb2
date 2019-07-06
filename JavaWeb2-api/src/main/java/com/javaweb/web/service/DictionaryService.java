package com.javaweb.web.service;

import java.util.List;

import com.javaweb.web.po.Dictionary;

public interface DictionaryService {
    
    public List<Dictionary> selectAll();
    
    public List<Dictionary> getDictionary(Dictionary dictionary);
	
}
