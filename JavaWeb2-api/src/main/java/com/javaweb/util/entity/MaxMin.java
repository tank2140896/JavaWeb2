package com.javaweb.util.entity;

public class MaxMin<T extends Number> {
	
	private T minNum;

	private T maxNum;
	
	public MaxMin(T minNum, T maxNum) {
		this.minNum = minNum;
		this.maxNum = maxNum;
	}

	public T getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(T maxNum) {
		this.maxNum = maxNum;
	}

	public T getMinNum() {
		return minNum;
	}

	public void setMinNum(T minNum) {
		this.minNum = minNum;
	}
	
}
