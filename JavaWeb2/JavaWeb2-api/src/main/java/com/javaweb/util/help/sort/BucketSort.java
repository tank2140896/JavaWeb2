package com.javaweb.util.help.sort;

//桶排序
public class BucketSort implements BaseSort<Integer> {
	
	private int numMinZone = 0;
	
	private int numMaxZone = 100;

	/**
	<<算法导论>>的伪代码如下:
	BUCKET-SORT(A)
	n=A.length
	let B[0..n-1] be a new array
	for i=0 to n-1
		make B[i] an empty list
	for i=1 to n
		insert A[i] into list B[范围[nA[i]]
	for i=0 to n-1
		sort list B[i] with insertion sort 
	concatenate the lists B[0],B[1],...,B[n-1] together in order
	*/
	//此处实现与算法导论中的实现有差异
	public Integer[] sort(Integer[] array) {
		int bucketArray[] = new int[numMaxZone-numMinZone+1];
		int distance = 0-numMinZone;
		int bucketElement = 0;
		if(distance<0){
			distance = 0;
		}
		//将元素放入桶中
		for(int i=0;i<array.length;i++) {
			bucketElement = array[i]+distance;
			bucketArray[bucketElement] = ++bucketArray[bucketElement];
		}
		//从桶中取出元素
		int count = 0;
		for(int i=0;i<bucketArray.length;i++) {
			if(bucketArray[i]!=0){
				for(int j=1;j<=bucketArray[i];j++){
					array[count++] = i-distance;
				}
			}
		}
		return array;
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
