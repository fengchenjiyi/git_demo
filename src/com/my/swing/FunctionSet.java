package com.my.swing;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.my.Tool.JPanelControl;

public class FunctionSet {

	JPanel pane;
	
	public FunctionSet(){
		pane = new JPanel();
		pane.setLayout(new GridLayout(1,3));
		
		MenuBarSet mbs = new MenuBarSet();
		pane.add(mbs.pane);
		pane.setVisible(false);
		
		//把当前 pane 对象添加到管理器
		JPanelControl.getJpControl().addJPanel(pane);
	}
	
	

}
