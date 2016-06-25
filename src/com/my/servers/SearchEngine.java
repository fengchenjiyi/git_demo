package com.my.servers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.my.Tool.StringJudge;
import com.my.dao.FileStream;
import com.my.dao.WordBean;

public class SearchEngine {
	public static final int TEXT_TYPE_OTHER = 0;  //
	public static final int TEXT_TYPE_CH = 1;     //中文标识
	public static final int TEXT_TYPE_EN = 2;     //英文标识
	
	
	/**
	 * 判断字符串类型,根据不同类型进行搜索字典对应数据
	 * @param str     搜索内容
	 * @param listWb  单词字典集合
	 * @return
	 */
	public List<WordBean> serchWord(String str){
		List<WordBean> listWb = new FileStream().getWordBook();
		List<WordBean> list = new ArrayList<>();
		//判断用户输入内容,根据不同类型进行搜索
		if(StringJudge.isChinese(str.trim())){
			
			list = selectAllWord(str , TEXT_TYPE_CH, listWb);
			//如果集合中没有数据,就单个字符进行匹配搜索
			if(list.size() == 0){
				list = selectSingleChar(str , TEXT_TYPE_CH, listWb);
			}
			
		}else if (StringJudge.isEnglish(str.trim())){
			list = selectAllWord(str , TEXT_TYPE_EN, listWb);
			if(list.size() == 0){
				list = selectSingleChar(str , TEXT_TYPE_EN,listWb);
			}
		}
		
		return list;
	}
	
	
	/**
	 * 对整个字符串进行匹配搜索
	 * @param str     搜索内容
	 * @param type    字符串类型
	 * @param listWb  字典集合
	 * @return
	 */
	private List<WordBean> selectAllWord (String  str, int type, List<WordBean> listWb){
		List<WordBean> rsList = new ArrayList<>();
		
		if(type == TEXT_TYPE_CH){
			for(WordBean wb: listWb){
				//输入字符串和字典里字符串匹配
				int rs = wb.getChName().indexOf(str);
				//如果结果大于-1,说明字典里字符串包含了搜索字符串,添加到结果集合里
				if(rs > -1){
					rsList.add(wb);
				}
			}
		}else if(type == TEXT_TYPE_EN){
			for(WordBean wb: listWb){
				//把字典里字符串转换为大写
				String enStr = wb.getEnName().toUpperCase();
				int rs = enStr.indexOf(str.toUpperCase());
				if(rs > -1){
					rsList.add(wb);
				}
			}
		}
		
		return rsList;
	}
	
	/**
	 * 将字符串 拆解成一个一个字符 进行匹配搜索
	 * @param str     搜索类容
	 * @param type    字符串类型
	 * @param listWb  字典集合
	 * @return
	 */
	private List<WordBean> selectSingleChar(String str, int type, List<WordBean> listWb){
		List<WordBean> rsList = new ArrayList<>();
		
		for(WordBean wb: listWb){
			int count = 0;  //匹配次数计算器
			if(type == TEXT_TYPE_CH){
				//搜索的字符串进行一个一个字符来进行匹配
				for(int i=0; i<str.length(); i++){
					String c = str.substring(i,i+1);
					//匹配成功一次记录一次
					if(wb.getChName().indexOf(c) > -1){
						count++;
					}
				}
			}else if(type == TEXT_TYPE_EN){
				str = str.toUpperCase();
				for(int i=0; i<str.length(); i++){
					String c = str.substring(i,i+1);
					if(wb.getEnName().toUpperCase().indexOf(c) > -1){
						count++;
					}
				}
			}
			
			//把有匹配的单词加入结果集合
			if(count > 0){
				wb.setCount(count);
				rsList.add(wb);
			}
		}
		sort(rsList);
		return rsList;
	}
	
	/**
	 * List<WordBean>集合根据匹配数进行降序排序
	 * @param wbList  搜索出来的 List<WordBean> 集合
	 */
	private void sort(List<WordBean> wbList){
		Collections.sort(wbList, new Comparator<WordBean>(){

			@Override
			public int compare(WordBean o1, WordBean o2) {
				Integer count1 = o1.getCount();
				Integer count2 = o2.getCount();
				//return count1.compareTo(count2); 升序
				return count2.compareTo(count1);
			}
			
		});
	}
}
