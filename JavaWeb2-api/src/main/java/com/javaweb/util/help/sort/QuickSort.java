package com.javaweb.util.help.sort;

//快速排序
public class QuickSort implements BaseSort<Integer> {

	public Integer[] sort(Integer[] array) {
		return this.sort(array,0,array.length-1);
	}
	
	/**
	<<算法导论>>的伪代码如下:
	QUICKSORT(A,p,r)
	if p<r
		q=PARTITION(A,p,r)
		QUICKSORT(A,p,q-1)
		QUICKSORT(A,q+1,r)
	*/
	private Integer[] sort(Integer[] array,int startArrayIndex,int endArrayIndex){
		if(startArrayIndex<endArrayIndex){
			int partition = partition(array,startArrayIndex,endArrayIndex);
			array = sort(array,startArrayIndex,partition-1);
			array = sort(array,partition,endArrayIndex);
		}
		return array;
	}
	
	/**
	<<算法导论>>的伪代码如下:
	PARTITION(A,p,r)
	x=A[r]
	i=p-1
	for j=p to r-1
		if A[j]<=x
			i=i+1
			exchange A[i] with A[j]
	exchange A[i+1] with A[j]
	return i+1
	*/
	//此处实现与算法导论中的实现有差异(所要实现的就是针对一个选取的数,所有比选取的数小的都在该选取数的左侧,所有比选取的数大的都在该选取数的右侧)
	public int partition(Integer[] array,int startIndex,int endIndex){
		//final int fixedValue = array[endIndex];//固定选取数组最靠右的一个元素
		/** 随机化版本 start */
		int fixedValue = array[endIndex];//选择的还是最后一位,但是最后一位的值已经不是原来的最后一位的值
	    if((endIndex-startIndex)/2>2){//忽略就2个数的情况
	    	int mid = startIndex+(int)(Math.random()*(endIndex-startIndex));//(endIndex-startIndex)/2;
	    	array[mid] = array[mid]^array[endIndex];
	    	array[endIndex] = array[mid]^array[endIndex];
	    	array[mid] = array[mid]^array[endIndex];
	    	fixedValue = array[endIndex];
	    }
	    /** 随机化版本 end */
		int leftPosition = startIndex;//左侧下标位
		int rightPosition = endIndex;//右侧下标位
		boolean leftTurn = true;
		for(int i=startIndex;i<=endIndex;i++){//仅用startIndex和endIndex来确定数组遍历范围
			if(leftPosition<rightPosition){
				if(leftTurn){
					if(array[leftPosition]>fixedValue){
						array[leftPosition] = array[leftPosition]^array[rightPosition];
						array[rightPosition] = array[leftPosition]^array[rightPosition];
						array[leftPosition] = array[leftPosition]^array[rightPosition];
						rightPosition--;
						leftTurn = false;
					}else{
						leftPosition++;
					}
				}else{
					if(array[rightPosition]<=fixedValue){
						array[leftPosition] = array[leftPosition]^array[rightPosition];
						array[rightPosition] = array[leftPosition]^array[rightPosition];
						array[leftPosition] = array[leftPosition]^array[rightPosition];
						leftPosition++;
						leftTurn = true;
					}else{
						rightPosition--;
					}
				}
			}
		}
		return leftPosition;
	}

}
