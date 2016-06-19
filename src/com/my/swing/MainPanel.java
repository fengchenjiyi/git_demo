package com.my.swing;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.my.dao.MySQL;

public class MainPanel extends JFrame {
	private JTabbedPane container;
	private FlowLayout flow;
	private JTextField selInfoText;
	private JButton butSelect;
	private JTextArea textArea;
	
	private JTextField enText;
	private JTextField smText;
	private JTextField chText;
	private JTextField rmText;
	
	private JButton butSave;
	private JButton butUpdate;
	private JButton butDelect;

	public MainPanel() {

		super("我的单词本");
		init();
	}

	/**
	 * 界面组件初始化
	 */
	private void init() {

		// 定义界面大小
		this.setSize(500, 500);
		// 定义界面出现在显示器中间
		this.setLocationRelativeTo(null);
		// 定义界面右上角的x符号退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 定义界面大小不可变
		this.setResizable(true);

		this.setName("Analyse");

		
		// 实例切换面板
		container = new JTabbedPane();
		//流失布局,向左对齐,横向控件间距20,垂直40
		flow = new FlowLayout(FlowLayout.CENTER,20,20);
		 
		// 添加界面组件
		setContainerOne();
		setContainerTwo();
		// 添加组件监听器
		// addListener();

	}
	
	/**
	 * 查询界面布局
	 */
	private void setContainerOne() {
		

		// 查询单词面板
		JPanel container1 = new JPanel();
		// 布局方式
		container1.setLayout(flow);
		
		

		// 添加显示面板到切换面板
		container.addTab("查询单词", container1);
		
		
		JLabel jl = new JLabel("请输入查询内容:");
		selInfoText = new JTextField(16);
		butSelect = new JButton("查 询");
		
		textArea = new JTextArea("什么也没有",15,35); 
		
		
		container1.add(jl);
		container1.add(selInfoText);
		container1.add(butSelect);
		container1.add(textArea);
		
		// 添加切换面板到界面
		this.add(container);

	}
	
	/**
	 * 新增界面布局
	 */
	private void setContainerTwo() {
		// 新增单词面板
		JPanel container2 = new JPanel();
		container2.setLayout(flow);
		// 添加显示面板到切换面板
		container.addTab("新增单词", container2);
		
		JLabel labelEn = new JLabel("单词:");
		enText = new JTextField(15);
		JLabel labelSm = new JLabel("音标:");
		smText = new JTextField(15);
		JLabel labelCh = new JLabel("译文:");
		chText = new JTextField(15);
		JLabel labelRm = new JLabel("备注:");
		rmText = new JTextField(15);
		butSave = new JButton("增  加");
		butUpdate = new JButton("修  改");
		butDelect = new JButton("删  除");

		String[] str = new String[] { "序号","单词名称", "单词音标", "单词译文", "备  注" };
		MySQL ms = new MySQL();
		ms.setWordBook();
		//JTable table = new JTable(ms.getWordBook(), str);
		//table.setSize(200, 300);

		//table.setRowHeight(35);
		//table.setAutoResizeMode(ABORT);
		//table.setDragEnabled(true);
		//table.setBounds(600, 600, 600, 600);
		//table.setFillsViewportHeight(false);
		
		container2.add(labelEn);
		container2.add(enText);
		container2.add(labelSm);
		container2.add(smText);
		container2.add(labelCh);
		container2.add(chText);
		container2.add(labelRm);
		container2.add(rmText);
		container2.add(butSave);
		container2.add(butUpdate);
		container2.add(butDelect);
		//container2.add(table);
		
		
		
		
		this.add(container);
	}
}
