package com.javaweb.util.core;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.javaweb.util.entity.DateTimeInfo;

/**
 * LocalDate         LocalTime         ZoneId
 * LocalDateTime..............
 * ZonedDateTime.............................
 * 日期时间加减的链式处理：LocalDate localDate = LocalDate.of(2017,3,24).with(ChronoField.YEAR,2020).plusYears(3).minusDays(2);//2023-03-22
 * 设置时间用with方法即可，但是用完要替换原值，如：
 * LocalDateTime localDateTime = LocalDateTime.now();
 * localDateTime = localDateTime.with(ChronoField.YEAR,2020);
 */
public class DateUtil {
	
	//默认每月第一天是1号
	public static final int FIRST_DAY_OF_MONTH = 1;
	
	//日期格式(年月日时分秒)
	public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_PATTERN_TYPE1 = "yyyyMMddHHmmss";
	public static final String DATETIME_PATTERN_TYPE2 = "yyyyMMddHHmmssSSS";
	
	//日期格式(年月日)
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DATE_PATTERN_TYPE1 = "yyyyMMdd";
	
	//日期格式(时分秒)
	public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
	public static final String TIME_PATTERN_TYPE1 = "HHmmss";
	public static final String TIME_PATTERN_TYPE2 = "HHmmssSSS";
	
	//获取年月日信息
	public static DateTimeInfo getDateInfo(LocalDate localDate){
		DateTimeInfo dateTimeInfo = new DateTimeInfo();
		dateTimeInfo.setYear(localDate.get(ChronoField.YEAR));//年
		dateTimeInfo.setYearOfMonth(localDate.get(ChronoField.MONTH_OF_YEAR));//月
		dateTimeInfo.setDayOfMonth(localDate.get(ChronoField.DAY_OF_MONTH));//日
		dateTimeInfo.setDayOfWeek(localDate.get(ChronoField.DAY_OF_WEEK));//星期几
		dateTimeInfo.setTotalDayOfMonth(localDate.lengthOfMonth());//所在月总共天数
		dateTimeInfo.setIsLeapYear(localDate.isLeapYear());//是否是闰年
		return dateTimeInfo;
	}
	
	//获取时分秒信息
	public static DateTimeInfo getTimeInfo(LocalTime localTime){
		DateTimeInfo dateTimeInfo = new DateTimeInfo();
		dateTimeInfo.setHourOfDay(localTime.get(ChronoField.HOUR_OF_DAY));//时
		dateTimeInfo.setMinuteOfHour(localTime.get(ChronoField.MINUTE_OF_HOUR));//分
		dateTimeInfo.setSecondOfMinute(localTime.get(ChronoField.SECOND_OF_MINUTE));//秒
		return dateTimeInfo;
	}
	
	//获取年月日时分秒信息
	public static DateTimeInfo getDateTimeInfo(LocalDateTime localDateTime){
		LocalDate localDate = localDateTime.toLocalDate();
		LocalTime localTime = localDateTime.toLocalTime();
		DateTimeInfo dateTimeInfo = new DateTimeInfo();
		dateTimeInfo.setYear(localDate.get(ChronoField.YEAR));//年
		dateTimeInfo.setYearOfMonth(localDate.get(ChronoField.MONTH_OF_YEAR));//月
		dateTimeInfo.setDayOfMonth(localDate.get(ChronoField.DAY_OF_MONTH));//日
		dateTimeInfo.setDayOfWeek(localDate.get(ChronoField.DAY_OF_WEEK));//星期几
		dateTimeInfo.setTotalDayOfMonth(localDate.lengthOfMonth());//所在月总共天数
		dateTimeInfo.setIsLeapYear(localDate.isLeapYear());//是否是闰年
		dateTimeInfo.setHourOfDay(localTime.get(ChronoField.HOUR_OF_DAY));//时
		dateTimeInfo.setMinuteOfHour(localTime.get(ChronoField.MINUTE_OF_HOUR));//分
		dateTimeInfo.setSecondOfMinute(localTime.get(ChronoField.SECOND_OF_MINUTE));//秒
		return dateTimeInfo;
	}
	
	//获得本月最后一天
	public static int getLastDayOfMonth(int year,int month){
		LocalDate localDate = LocalDate.of(year, month, FIRST_DAY_OF_MONTH);
		//LocalDate.of(year, month, FIRST_DAY_OF_MONTH).with(TemporalAdjusters.lastDayOfMonth())
		return localDate.lengthOfMonth();
	}
	
	//根据默认格式(yyyy-MM-dd HH:mm:ss)得到日期
	public static String getDefaultDate(){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN));
	}
	
	//根据指定格式得到当前日期的字符串
	public static String getStringDate(String pattern){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
	
	//根据指定日期和格式得到当前日期的字符串
	public static String getStringDate(String date,String originPattern,String newPattern) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern(originPattern))
					    .format(DateTimeFormatter.ofPattern(newPattern));
	}
	
	//根据指定日期和格式得到当前日期的字符串
	public static String getStringDateTime(String date,String originPattern,String newPattern) {
		return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(originPattern))
					        .format(DateTimeFormatter.ofPattern(newPattern));
	}
	
	//根据指定日期和格式得到当前日期的字符串
	public static String getStringTime(String date,String originPattern,String newPattern) {
		return LocalTime.parse(date, DateTimeFormatter.ofPattern(originPattern))
					    .format(DateTimeFormatter.ofPattern(newPattern));
	}
	
	//根据日期字符串和指定格式得到日期(年月日时分秒)
	public static LocalDateTime getDateTime(String date,String pattern) {
		return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
	}
	
	//根据日期字符串和指定格式得到日期(年月日)
	public static LocalDate getDate(String date,String pattern) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
	}
	
	//根据日期字符串和指定格式得到日期(时分秒)
	public static LocalTime getTime(String date,String pattern) {
		return LocalTime.parse(date, DateTimeFormatter.ofPattern(pattern));
	}
	
	//根据给定的字符串日期和指定的格式得到是星期几(1:周一;2:周二;3:周三;4:周四;5:周五;6:周六;7:周日)
	public static int getDayOfWeek(String date,String pattern) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern)).getDayOfWeek().getValue();
	}
	
	//判断给定的字符串日期是否是周末(周六和周日)
	public static boolean isWeekends(String date,String pattern) {
		int value = getDayOfWeek(date, pattern);
		//周六(6)和周日(7)
		if(value>5){
			return true;
		}
		return false;
	}
	
	//得到两个日期(年月日)间的所有日期(闭区间,即包括头和尾)
	public static List<String> getAllDates(String startDate,String endDate,String pattern) {
		final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
		List<String> list = new ArrayList<>();
		LocalDate localStartDate = getDate(startDate, pattern);
		LocalDate localEndDate = getDate(endDate, pattern);
		list.add(localStartDate.format(dateTimeFormatter));
		while(localStartDate.isBefore(localEndDate)){
			localStartDate = localStartDate.plusDays(1);
			list.add(localStartDate.format(dateTimeFormatter));
		}
		return list;
	}
	
	//两时间早晚比较(年月日时分秒)
	public static boolean isDateEarly(LocalDateTime localDateTime1,LocalDateTime localDateTime2){
		return localDateTime1.isBefore(localDateTime2);
	}
	
	//两时间早晚比较(年月日)
	public static boolean isDateEarly(LocalDate localDate1,LocalDate localDate2){
		return localDate1.isBefore(localDate2);
	}
	
	//两时间早晚比较(时分秒)
	public static boolean isDateEarly(LocalTime localTime1,LocalTime localTime2){
		return localTime1.isBefore(localTime2);
	}
	
	//得到当前毫秒数
	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	} 
	
	//得到当前纳秒数
	public static long getNanoTime(){
		return System.nanoTime();
	} 
	
	//根据指定日期(年月日)获得该日期的前面N天的日期(年月日)
	public static List<String> getBeforeDays(String date,String pattern,int beforeDays) {
		LocalDate localDate = getDate(date, pattern);
		List<String> list = new ArrayList<>();
		while(beforeDays!=0){
			String day = localDate.minusDays(beforeDays--).format(DateTimeFormatter.ofPattern(pattern));
			list.add(day);
		}
		return list;
	}
	
	//根据指定日期(年月日)获得该日期的后面N天的日期(年月日)
	public static List<String> getAfterDays(String date,String pattern,int afterDays) throws Exception {
		LocalDate localDate = getDate(date, pattern);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < afterDays; i++) {
			localDate = localDate.plusDays(1);
			String day = localDate.format(DateTimeFormatter.ofPattern(pattern));
			list.add(day);
		}
		return list;
	}
	
	//得到经过一定时间(以秒为单位)之前的日期(年月日时分秒)
	public static String countTimeBefore(String date,String pattern,int second) throws Exception {
		LocalDateTime localDateTime = getDateTime(date, pattern);
		return localDateTime.minusSeconds(second).format(DateTimeFormatter.ofPattern(pattern));
	}
	
	//得到经过一定时间(以秒为单位)后的日期(年月日时分秒)
	public static String countTimeAfter(String date,String pattern,int second) throws Exception {
		LocalDateTime localDateTime = getDateTime(date, pattern);
		return localDateTime.plusSeconds(second).format(DateTimeFormatter.ofPattern(pattern));
	}
	
	//获取两个时间(年月日)间的间隔
	public static Period getPeriod(LocalDate startDate,LocalDate endDate){
		return Period.between(startDate,endDate);
	}
	
	//获取两个时间(时分秒)间的间隔
	public static Duration getDuration(LocalTime startDate,LocalTime endDate){
		return Duration.between(startDate,endDate);
	}
	
	//获取两个时间(年月日时分秒)间的间隔
	public static Duration getDuration(LocalDateTime startDate,LocalDateTime endDate){
		return Duration.between(startDate,endDate);
	}
	
	//LocalDateTime转Date
	public static Date LocalDateTimeToDate(LocalDateTime localDateTime){
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zoneId).toInstant();
		return Date.from(instant);
	}
	
	//Date转LocalDateTime
	public static LocalDateTime DateToLocalDateTime(Date date){
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		return LocalDateTime.ofInstant(instant,zoneId);
	}
	
	public static ZoneId getCurrentJvmDefaultZoneId(){
		//ZoneId zoneId = ZoneId.of("Europe/Rome");
		return TimeZone.getDefault().toZoneId();
	}

}
