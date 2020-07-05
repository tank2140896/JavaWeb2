package com.javaweb.util.help.sort;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		final Integer[] i = {4,5,6,7,3,2,1,9,8};
		Integer selectSortArray[] = i;
		Integer insertSortArray[] = i;
		SortTemplete<Integer> st1 = new SelectSort<Integer>();st1.sort(selectSortArray);
		SortTemplete<Integer> st2 = new InsertSort<Integer>();st2.sort(insertSortArray);
		System.out.println(Arrays.toString(selectSortArray));
		System.out.println(Arrays.toString(insertSortArray));
	}

}
