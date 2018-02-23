package com.javaweb.util.help.sort;

//桶排序
public class BucketSort implements BaseSort<Integer> {
	
	public int numMinZone = 0;
	
	public int numMaxZone = 100;

	/**
	 * 桶排序有点特殊,需要设置待排序所有数字的最小值和最大值,简单用法如下:
	 * Integer array[] = new Integer[]{3,7,1,3,6};
	 * BucketSort bucketSort = new BucketSort();
	 * bucketSort.numMinZone = -3;
	 * bucketSort.numMaxZone = 10;
	 * array = SortUtil.getSort(array,bucketSort);
	 * System.out.println(Arrays.toString(array));
	 */
	public Integer[] sort(Integer[] array) {
		int bucketArray[] = new int[numMaxZone-numMinZone+2];
		int distance = 0-numMinZone;
		int bucketElement = 0;
		if(distance<0){
			distance = 0;
		}
		//将元素放入桶中
		for (int i = 0; i < array.length; i++) {
			bucketElement = array[i]+distance;
			bucketArray[bucketElement] = ++bucketArray[bucketElement];
		}
		//从桶中取出元素
		int count = 0;
		for (int i = 0; i < bucketArray.length; i++) {
			if(bucketArray[i]!=0){
				for(int j=1;j<=bucketArray[i];j++){
					array[count++] = i-distance;
				}
			}
		}
		return array;
	}
	
}
