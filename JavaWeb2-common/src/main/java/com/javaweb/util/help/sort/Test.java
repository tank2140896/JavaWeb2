package com.javaweb.util.help.sort;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		final Integer[] i = {4,5,6,7,3,2,1,9,8};
		Integer bubbleSortArray[] = i;
		Integer insertSortArray[] = i;
		Integer shellSortArray[] = i;
		SortTemplete<Integer> st1 = new BubbleSort<Integer>();st1.sort(bubbleSortArray);
		SortTemplete<Integer> st2 = new InsertSort<Integer>();st2.sort(insertSortArray);
		SortTemplete<Integer> st3 = new ShellSort<Integer>();st3.sort(shellSortArray);
		System.out.println(Arrays.toString(bubbleSortArray));
		System.out.println(Arrays.toString(insertSortArray));
		System.out.println(Arrays.toString(shellSortArray));
	}

}
