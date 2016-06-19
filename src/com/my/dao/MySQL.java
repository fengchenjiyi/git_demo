package com.my.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;




//http://www.cnblogs.com/qiyebao/p/3658747.html 使用参考地址
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;


public class MySQL {
	private static String FILE_NAME = "resource/wordbook.txt";
	private File file;
	
	public MySQL(){
		//uri = GetField.class.getClass().getResource("/resource/wordbook.txt").toURI();  获取和src目录同级resource文件夹中的文件路径,文件必须先存在
		this.setWordBook();
	}
	
	/**
	 * 初始化基础数据
	 */
	public void setWordBook(){
		//获取文件对象
		file = new File(FILE_NAME);
		//判断文件是否存在
		if(!file.exists()){
			try {
				//创建文件
				file.createNewFile();
				//初始化单词内容
				/*String [][] wbStr = {{"0","Path","[ pɑ:θ ]","路径",""},{"1","Class","[klɑ:s]","类",""},{"2","classpath","[klɑ:s'pɑ:θ ]","类路径",""},{"3","public","['p ʌblik]","公共的",""}
				,{"4","private ","['praivit]","私有的",""}}; */
				
				String [][] wbStr = {{"0","abc","[ pɑ:θ ]","路径",""},{"1","efg","[klɑ:s]","类",""},{"2","hij","[klɑ:s'pɑ:θ ]","类路径",""},{"3","abcd","['p ʌblik]","公共的",""}
				,{"4","aaaaa ","['praivit]","私有的",""}};
				
				List<WordBean> list = new ArrayList<WordBean>();
				//数据集合填充内容
				for(int i=0; i<wbStr.length; i++){
					WordBean wb = new WordBean();
					wb.setIndex(wbStr[i][0]);
					wb.setEnName(wbStr[i][1]);
					wb.setSoundmark(wbStr[i][2]);
					wb.setChName(wbStr[i][3]);
					wb.setRemark(wbStr[i][4]);
					list.add(wb);
				}
				
				//数据集合转换成JSON格式字符串
				String jsonStr = JSON.toJSONString(list);
				
				//文件输出流,把JSON字符串写入到文件中
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(jsonStr.getBytes());
				
				bos.flush();
				bos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取单词字典集合
	 * @return  返回所有单词集合
	 */
	public List<WordBean>  getWordBook(){
		file = new File(FILE_NAME);
		List<WordBean> list = new ArrayList<WordBean>();
		if(file.exists()){
			try {
				//文件输入流
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				//文件读取
				byte [] buffer = new byte[1024];
				int len = 0;
				while((len = bis.read(buffer)) != -1){
					outStream.write(buffer, 0, len);
				}
				bis.close();
				//读取的文件流转换成字符串
				String str = new String(outStream.toByteArray());
				//JSON格式字符串转换成集合
				list = JSON.parseArray(str , WordBean.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			setWordBook();
		}
		
		return list;
	}
}
