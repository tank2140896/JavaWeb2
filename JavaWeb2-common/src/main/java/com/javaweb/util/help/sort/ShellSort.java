package com.javaweb.util.help.sort;

//希尔排序（指定一个间隔如5，然后按照每隔5的间隔分成若干组，每组插入排序，然后再选取间隔如3，直到1）
public class ShellSort<T> implements SortTemplete<T> {
	
	public void sort(Comparable<T>[] array) {
		final int arrayLength = array.length;
		int h = 1;
		while(h<arrayLength/3){//初始间隔最大值不应该超过数组总长度的1/3
			h = 3*h+1;//间隔遵循1、4、13、40...为什么要加1？因为要保证最后一个值永远是1
		}
		while(h>=1){
			for(int i=h;i<arrayLength;i++){//数组h有序
				for(int j=i;j>=h&&less(array[j],array[j-h]);j-=h){//将array[i]插入到array[i-h],array[i-2*h],array[i-3*h]...之中
					exchange(array,j,j-h);
				}
			}
			h=h/3;
		}
	}
	
}
