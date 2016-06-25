package com.my.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.my.Tool.JPanelControl;
import com.my.dao.MenuSelBean;
import com.my.dao.FileStream;

/**
 * 主界面
 * @author Administrator
 *
 */
public class MainFrame {
	static final int WIDTH = 750;
	static final int HEIGHT = 500;

	public static JFrame mainFrame;
	public static JToolBar toolBar;
	private JMenuBar menuBar;
	private JPanel panelMain;
	
	JPanel panel_one;
	JPanel panel_two;
	JPanel panel_three;
	
	private FileStream files;
	private List<MenuSelBean> menuSelList;

	public MainFrame() {
		mainFrame = new JFrame("欢迎使用秘书系统");
		mainFrame.setResizable(false);
		mainFrame.getContentPane().setLayout(new CardLayout());
		//mainFrame.setSize(750, 500);
		//mainFrame.setBounds(100, 100, 500, 170);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		panelMain = new JPanel();
		mainFrame.setContentPane(panelMain);
		BorderLayout bord = new BorderLayout();
		panelMain.setLayout(bord);
		
		//菜单控件
		menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
		
		//创造菜单
		createJMenu();
		//创造快速工具栏
		createToolBarItem();

		mainFrame.setVisible(true);
		mainFrame.setSize(WIDTH, HEIGHT);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		int x = (width - WIDTH) / 2;
		int y = (height - HEIGHT) / 2;
		mainFrame.setLocation(x, y);
		
		enItemlAL();
	}
	
	private void createJMenu() {
		/// 创建菜单系统
		JMenu menuEn = new JMenu("英语学习");
		JMenu menuNw = new JMenu("网络管理");
		JMenu menuDa = new JMenu("数据备份");

		menuBar.add(menuEn);
		menuBar.add(menuNw);
		menuBar.add(menuDa);

		JMenuItem enItem1 = new JMenuItem("单词查询");
		JMenuItem enItem2 = new JMenuItem("新增单词");
		JMenuItem enItem3 = new JMenuItem("常用短语");

		JMenuItem nwItem1 = new JMenuItem("账号管理");

		JMenuItem daItem1 = new JMenuItem("重要文件");

		menuEn.add(enItem1);
		menuEn.addSeparator();
		menuEn.add(enItem2);
		menuEn.addSeparator();
		menuEn.add(enItem3);

		menuNw.add(nwItem1);
		menuNw.addSeparator();

		menuDa.add(daItem1);
		menuDa.addSeparator();
		
		enItem1.addActionListener(enItemlAL);
		enItem2.addActionListener(enItem2AL);
		enItem3.addActionListener(enItem3AL);
		nwItem1.addActionListener(nwItem1AL);
		daItem1.addActionListener(daItem1AL);
		
		
		files = (files == null ? new FileStream() : files);
		//获取已有的菜单
		menuSelList = files.getMenuSel();
		//判断软件是否为第一次启动
		if(menuSelList.size() == 0){
			String [] menuStr = new String [] {"单词查询","新增单词","常用短语","账号管理","重要文件"};
			//所有菜单写入到本地文件
			files.setMenuSel(getMenuList(menuStr));
		}
	}
	
	public void createToolBarItem(){
		toolBar = new JToolBar();
		
		List<MenuSelBean> msbList = new ArrayList<MenuSelBean>();
		//被设置的菜单 到 快捷导航
		for(MenuSelBean msb: menuSelList){
			if(msb.getState() == true){
				msbList.add(msb);
			}
		}
		
		//动态创建菜单按钮  将所有的菜单 事件进行监听
		for(int i=0; i<msbList.size(); i++){
			MenuSelBean msb = msbList.get(i);
			switch(i){
			case 0:
				JButton button0 = new JButton(msb.getMenuStr());
				button0.addActionListener(btnBarAL);
				toolBar.add(button0);
				break;
			case 1:
				JButton button1 = new JButton(msb.getMenuStr());
				button1.addActionListener(btnBarAL);
				toolBar.add(button1);
				break;
			case 2:
				JButton button2 = new JButton(msb.getMenuStr());
				button2.addActionListener(btnBarAL);
				toolBar.add(button2);
				break;
			case 3:
				JButton button3 = new JButton(msb.getMenuStr());
				button3.addActionListener(btnBarAL);
				toolBar.add(button3);
				break;
			case 4:
				JButton button4 = new JButton(msb.getMenuStr());
				button4.addActionListener(btnBarAL);
				toolBar.add(button4);
				break;
			case 5:
				JButton button5 = new JButton(msb.getMenuStr());
				button5.addActionListener(btnBarAL);
				toolBar.add(button5);
				break; 
			case 6:
				JButton button6 = new JButton(msb.getMenuStr());
				button6.addActionListener(btnBarAL);
				toolBar.add(button6);
				break;
			}
			
		}
		
		JButton button7 = new JButton("功能设置");
		button7.addActionListener(btn7Listener);
		toolBar.add(button7);
		//加入到容器中
		panelMain.add("North", toolBar);
		
	}
	
	/**
	 * 将所有菜单功能放到List集合
	 * @param menuStr
	 * @return
	 */
	private List<MenuSelBean> getMenuList(String[] menuStr){
		List<MenuSelBean> menuList = new ArrayList<MenuSelBean>();
		for(int i=0; i<menuStr.length; i++){
			MenuSelBean msb = new MenuSelBean();
			msb.setMenuStr(menuStr[i]);
			msb.setState(false);
			
			menuList.add(msb);
		}
		return menuList;
	}
	
	/**
	 * 功能设置
	 */
	ActionListener btn7Listener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			FunctionSet fs = new FunctionSet();
			JPanel jPanel = fs.pane;
			mainFrame.add("Center",jPanel);
			JPanelControl.getJpControl().closeJPanel(jPanel);
			jPanel.setVisible(true);
			
		}
	};
	
	/**
	 * 
	 */
	ActionListener btnBarAL = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()){
			case "单词查询":
				enItemlAL();
				break;
			case "新增单词":
				enItem2AL();
				break;
			case "常用短语":
				break;
			case "账号管理":
				break;
			case "重要文件":
				break;
			case "功能设置":
				break;
			}

		}
	};
	
	/**
	 * 调出  新增单词   Panel
	 */
	private void enItemlAL(){
		SearchWord sw = new SearchWord();
		JPanel jPanel = sw.pane;
		mainFrame.add("Center",jPanel);
		JPanelControl.getJpControl().closeJPanel(jPanel);
		jPanel.setVisible(true);
	}
	
	/**
	 * 搜索单词  按钮 监听事件
	 */
	ActionListener enItemlAL = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			enItemlAL();
		}
		
	};
	
	/**
	 * 调出  增加单词   Panel
	 */
	private void enItem2AL(){
		AddWord aw = new AddWord();
		JPanel jPanel = aw.pane;
		mainFrame.add("Center",jPanel);
		JPanelControl.getJpControl().closeJPanel(jPanel);
		jPanel.setVisible(true);
	}
	
	
	/**
	 * 新增单词  按钮 监听事件
	 */
	ActionListener enItem2AL = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			enItem2AL();
		}
		
	};
	
	ActionListener enItem3AL = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};
	ActionListener nwItem1AL = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};
	ActionListener daItem1AL = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int i=JOptionPane.showConfirmDialog(null,"是否真的需要退出系统","退出确认", JOptionPane.YES_NO_OPTION);
			if(i==0){
        		mainFrame.dispose();
        	}
		}
	};
	
}
