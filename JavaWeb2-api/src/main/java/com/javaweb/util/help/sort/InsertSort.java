package com.javaweb.util.help.sort;

//插入排序(一句话攻略:如果说冒泡排序是正向处理的,那么插入排序就是反向处理的)
public class InsertSort implements BaseSort<Integer> {
	
	/**
	<<算法导论>>的伪代码如下:
	for j=2 to A.length
  		key = A[j]
  		i = j-1
  		while i>0 and A[i]>key
  			A[i+1] = A[i]
  			i = i-1
  		A[i+1] = key
	*/
	public Integer[] sort(Integer[] array) {
		for (int i = 1; i < array.length; i++) {
			final int key = array[i];//待插入的数
			int compareStartIndex = i-1;//需要比较的数的下标
			while(compareStartIndex>=0&&key<array[compareStartIndex]){
				array[compareStartIndex+1] = array[compareStartIndex];
				compareStartIndex--;
			}
			array[compareStartIndex+1] = key;
		}
		return array;
	}
	
	/**
	public Integer[] sort(Integer[] array) {
		if(array!=null&&array.length>1){
			for(int i=1;i<array.length;i++){
				final int key = array[i];
				int startIndex = i;
				int compareStartIndex = i-1;
				while((compareStartIndex>=0)&&(key<array[compareStartIndex])){
					array[startIndex] = array[startIndex]^array[compareStartIndex];
					array[compareStartIndex] = array[startIndex]^array[compareStartIndex];
					array[startIndex] = array[startIndex]^array[compareStartIndex];
					compareStartIndex--;
					startIndex--;
				}
			}
		}
		return array;
	}

	public Integer[] sort(Integer[] array) {
		for (int i = 1; i < array.length; i++) {
			int key = array[i];
			boolean change = false;
			int changeIndex = 0;
			for (int j = i-1; j > -1; j--) {
				if(array[j]>key){
					array[j+1] = array[j];
					change = true;
					changeIndex = j;
				}
			}
			if(change){
				array[changeIndex] = key;
			}
		}
		return array;
	}
	
	public Integer[] sort(Integer[] array) {
		for (int i = 1; i < array.length; i++) {
			int key = array[i];
			int count = i;
			while(count>0&&array[count-1]>key){
				array[count] = array[count-1];
				array[count-1] = key;
				count--;
			}
		}
		return array;
	}
	*/
	
}
