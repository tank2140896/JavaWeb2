package com.javaweb.web.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.base.BaseService;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.web.eo.schedule.ScheduleAddRequest;
import com.javaweb.web.eo.schedule.ScheduleListRequest;
import com.javaweb.web.eo.schedule.ScheduleListResponse;
import com.javaweb.web.po.Schedule;
import com.javaweb.web.po.User;
import com.javaweb.web.service.ScheduleService;

@Service("scheduleServiceImpl")
public class ScheduleServiceImpl extends BaseService implements ScheduleService {
	
	public List<ScheduleListResponse> getScheduleByDate(ScheduleListRequest scheduleListRequest){
		String year = scheduleListRequest.getYear();
		String month = scheduleListRequest.getMonth();
		String yearMonth = year+CommonConstant.BAR+month+CommonConstant.BAR;
		String startDate = yearMonth+DateUtil.FIRST_DAY_OF_MONTH_STRING_FORMAT;
		String endDate = yearMonth+DateUtil.getLastDayOfMonth(Integer.parseInt(year),Integer.parseInt(month));
		List<String> allDates = getAllDates(startDate,endDate);
		Map<String,String> map = new HashMap<String,String>();
		map.put("startDate",allDates.get(0));
		map.put("endDate",allDates.get(allDates.size()-1));
		List<Schedule> companyScheduleList = scheduleDao.getScheduleByDate(map);
		List<ScheduleListResponse> scheduleList = new ArrayList<>();
		for(int i=0;i<allDates.size();i++){
			ScheduleListResponse scheduleListResponse = new ScheduleListResponse();
			String date = allDates.get(i);
			boolean continueFlag = true;
			for(int j=0;j<companyScheduleList.size();j++){
				String companyDate = companyScheduleList.get(j).getScheduleDate();
				if(date.equals(companyDate)){
					date = DateUtil.getStringDate(date,DateUtil.DEFAULT_DATE_PATTERN,DateUtil.DATE_PATTERN_TYPE6);
					scheduleListResponse.setScheduleDate(date);
					scheduleListResponse.setScheduleType(companyScheduleList.get(j).getScheduleType());
					continueFlag = false;
					break;
				}
			}
			if(continueFlag){
				int weekendsFlag = DateUtil.isWeekends(date,DateUtil.DEFAULT_DATE_PATTERN)==true?1:2;//在公司等特殊日期处理完后，如果不是1周末的话那就是正常日
				date = DateUtil.getStringDate(date,DateUtil.DEFAULT_DATE_PATTERN,DateUtil.DATE_PATTERN_TYPE6);
				scheduleListResponse.setScheduleDate(date);
				scheduleListResponse.setScheduleType(weekendsFlag);
			}
			scheduleList.add(scheduleListResponse);
		}
		return scheduleList;
	}
	
	//特殊日期处理，获得一共42天，满足前端界面显示
	private List<String> getAllDates(String startDate,String endDate){
		List<String> allDates = DateUtil.getAllDates(startDate,endDate,DateUtil.DEFAULT_DATE_PATTERN);
		int startCount = DateUtil.getDayOfWeek(startDate,DateUtil.DEFAULT_DATE_PATTERN)-1;//作为开头,是几就往前推几天-1
		int endCount = 7-DateUtil.getDayOfWeek(endDate, DateUtil.DEFAULT_DATE_PATTERN);//作为结尾,(7-结尾)为后推的天数
		List<String> beforeList = DateUtil.getBeforeDays(startDate,DateUtil.DEFAULT_DATE_PATTERN,startCount);
		int rest = 42-(startCount+endCount+allDates.size());
		List<String> afterList = DateUtil.getAfterDays(endDate,DateUtil.DEFAULT_DATE_PATTERN,endCount+rest);
		List<String> finalList = new ArrayList<>();
		finalList.addAll(beforeList);
		finalList.addAll(allDates);
		finalList.addAll(afterList);
		return finalList;
	}

	@Transactional
	public void scheduleSave(ScheduleAddRequest scheduleAddRequest,User user) {
		String year = scheduleAddRequest.getYear();
		String month = scheduleAddRequest.getMonth();
		String yearMonth = year+CommonConstant.BAR+month+CommonConstant.BAR;
		String startDate = yearMonth+DateUtil.FIRST_DAY_OF_MONTH_STRING_FORMAT;
		String endDate = yearMonth+DateUtil.getLastDayOfMonth(Integer.parseInt(year),Integer.parseInt(month));
		List<String> allDates = getAllDates(startDate,endDate);
		List<ScheduleListResponse> list = scheduleAddRequest.getList();
		List<Schedule> scheduleList = new ArrayList<>();
		for(int i=0;i<list.size();i++){
			Schedule schedule = new Schedule();
			schedule.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			schedule.setScheduleDate(scheduleAddRequest.getYear()+CommonConstant.BAR+list.get(i).getScheduleDate().replace("月",CommonConstant.BAR).replace("日",CommonConstant.EMPTY_VALUE));
			schedule.setScheduleType(list.get(i).getScheduleType());
			schedule.setCreator(user.getCreator());
			schedule.setCreateDate(DateUtil.getDefaultDate());
			schedule.setDelFlag(CommonConstant.ZERO_NUMBER_VALUE);
			scheduleList.add(schedule);
		}
		allDates = allDates.stream().sorted(new Comparator<String>() {
			public int compare(String a, String b) {
				return
				Integer.parseInt(a.replaceAll(CommonConstant.BAR,CommonConstant.EMPTY_VALUE))
				>
				Integer.parseInt(b.replaceAll(CommonConstant.BAR,CommonConstant.EMPTY_VALUE))
				?0:-1;
			}
		}).collect(Collectors.toList());
		Map<String,String> map = new HashMap<>();
		map.put("startDate",allDates.get(0));
		map.put("endDate",allDates.get(allDates.size()-1));
		scheduleDao.deleteByScheduleDate(map);
		scheduleDao.scheduleSave(scheduleList);
	}
	
}
