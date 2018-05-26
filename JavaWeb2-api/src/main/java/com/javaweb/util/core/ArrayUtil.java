package com.javaweb.util.core;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.javaweb.exception.MatrixException;
import com.javaweb.util.entity.MaxMin;
import com.javaweb.util.help.sort.QuickSort;

public class ArrayUtil {
	
	public enum ValueResult {
		MAX_NUM,MIN_NUM
	}
	
	//获取数组中的最大值或最小值
	public static Integer getMaxOrMinNumInArray(Integer array[],ValueResult valueResult){
		if(array==null||array.length==0){
			return null;
		}
		int maxMinNum = array[0];
		if(valueResult==ValueResult.MAX_NUM){
			for(int i=1;i<array.length;i++){
				if(maxMinNum<array[i]){
					maxMinNum = array[i];
				}
			}
		}else{
			for(int i=1;i<array.length;i++){
				if(maxMinNum>array[i]){
					maxMinNum = array[i];
				}
			}
		}
		return maxMinNum;
	}
	
	//获取数组中的最大值和最小值
	public static MaxMin<Integer> getMaxAndMinNumInArray(Integer array[]){
		if(array==null||array.length==0){
			return null;
		}
		if(array.length==1){
			return new MaxMin<Integer>(array[0],array[0]);
		}
		int minNum = array[0];
		int maxNum = array[1];
		if(minNum>maxNum){
			maxNum=minNum^maxNum;
			minNum=minNum^maxNum;
			maxNum=minNum^maxNum;
		}
		for(int i=2;i<array.length;){
			if(i+1<array.length){//表示还剩2个数
				int insideMinNum = array[i];
				int insideMaxNum = array[i+1];
				if(insideMinNum>insideMaxNum){
					insideMaxNum=insideMinNum^insideMaxNum;
					insideMinNum=insideMinNum^insideMaxNum;
					insideMaxNum=insideMinNum^insideMaxNum;
				}
				if(minNum>insideMinNum){
					minNum = insideMinNum;
				}
				if(maxNum<insideMaxNum){
					maxNum = insideMaxNum;
				}
				i+=2;
			}else{//表示只剩一个数
				if(array[i]<minNum){
					minNum = array[i];
				}
				if(array[i]>maxNum){
					maxNum = array[i];
				}
				i+=1;
			}
		}
		return new MaxMin<Integer>(minNum,maxNum);
	}
	
	//从集合中随机抽取N个数
	public static List<Object> getRandomNum(List<Object> list,long size){
		return new Random().ints(size,0,list.size()).mapToObj(list::get/*remove*/)/*.distinct()*/.collect(Collectors.toList());
	}
	
	//获取两个数组的交集
	public static int[] getArrayIntersection(int a[],int b[]) {
		return Arrays.stream(a).flatMap(i->Arrays.stream(b).filter(j->i==j)).distinct().toArray();
	}
	
	//获取两个数组的并集
	public static int[] getArrayUnion(int a[],int b[]){
		return IntStream.concat(Arrays.stream(a),Arrays.stream(b)).distinct().toArray();
	}
	
	/**
	<<算法导论>>的伪代码如下:
	n=A.rows
	let C be a new n*n matrix
	for i=1 to n
		for j=1 to n
			c[i,j] = 0
			for k=1 to n
				c[i,j]=c[i,j]+a[i,k]+b[k,j]
	return C
	*/
	//矩阵相乘(行乘以列)  
    public static int[][] matrixMultiplication(int a[][],int b[][]) throws MatrixException {
        final int aLength = a.length;
        final int bLength = b[0].length;
        //a的列数要与b的行数相同  
        if(aLength!=bLength){  
            throw new MatrixException("第一个矩阵的列数与第二个矩阵的行数不同"); 
        } 
        int newArray[][] = new int[aLength][aLength];
        for(int i=0;i<aLength;i++){
        	for(int j=0;j<aLength;j++){
        		newArray[i][j] = 0;
        		for(int k=0;k<b.length;k++){
        			newArray[i][j] += a[i][k]*b[k][j];
        		}
        	}
        }
        return newArray;
    }
    
    //矩阵置换(行列置换)  
    public static int[][] maxtrixPermutation(int beforeArray[][]){  
        int newArray[][] = new int[beforeArray[0].length][beforeArray.length];  
        for (int i = 0; i < newArray.length; i++) {  
            for (int j = 0; j < newArray[i].length; j++) {  
                newArray[i][j] = beforeArray[j][i];  
            }  
        }  
        return newArray;  
    }
	
	//获得数组的最大子数组
	public static int[] getMaxSubarray(int[] array,int low,int high){
		int newArray[] = findMaximumSubarray(array, low, high);
		return Arrays.copyOfRange(array, newArray[0], newArray[1]+1);
	}
	
	/**
	<<算法导论>>的伪代码如下:
	FIND-MAXIMUM-SUBARRAY(A,low,high)
	if high==low
		return (low,high,A[low])
	else 
		mid=(low+high)/2
		(left-low,left-high,left-sum)=FIND-MAXIMUM-SUBARRAY(A,low,mid)
		(right-low,right-high,right-sum)=FIND-MAXIMUM-SUBARRAY(A,mid+1,high)
		(cross-low,cross-high,cross-sum)=FIND-MAX-CROSSING-SUBARRAY(A,low,mid,high)
		if left-sum>=right-sum and left-sum>=cross-sum
			return (left-low,left-high,left-sum)
		elseif right-sum>=left-sum and right-sum>=cross-sum
			return (right-low,right-high,right-sum)
		else 
			return (cross-low,cross-high,cross-sum)
	*/
	/**
	 * [1,2,3,4,5]的情况:
	 * 左:[0,0,1],中:[0,1,3],右:[1,1,2]
	 * 左:[0,1,3],中:[0,2,6],右:[2,2,3]
	 * 左:[3,3,4],中:[3,4,9],右:[4,4,5]
	 * 左:[0,2,6],中:[0,4,15],右:[3,4,9]
	 * [6,-7,3,15,1,-2]的情况:
	 * 左:[0,0,6],中:[0,1,-1],右:[1,1,-7]
     * 左:[0,0,6],中:[0,2,2],右:[2,2,3]
	 * 左:[3,3,15],中:[3,4,16],右:[4,4,1]
     * 左:[3,4,16],中:[3,5,14],右:[5,5,-2]
     * 左:[0,0,6],中:[2,4,19],右:[3,4,16]
     * 这里稍微不大好理解的是[2,4,19],出现这个结果是根据[6,-7,3,15,1,-2]整个数组(low:0,mid:2,high:5)调用方法findMaxCrossingSubarray得出来的,
     * 在<<算法导论>>中其原理解释理解为:一个数组的最大子数据基本可认为只有三种情况,把整个数组一分二,最大子数组要么在分界线的左侧;要么在分界线右侧;要么横跨分界线
     * [6,-7,3,15,1,-2]
     *     ←左|右→         
	 */
	//获得数组的最大子数组(一句话攻略:从上到下将数组不断对半分直致数组长度为最小1,对半分后一左一右,合起就是中,左中右比较取最大的,中计算稍特殊)
	public static int[] findMaximumSubarray(int[] array,int low,int high){
		if(low==high){
			return new int[]{low,high,array[low]};
		}else{
			int mid = (low+high)/2;
			int left[]  = findMaximumSubarray(array, low, mid);
			int right[] = findMaximumSubarray(array, mid+1, high);
			int cross[] = findMaxCrossingSubarray(array, low, mid, high);
			if(left[2]>=right[2]&&left[2]>=cross[2]){
				return left;
			}else if(right[2]>=left[2]&&right[2]>=cross[2]){
				return right;
			}else{
				return cross;
			}
		}
	}
	
	/**
	<<算法导论>>的伪代码如下:
	FIND-MAX-CROSSING-SUBARRAY(A,low,mid,high)
	left-sum=负无穷(-∞)
	sum=0
	for i=mid downto low
		sum=sum+A[i]
		if sum>left-sum
			left-sum=sum
			max-left=i
	right-sum=负无穷(-∞)
	sum=0
	for j=mid+1 to high
		sum=sum+A[j]
		if sum>right-sum
			right-sum=sum
			max-right=j
	return (max-left,max-right,(left-sum)+(right-sum))
	*/
	private static int[] findMaxCrossingSubarray(int[] array,int low,int mid,int high){
		int leftFlag = low,rightFlag = mid+1,leftSum = Integer.MIN_VALUE,rightSum = Integer.MIN_VALUE,sum=0;
		for (int i=mid;i>=low;i--) {
			sum+=array[i];
			if(sum>leftSum){
				leftSum = sum;
				leftFlag = i;
			}
		}
		sum=0;
		for (int j=mid+1;j<=high;j++) {
			sum+=array[j];
			if(sum>rightSum){
				rightSum = sum;
				rightFlag = j;
			}
		}
		return new int[]{leftFlag,rightFlag,leftSum+rightSum};
	}
	
	//获取数组中第i小的元素
	public static int getTheElementOfTheArrayI(Integer array[],int i){
		//return new QuickSort().sort(array)[i-1];
		return randomizedSelect(array,0,array.length-1,i);
	}
	
	/**
	<<算法导论>>的伪代码如下:
	RANDOMIZED-SELECT(A,p,r,i)
	if p==r
		return A[p]
	q=RANDOMIZED-PARTITION(A,p,r)
	k=q-p+1
	if i==k
		return A[q]
	else if i<k
		return RANDOMIZED-SELECT(A,p,q-1,i)
	else return RANDOMIZED-SELECT(A,q+1,r,i-k)
	*/
	public static int randomizedSelect(Integer array[],int startIndex,int endIndex,int i){
		if(startIndex==endIndex){
			return array[startIndex];
		}
		int partition = new QuickSort().partition(array,startIndex,endIndex);
		int k = partition-startIndex+1;
		if(i==k){
			return array[partition];
		}else if(i<k){
			return randomizedSelect(array,startIndex,partition-1,i);
		}else{
			return randomizedSelect(array,partition+1,endIndex,i-k);
		}
	}

}
