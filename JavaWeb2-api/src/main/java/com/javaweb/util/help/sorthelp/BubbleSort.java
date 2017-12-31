package com.javaweb.util.help.sorthelp;

//冒泡排序(一句话攻略:abcde,拿a出来和bcde比,拿b出来和cde比,拿c出来和de比,拿d出来和e比)
public class BubbleSort implements BaseSort<Integer> {

	/**
		<<算法导论>>的伪代码如下:
		for i=1 to A.length-1
			for j=A.length downto i+1
				if A[j]<A[j-1]
					exchange A[j] with A[j-1]
	 */
	public Integer[] sort(Integer[] array) {
		final int toSortLength = array.length;
		for (int i = 0; i < toSortLength; i++) {
			for (int j = i+1; j < toSortLength; j++) {
				if(array[i]>array[j]){
					//两数交换
					array[i] = array[i]^array[j];
					array[j] = array[i]^array[j];
					array[i] = array[i]^array[j];
				}
			}
		}
		return array;
	}

}
