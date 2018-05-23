package com.javaweb.util.help.sort;

import java.util.Arrays;

//归并排序
public class MergeSort implements BaseSort<Integer> {

	/**
	<<算法导论>>的伪代码如下:
	MERGE-SORT(A,p,r)
	if p<r
		q=(p+r)/2
		MERGE-SORT(A,p,q)
		MERGE-SORT(A,q+1,r)
		MERGE(A,p,q,r)
	*/
	//递归调用,将数组大小二分二分再二分,直致数组长度为最小1
	public Integer[] sort(Integer[] array) {
		final int arrayLength = array.length;
		if(arrayLength!=1){//递归终止条件
			int mid = arrayLength/2;
			Integer leftArray[] = Arrays.copyOfRange(array,0,mid);
			Integer rightArray[] = Arrays.copyOfRange(array,mid,arrayLength);
			leftArray = sort(leftArray);
			rightArray = sort(rightArray);
			//合并左半部分数组和右半部分数组为一个新的数组,且左右两半数组均已排好序
			Integer newArray[] = new Integer[leftArray.length+rightArray.length];
			System.arraycopy(leftArray, 0, newArray, 0, leftArray.length);
			System.arraycopy(rightArray, 0, newArray, leftArray.length, rightArray.length);
			array = merge(newArray);
		}
		return array;
	}
	
	/**
	<<算法导论>>的伪代码如下:
	MERGE(A,p,q,r)
	n1=q-p+1
	n2=r-q
	let L[1..n1+1] and R[1..n2+1] be new arrays
	for i=1 to n1
		L[i]=A[p+i-1]
	for j=1 to n2
		R[j]=A[q+j]
	L[n1+1]=正无穷(+∞)
	R[n2+1]=正无穷(+∞)
	i=1
	j=1
	for k=p to r
		if L[i]<=R[j]
			A[k]=L[i]
			i=i+1(i++)
		else
			A[k]=R[j]
			j=j+1(j++)
	*/
	//对两个排好序的数组进行再排序(这是一种耗空间的算法)
	public static Integer[] merge(Integer array[]){
		final int arrayLength = array.length;
		Integer tempArray[] = new Integer[arrayLength];
		final int mid = arrayLength/2;//对半位置计算
		int leftIndex = 0;//左半边起始位置
		int rightIndex = mid;//右半边起始位置
		int count = 0;
		for(int i=0;i<arrayLength;i++){
			if(leftIndex==mid){
				tempArray[count++] = array[rightIndex];
				rightIndex++;
			}else if(rightIndex==arrayLength){
				tempArray[count++] = array[leftIndex];
				leftIndex++;
			}else{
				int leftValue = array[leftIndex];
				int rightValue = array[rightIndex];
				if(leftValue>rightValue){
					tempArray[count++] = rightValue;
					rightIndex++;
				}else{
					tempArray[count++] = leftValue;
					leftIndex++;
				}
			}
		}
		return tempArray;
	}
	
}
