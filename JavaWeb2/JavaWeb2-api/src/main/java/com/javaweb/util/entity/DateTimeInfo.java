package com.javaweb.util.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateTimeInfo {
	
	private Integer year;
	
	private Integer yearOfMonth;
	
	private Integer dayOfMonth;
	
	private Integer dayOfWeek;
	
	private Integer hourOfDay;
	
	private Integer minuteOfHour;
	
	private Integer secondOfMinute;
	
	private Integer totalDayOfMonth;
	
	private Boolean isLeapYear;

}
