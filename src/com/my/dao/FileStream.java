package com.my.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;




//http://www.cnblogs.com/qiyebao/p/3658747.html 使用参考地址
import com.alibaba.fastjson.JSON;
import com.my.Tool.DateTime;


/**
 * 文件写入读取类
 * @author Administrator
 *
 */
public class FileStream {
	//存放单词文件路径
	private static String FILE_NAME = "resource/wordbook.txt";
	//存放菜单信息文件路径
	private static String MENUSEL_FILE = "resource/menusel.txt";
	
	//uri = GetField.class.getClass().getResource("/resource/wordbook.txt").toURI();  
	//获取和src目录同级resource文件夹中的文件路径,文件必须先存
	
	/**
	 * 第一次初始化基础数据
	 */
	public void setWordBook() {
		// 获取文件对象
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			try {
				// 创建文件
				file.createNewFile();
				// 初始化单词内容
				String[][] wbStr = { { "0", "Path", "[ pɑ:θ ]", "路径", "文件路径" },
						{ "1", "Class", "[klɑ:s]", "类", "一般只一个程序文件" },
						{ "2", "classpath", "[klɑ:s'pɑ:θ ]", "类路径", "程序文件路径" },
						{ "3", "public", "['p ʌblik]", "公共的", "用来修饰类的" },
						{ "4", "private ", "['praivit]", "私有的", "" } };

				setWordBook(wbStr);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * 把传入进来的 List<WordBean>  写入文本中
	 * @param listWb
	 */
	public void setWordBook(List<WordBean> listWb) {
		// 数据集合转换成JSON格式字符串
		try {
			String jsonStr = JSON.toJSONString(listWb);
			outputFile(fileState(FILE_NAME), jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将传入进来的字符数组 写入文件
	 * @param wbStr
	 * @return
	 */
	public void setWordBook(String[][] wbStr) {

		try {
			List<WordBean> list = new ArrayList<WordBean>();
			// 数据集合填充内容
			for (int i = 0; i < wbStr.length; i++) {
				WordBean wb = new WordBean();
				wb.setIndex(wbStr[i][0]);
				wb.setEnName(wbStr[i][1]);
				wb.setSoundmark(wbStr[i][2]);
				wb.setChName(wbStr[i][3]);
				wb.setRemark(wbStr[i][4]);
				wb.setDateTime(new DateTime().getSystemDateTime());
				list.add(wb);
			}

			// 数据集合转换成JSON格式字符串
			String jsonStr = JSON.toJSONString(list);
			outputFile(fileState(FILE_NAME), jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 把菜单信息写入记事本记录
	 * @param menuStr
	 */
	public void setMenuSel(List<MenuSelBean> menuList){

		try {
			String jsonStr = JSON.toJSONString(menuList);
			outputFile(fileState(MENUSEL_FILE), jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断文件状态,不存在就新建文件
	 * @param path  文件相对路径
	 * @return      返回文件对象
	 * @throws IOException
	 */
	private File fileState(String path) throws IOException{
		File file = new File(path);
		//判断文件是否存在
		if( !file.exists() ){
			//创建文件
			file.createNewFile();
		}
		return file;
	}
	
	/**
	 * 把传入进来的字符串写到对应的文件对象中
	 * @param file   文件对象
	 * @param str    写入字符串
	 * @throws IOException
	 */
	private void outputFile(File file, String str) throws IOException{
		//文件输出流,把JSON字符串写入到文件中
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file));
		bos.write(str.getBytes());
		bos.flush();
		bos.close();
	}
	
	/**
	 * 读取文件对象中的文件内容,返回字符串形式
	 * @param file   需要读取的文件对象
	 * @return       文件对象读取的数据
	 * @throws IOException
	 */
	private String inputFile(File file) throws IOException{
		//文件输入流
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(file));
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//文件读取
		byte [] buffer = new byte[1024];
		int len = 0;
		while((len = bis.read(buffer)) != -1){
			outStream.write(buffer, 0, len);
		}
		bis.close();
		//读取的文件流转换成字符串
		return new String(outStream.toByteArray());
	}
	
	/**
	 * 获取所有菜单信息
	 * @return   返回 
	 */
	public List<MenuSelBean> getMenuSel(){
		List<MenuSelBean> list = new ArrayList<MenuSelBean>();
		try {
			String jsonStr = inputFile(fileState(MENUSEL_FILE));
			if( !jsonStr.equals("") ){
				list = JSON.parseArray(jsonStr , MenuSelBean.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 获取单词字典集合
	 * @return  返回所有单词集合
	 */
	public List<WordBean>  getWordBook(){
		List<WordBean> listWb = new ArrayList<WordBean>();
		try {
			//JSON格式字符串转换成集合
			listWb = JSON.parseArray(inputFile(fileState(FILE_NAME)) , WordBean.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sort(listWb);
		return listWb;
	}
	
	/**
	 * 获取所有单词内容
	 * @return  返回所有单词内容 数组 形式
	 */
	public String[][] getWordBookStr(List<WordBean> listWb){
		sort(listWb);
		int size = listWb.size();
		String [][] strList = new String[size][6];
		for(int i=0; i<size; i++){
			WordBean wb = listWb.get(i);
			strList[i][0] = wb.getIndex();
			strList[i][1] = wb.getEnName();
			strList[i][2] = wb.getSoundmark();
			strList[i][3] = wb.getChName();
			strList[i][4] = wb.getRemark();
			strList[i][5] = wb.getDateTime();
		}
		
		return strList;
	}
	
	/**
	 * List<WordBean>集合根据索引数进行降序排序
	 * @param wbList  搜索出来的 List<WordBean> 集合
	 */
	private void sort(List<WordBean> wbList){
		Collections.sort(wbList, new Comparator<WordBean>(){

			@Override
			public int compare(WordBean o1, WordBean o2) {
				Integer count1 = Integer.valueOf(o1.getIndex());
				Integer count2 = Integer.valueOf(o2.getIndex());
				return count1.compareTo(count2); 
			}
			
		});
	}
}
