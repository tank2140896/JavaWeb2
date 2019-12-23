package com.javaweb.util.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaxMin<T extends Number> {
	
	private T minNum;

	private T maxNum;
	
	public MaxMin(T minNum, T maxNum) {
		this.minNum = minNum;
		this.maxNum = maxNum;
	}

}
