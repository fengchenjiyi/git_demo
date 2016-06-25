package com.my.dao;

/**
 * 单词 Bean
 * @author Administrator
 *
 */
public class WordBean{
	
	private String index;
	private String enName;
	private String chName;
	private String soundmark;
	private String remark;
	private String dateTime;
	private int count;    // 用来搜索过程中记录匹配次数
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getSoundmark() {
		return soundmark;
	}
	public void setSoundmark(String soundmark) {
		this.soundmark = soundmark;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
