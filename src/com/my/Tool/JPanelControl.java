package com.my.Tool;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

/**
 * 单例模式  进行管理不同面板
 * @author Administrator
 *
 */
public class JPanelControl {
	private List<JPanel> panelList = new LinkedList<JPanel>();
	private static JPanelControl jpControl;
	
	private JPanelControl(){}
	
	public static JPanelControl getJpControl(){
		if(jpControl == null){
			jpControl = new JPanelControl();
		}
		return jpControl;
	}
	
	/**
	 * 添加 JPanel 对象
	 * @param jPanel
	 */
	public  void addJPanel(JPanel jPanel){
		panelList.add(jPanel);
	}
	
	/**
	 * 隐藏多余的JPanel
	 * @param jPanel
	 */
	public void closeJPanel(JPanel jPanel){
		for(JPanel jp : panelList){
			if( !(jp == jPanel) ){
				jp.setVisible(false);
			}
		}
	}

}
