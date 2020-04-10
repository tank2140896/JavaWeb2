package com.javaweb.web.eo.dictionary;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DictionaryListRequest implements Serializable {
    
	private static final long serialVersionUID = 1421848003510714297L;

	private String dataType;//数据类型
    
    private String keyCode;//key值
    
    private String means;//含义
    
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条
    
}
