package com.my.Tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
	
	/**
	 * 获取用户系统时间 年月 日
	 * @return  yyyy-MM-dd
	 */
	public static String getSystemDateStr(){
		/*String date = getSystemDate();
		date = date.replace("年", "-");
		date = date.replace("月", "-");
		date = date.replace("日", "");*/
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		return sDateFormat.format(new java.util.Date());
	}
	
	/**
	 * 获取用户系统时间 年月 日
	 * @return yyyy-MM-dd hh:mm:ss
	 */
	public static String getSystemDateTime(){
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		return sDateFormat.format(new java.util.Date());
	}
	
	/**
	 * 把传入的    2014年11月17日    转化为  yyyy-MM-dd
	 * @param date
	 * @return  yyyy-MM-dd
	 */
	public static String getSystemDateStr(String date){
		date = date.replace("年", "-");
		date = date.replace("月", "-");
		date = date.replace("日", "");
		return date;
	}
	
	/**
	 * 获取用户登录时间 年月 日
	 * 返回 int[]{yyyy,mm,dd}    
	 * @return 返回为null时证明当前系统日期小于以前使用日期，否则返回系统登录日期
	 */
	public static int[] getYearMonthDay(){
		int [] yearMontDay = null;
		String date = getSystemDateStr();
		if(date != null){
			int a = date.indexOf("-");
			int b = date.indexOf("-",a+1);
			int c = date.length();
			yearMontDay = new int [3];
			yearMontDay[0] = Integer.valueOf(date.substring(0, a));
			yearMontDay[1] = Integer.valueOf(date.substring(a+1, b));
			yearMontDay[2] = Integer.valueOf(date.substring(b+1, c));
		}
		return yearMontDay;
	}
	
	/**
	 * 把传入的字符串日期封装成 int[]
	 * @param date
	 * @return int[]{yyyy,mm,dd}
	 */
	public static int[] getYearMonthDay(String date){
		int [] yearMontDay = null;
		if(date != null){
			int a = date.indexOf("-");
			int b = date.indexOf("-",a+1);
			int c = date.length();
			yearMontDay = new int [3];
			yearMontDay[0] = Integer.valueOf(date.substring(0, a));
			yearMontDay[1] = Integer.valueOf(date.substring(a+1, b));
			yearMontDay[2] = Integer.valueOf(date.substring(b+1, c));
		}
		return yearMontDay;
	}
	
	/**
	 * 比较当前系统日期是否大于之前记载的日期
	 * @param date
	 * @param systemDate
	 * @return 0 当前日期小于记载日期，1 当前日期等于记载日期， 2当前日期大于记载日期
	 */
	public static int contrastDate(String date , String systemDate){
		int rs = 0;
		if(date.equals(systemDate)){
			rs = 1;
		}else{
			java.sql.Date jzData = getStandardDate(date);
			java.sql.Date dqData = getStandardDate(systemDate);
			//判断当前时间是否比记录时间大
			if(dqData.after(jzData)){
				rs = 2;
			}
		}
		
		return rs;
	}
	
	/**
	 * 获取当前月最后一天
	 * @param currDate yyyy-MM-dd
	 * @return
	 */
	public static int getLastDayOfMonth(String currDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getStandardDate(currDate));
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
	
	/**
	 * 把字符串转换成时间类型   格式化
	 * @param date yyyy-MM-dd
	 * @return yyyy-MM-dd
	 */
	public static java.sql.Date getStandardDate(String date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date cDate = null;
		java.sql.Date bgData = null;
		try {
			cDate = df.parse(date);
			bgData = new java.sql.Date(cDate.getTime());
		} catch (ParseException e) {
			return bgData;
		}
		return bgData;
	}
	
	/**
	 * 把字符串转换成时间类型  格式化
	 * @param date yyyy-MM-dd hh:mm:ss
	 * @return yyyy-MM-dd hh:mm:ss
	 */
	public static java.sql.Date getStandardDateTime(String date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date cDate = null;
		java.sql.Date bgData = null;
		try {
			cDate = df.parse(date);
			bgData = new java.sql.Date(cDate.getTime());
		} catch (ParseException e) {
			return bgData;
		}
		return bgData;
	}
	
	/**
	 * 计算时间差
	 * @param datetime1  大  yyyy-MM-dd HH:mm:ss
	 * @param datetime2  小 yyyy-MM-dd HH:mm:ss
	 * @return  返回小时
	 */
	public static long getTimeDifference(String datetime1, String datetime2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(datetime1);
			Date d2 = df.parse(datetime2);
			long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
			//long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
			return hours;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	/**
	 * 日期 加减 为 指定天数后
	 * @param date 当前日期
	 * @param dayAddNum  正数加，负数减
	 * @return 返回 加减后  yyyy-MM-dd
	 */
	public static String getDateAddSubOneDay(String date, int addSubDay) {  
		Date nowDate = getStandardDate(date);
        Date newDate2 = new Date(nowDate.getTime() + addSubDay * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        return simpleDateFormat.format(newDate2); 
    }
	
	/**
	 * 月  加减  为指定月 后
	 * @param date 当前日期
	 * @param addSubMonth 正数加，负数减
	 * @return 返回 加减后  yyyy-MM-dd
	 */
	public static String getDateAddSubOneMonth(String date, int addSubMonth){
		Date nowDate = getStandardDate(date);
		Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(nowDate);
        //rightNow.add(Calendar.YEAR,-1);//日期减1年
        rightNow.add(Calendar.MONTH,addSubMonth);//日期加3个月
        //rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(rightNow.getTime());
	}
	
	/**
	 * 根据日期获取  星期几
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(String date) {
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六" };
		// String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getStandardDate(date));
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysName[intWeek];
	}

}
