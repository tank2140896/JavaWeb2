package com.javaweb.util.help.sort;

//计数排序
public class CountSort implements BaseSort<Integer> {
	
	private int numMinZone = 0;
	
	private int numMaxZone = 100;

	/**
	<<算法导论>>的伪代码如下:
	COUNT-SORT(A,B,k)
	let C[0..k] be a new array
	for i=0 to k
		C[i]=0
	for j=1 to A.length
		C[A[j]]=C[A[j]]+1
	for i=1 to k
		C[i]=C[i]+C[i-1]
	for j=A.length downto 1
		B[C[A[j]]]=A[j]
		C[A[j]]=C[A[j]]-1
	*/
	public Integer[] sort(Integer[] array) {
		Integer countArray[] = new Integer[array.length];//最终输出的排序数组
		int tmpArray[] = new int[numMaxZone-numMinZone+1];//临时存储空间数组
		int distance = 0-numMinZone;//distance是为了处理负数的
		int countElement = 0;
		if(distance<0){
			distance = 0;
		}
		//经过该步,tmpArray数组下标即为排序的数字,而下标所在的值即表示有几个排序的数字,例如tmpArray[2]=3,即表示排序数字2有3个
		for(int i=0;i<array.length;i++) {
			countElement = array[i]+distance;
			array[i] = countElement;
			tmpArray[countElement] = ++tmpArray[countElement];
		}
		//经过该步,例如tmpArray[2]=4,即表示小于等于排序数字2的有4个
		for(int i=1;i<tmpArray.length;i++){//去掉第一个
			tmpArray[i]+=tmpArray[i-1];
		}
		//关键步骤(个人认为算法导论这步有点绕了,没必要这么麻烦)
		for(int i=array.length-1;i>=0;i--){
			countArray[tmpArray[array[i]]-1] = array[i]-distance;
			tmpArray[array[i]]=tmpArray[array[i]]-1;
		}
		return countArray;
	}
	
	public int getNumMinZone() {
		return numMinZone;
	}

	public void setNumMinZone(int numMinZone) {
		this.numMinZone = numMinZone;
	}

	public int getNumMaxZone() {
		return numMaxZone;
	}

	public void setNumMaxZone(int numMaxZone) {
		this.numMaxZone = numMaxZone;
	}
	
}
