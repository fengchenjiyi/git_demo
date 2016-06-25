package com.my.Tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringJudge {
	
	/**
	 * 判断字符串是否为中文
	 * @param str 搜索内容
	 * @return  
	 */
	public static boolean isChinese(String str){
		String regEx = "[\\u4e00-\\u9fa5]+";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if(m.find()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断字符串是否为英文
	 * @param str 搜索内容
	 * @return
	 */
	public static boolean isEnglish(String str){
		return str.matches("^[a-zA-Z]*");
	}
	
	/**
	 * 判断字符串是否为数字
	 * @param str  搜索内容
	 * @return
	 */
	public static boolean isIngeter(String str){
		return str.matches("^[0-9]*");
	}

}
