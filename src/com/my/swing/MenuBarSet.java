package com.my.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.my.dao.FileStream;
import com.my.dao.MenuSelBean;

/**
 * 功能设置 Pane 面板
 * @author Administrator
 *
 */
public class MenuBarSet extends MouseAdapter {
	JPanel pane;
	
	private DefaultListModel menuModel;
	private JList allMenuList;
	private DefaultListModel seletModel;
	private JList selMenuList;
	private FileStream files;
	private List<MenuSelBean> listMsb;
	
	public MenuBarSet() {
		pane = new JPanel();
		pane.setPreferredSize(new Dimension(400, 450));
		pane.setLayout(new GridLayout(1, 3));
		
		files = new FileStream();
		listMsb = files.getMenuSel();
		
		menuModel = new DataModel();
		allMenuList = new JList(menuModel);
		allMenuList.setBorder(BorderFactory.createTitledBorder("所有菜单功能"));
		allMenuList.addMouseListener(this);
		
		seletModel = new SelModel();
		selMenuList = new JList(seletModel);
		selMenuList.setBorder(BorderFactory.createTitledBorder("已有快捷菜单"));
		selMenuList.addMouseListener(this);
		
		JScrollPane jspAllList = new JScrollPane(allMenuList);
		jspAllList.setPreferredSize(new Dimension(280, 450));
		allMenuList.setBackground(new Color(238, 238, 238));
		pane.add(jspAllList);
		JLabel label = new JLabel("双击选择");
		pane.add(label);
		JScrollPane jspSelList = new JScrollPane(selMenuList);
		jspSelList.setPreferredSize(new Dimension(180, 450));
		selMenuList.setBackground(new Color(238, 238, 238));
		pane.add(jspSelList);
	}

	// 处理鼠标双击事件.
	public void mouseClicked(MouseEvent e) {
		int index;
		if (e.getSource() == allMenuList) {
			if (e.getClickCount() == 2) {
				// 当双击左边列表框中选项，会在左边将此项去掉，在右边列表框中将此项添加
				index = allMenuList.locationToIndex(e.getPoint());
				String tmp = (String) menuModel.getElementAt(index);
				seletModel.addElement(tmp);
				selMenuList.setModel(seletModel);
				menuModel.removeElementAt(index);
				allMenuList.setModel(menuModel);
				
				
				 //MainFrame.toolBar.updateUI();
				 
				upMenuFile(tmp,true);
				//MainFrame mf = new MainFrame();
				//mf.toolBar.update(null);
				//mf.createToolBarItem();
			}
		}
		if (e.getSource() == selMenuList) {
			if (e.getClickCount() == 2) {
				// 当双击右边列表框中选项，会在右边将此项去掉，在左边列表框中将此项添加
				 index = selMenuList.locationToIndex(e.getPoint());
				 String tmp = (String) seletModel.getElementAt(index);
				 menuModel.addElement(tmp);
				 allMenuList.setModel(menuModel);
				 seletModel.removeElementAt(index);
				 selMenuList.setModel(seletModel);
				 
				 
				 //MainFrame.toolBar.updateUI();
				 
				 upMenuFile(tmp,false);
			}
		}
	}
	
	private void upMenuFile(String str, boolean b){
		List<MenuSelBean> list = new ArrayList<MenuSelBean>();
		for(MenuSelBean msb : listMsb){
			if(msb.getMenuStr().equals(str)){
				//msb.setMenuStr(str);
				msb.setState(b);
				list.add(msb);
			}else{
				list.add(msb);
			}
		}
		listMsb = list;
		files.setMenuSel(list);
	}

	/**
	 * 让DateModel继承DefaultListModel类，创建一个列表框类
	 * 
	 * @author Administrator
	 *
	 */
	class DataModel extends DefaultListModel {

		public DataModel() {
			for (MenuSelBean msb : listMsb){
				if( !msb.getState()){
					addElement(msb.getMenuStr());
				}
			}
		}
	}
	
	class SelModel extends DefaultListModel {

		public SelModel() {
			for (MenuSelBean msb : listMsb){
				if(msb.getState()){
					addElement(msb.getMenuStr());
				}
			}
		}
	}
	
}
