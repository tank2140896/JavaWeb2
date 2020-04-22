package com.javaweb.util.core;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
	
	//分页数显示
	public static List<Long> getShowPages(Long currentPage,Long totalPage,Long minShow){
		List<Long> list = new ArrayList<>();
		if(totalPage<=minShow){
			for(long i=1;i<=totalPage;i++){
				list.add(i);
			}
		}else{
			long bothSideShow = (minShow%2==0)?(minShow/2):((minShow-1)/2);
			if(currentPage-bothSideShow<1){//偏左
				for(long i=1;i<=minShow;i++){
					list.add(i);
				}
			}else if(currentPage+bothSideShow>totalPage){//偏右
				for(long i=(totalPage-minShow+1);i<=totalPage;i++){
					list.add(i);
				}
			}else{
				if(minShow%2==0){
					for(long i=currentPage-bothSideShow;i<currentPage+bothSideShow;i++){
						list.add(i);
					}
				}else{
					for(long i=currentPage-bothSideShow;i<=currentPage+bothSideShow;i++){
						list.add(i);
					}
				}
			}
		}
		return list;
	}
	
	//获取总页数
	public static long getTotalPage(long totalSize,long pageSize) {
		if(pageSize!=0){
			return (long) Math.ceil((double)totalSize/pageSize);
		}
		return pageSize;
	}
	
	//获取数据截取开始位
	public static Long getSubStart(long currentPage,long pageSize) {
		if(currentPage==1){
			return 0L;
		}else{
			return (currentPage-1)*pageSize;
		}
	}

	//获取数据截取结束位
	public static Long getSubEnd(long currentPage,long pageSize,long totalSize) {
		Long endStart;
		if(currentPage==1){
			endStart = pageSize;
		}else{
			endStart = currentPage*pageSize;
		}
		if(endStart<=totalSize){
			return endStart;
		}else{
			return totalSize;
		}
	}
	
	//数组截取
	public static <T> List<T> getSubList(List<T> list,long pageSize,long currentPage){
	    if(list==null||list.size()==0){
        	return null;
		}
		int startIndex = Integer.parseInt(getSubStart(currentPage, pageSize).toString());
		int endIndex = Integer.parseInt(getSubEnd(currentPage, pageSize,list.size()).toString());
		if(startIndex>endIndex){
			return null;
		}
		return list.subList(startIndex,endIndex);
	}

}
