package com.javaweb.util.help.mathhelp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
	
	//递归
	public static long cal_1(long x){
		if(x<=1){
			return x;
		}else{
			return cal_1(x-1)+x;
		}
	}
	
	//尾递归
	public static long cal_2(long x,long y){
		if(x==1){
			return y;
		}else{
			return cal_2(x-1,x+y);
		}
	}
	
	//非递归
	public static long cal_3(long x){
		long ret = 0;
		for(long i=x;i>0;i--){
			ret+=i;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		/**
		long start1 = System.currentTimeMillis();
		System.out.println(cal_1(100000));
		System.out.println(System.currentTimeMillis()-start1);
		long start2 = System.currentTimeMillis();
		System.out.println(cal_2(100000,1));
		System.out.println(System.currentTimeMillis()-start2);
		long start3 = System.currentTimeMillis();
		System.out.println(cal_3(100000));
		System.out.println(System.currentTimeMillis()-start3);
		*/
		List<User> list = new ArrayList<>();
		User u1 = new User(1,0,"a");
		User u2 = new User(2,1,"a1");
		User u3 = new User(3,1,"a4");
		User u4 = new User(4,1,"a3");
		User u5 = new User(5,2,"a4");
		User u6 = new User(6,2,"a5");
		User u7 = new User(7,3,"a2");
		User u8 = new User(8,4,"a1");
		User u9 = new User(9,4,"a4");
		User u10 = new User(10,9,"a3");
		list.add(u1);list.add(u2);list.add(u3);list.add(u4);list.add(u5);
		list.add(u6);list.add(u7);list.add(u8);list.add(u9);list.add(u10);
		list = detail(list,null);
		//System.out.println(JSONArray.fromObject(getLevelData(list,3)));
		//System.out.println(JSONArray.fromObject(list));
		searchForLastLevel(list,"a6");
		//searchForAllLevel(list,"a4");
		//System.out.println(JSONArray.fromObject(list));
	}
	
	//封装成树形结构
	public static List<User> detail(List<User> originList,User user){
		List<User> targetList = new ArrayList<>();
		for (int i = 0; i < originList.size(); i++) {
			User getUser = originList.get(i);
			if((user!=null&&user.getId()==getUser.getPid())
			 ||(user==null&&getUser.getPid()==0)){
				getUser.setUsers(detail(originList,getUser));
				targetList.add(getUser);
			}
		}
		return targetList;
	}
	
	//只搜索最后一层节点
	public static void searchForLastLevel(List<User> list,String condition){
		Iterator<User> iterator = list.iterator();
		while(iterator.hasNext()){
			User getUser = iterator.next();
			List<User> userList = getUser.getUsers();
			if(!getUser.getName().equals(condition)){
				searchForLastLevel(userList,condition);
				if(userList==null||userList.size()==0){
					iterator.remove();
				}else{
					getUser.setUsers(userList);
				}
			}else{
				if(userList!=null&&userList.size()!=0){
					iterator.remove();
				}
			}
		}
	}
	
	//搜索所有节点
	public static void searchForAllLevel(List<User> list,String condition){
		for(int i=0;i<list.size();i++){
			User getUser = list.get(i);
			List<User> userList = getUser.getUsers();
			if(!getUser.getName().equals(condition)){
				searchForAllLevel(userList,condition);
				if(userList==null||userList.size()==0){
					list.remove(getUser);
					i--;//除了i--方式，还可以用Iterator
				}else{
					getUser.setUsers(userList);
				}
			}
		}
	}
	
	//获取指定层级的数据
	public static List<User> getLevelData(List<User> list,int level){
		List<User> userList = new ArrayList<>(); 
		for(int i=0;i<list.size();i++){
			User user = list.get(i);
			if(level-1>0){
				userList.addAll(getLevelData(user.getUsers(),level-1));
			}else{
				user.setUsers(null);
				userList.add(user);
			}
			
		}
		return userList;
	}
	
}
