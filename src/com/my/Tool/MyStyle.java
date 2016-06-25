package com.my.Tool;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class MyStyle {
	
	private MyStyle(){}
	
	/**
	 * 新宋体字体,粗体样式
	 * @param size  需要的字号
	 * @return
	 */
	public static Font myFont(int size){
		return new Font("新宋体", Font.BOLD, size);
	}
	
	/**
	 * 全局字体样式
	 */
	public static void  initGlobalFont(){
		FontUIResource fontRes = new FontUIResource(myFont(14));
		for(Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();){
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
				
			}
		}
	}

}
