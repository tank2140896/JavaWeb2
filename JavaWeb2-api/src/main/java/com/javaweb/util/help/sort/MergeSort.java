package com.javaweb.util.help.sort;

import java.util.Arrays;

//归并排序(一句话攻略:从上到下将数组不断对半分直致数组长度为最小1,从下到上将两两有序数组排序)
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
	/**
	 * 关于两个排好序的数组再排序是这样的:
	 * A[95,100,102,99,101,103]
	 * L[95,100,102];R[99,101,103]
	 * 比较规则:若L大于R(>)则置换;左边的数比右边最小的还要小,则终止比较;最多比较两数组的大小乘积,最少比较左数组的大小
	 * 第一次:L0 VS R0 (哨兵为95)  -> L[95,100,102] R[99,101,103] 
	 * 第二次:L1 VS R0 (哨兵为100) -> L[95,99,102]  R[100,101,103]
	 * 第三次:R0 VS R1 (哨兵为100) -> L[95,99,102]  R[100,101,103]
	 * 第四次:L2 VS R0 (哨兵为99)  -> L[95,99,102]  R[100,101,103]
	 * 第五次:L3 VS R0 (哨兵为102) -> L[95,99,100]  R[102,101,103]
	 * 第六次:R0 VS R1 (哨兵为102) -> L[95,99,100]  R[101,102,103]
	 */
	//对两个排好序的数组进行再排序
	private static Integer[] merge(Integer array[]){
		final int arrayLength = array.length;
		int mid = arrayLength/2;//mid:2个数,则mid=1;3个数则mid=3/2=1
		int sentry = 0,leftPoint = 0,rightPoint = mid;//sentry:哨兵元素;leftPoint:左指针;rightPoint:右指针
		for(int i=0;i<mid;i++){
			sentry = array[i];
			for(int j=mid;j<arrayLength;j++){
				if(sentry>array[j]){//从小到大
					array[leftPoint] = array[leftPoint]^array[rightPoint];
					array[rightPoint] = array[leftPoint]^array[rightPoint];
					array[leftPoint] = array[leftPoint]^array[rightPoint];
					leftPoint = j;
					rightPoint++;
				}else{
					break;
				}
			}
			leftPoint = i+1;
			rightPoint = mid;
		}
		return array;
	}
	
	/**
	 * 对两个有序数组进行排序
	 * 思路:[3,5]和[2,4],建一空数组[0,0,0,0]
	 * 第一次:3>2 [2,0,0,0] =>[3,5] [4]
	 * 第二次:3<4 [2,3,0,0] =>[5] [4]
	 * 第三次:5>4 [2,3,4,5] =>[] []
	 */
	/**
	private static int[] merge(int left[],int right[]){
		int leftLength = left.length,rightLength = right.length;
		int leftStart = 0,rightStart = 0;
		int temp[] = new int[leftLength+rightLength];
		int tempLength = temp.length;
		for (int i = 0; i < tempLength; i++) {
			if(leftStart>leftLength-1){
				temp[i] = right[rightStart];
				rightStart++;
			}else if(rightStart>rightLength-1){
				temp[i] = left[leftStart];
				leftStart++;
			}else{
				if(left[leftStart]>right[rightStart]){
					temp[i] = right[rightStart];
					rightStart++;
				}else{
					temp[i] = left[leftStart];
					leftStart++;
				}
			}
		}
		return temp;
	} 
	*/

}
