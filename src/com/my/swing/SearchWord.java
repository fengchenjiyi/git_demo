package com.my.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.my.Tool.JPanelControl;
import com.my.Tool.MyStyle;
import com.my.Tool.StringJudge;
import com.my.dao.WordBean;
import com.my.servers.SearchEngine;

/**
 * 单词搜索 Panel
 * @author Administrator
 *
 */
public class SearchWord extends JPanel{
	JPanel pane;
	private JTextField selInfoText;
	private JButton butSelect;
	private JTextArea rsTest;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxFontSize;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SearchWord(){
		pane = new JPanel();
		//流失布局,向左对齐,横向控件间距20,垂直40
		// 布局方式
		pane.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		setPaneElement();
		pane.setVisible(false);
		
		//把当前 pane 对象添加到管理器
		JPanelControl.getJpControl().addJPanel(pane);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setPaneElement() {
		
		JLabel labelTs = new JLabel("请输入查询内容:");
		
		selInfoText = new JTextField();
		selInfoText.setPreferredSize(new Dimension(300,35));
		
		butSelect = new JButton("查 询");
		butSelect.addActionListener(butSelectAction);
		
		String[] selFontSize={"20","22","24","26","28"};
		String defaultMessage="输入字号";
		
		cboxFontSize = new JComboBox(selFontSize);
		cboxFontSize.setEditable(true);
	  	ComboBoxEditor editor=cboxFontSize.getEditor();
	  	cboxFontSize.configureEditor(editor,defaultMessage);// 返回用于绘制和编辑 JComboBox 字段中所选项的编辑器
	  	cboxFontSize.addItemListener(itemListerner);
	  	//cboxFontSize.addActionListener(cBoxAction);
	  	
	  	rsTest = new JTextArea("还没有进行搜索...");
	  	rsTest.setPreferredSize(new Dimension(504, 260));
	  	rsTest.setBackground(new Color(238, 238, 238));
	  	rsTest.setEditable(false);
	  	rsTest.setBorder(BorderFactory.createTitledBorder("搜索结果"));
		
		
		pane.add(labelTs);
		pane.add(selInfoText);
		pane.add(butSelect);
		pane.add(cboxFontSize);
		pane.add(rsTest);
		
		// 添加切换面板到界面
		//add(pane);

	}
	
	ActionListener  butSelectAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = selInfoText.getText().trim();
			if(!str.trim().equals("")){
				List<WordBean> listWb = new SearchEngine().serchWord(str);
				StringBuffer rsStr = new StringBuffer();
				for(WordBean wb : listWb){
					rsStr.append(wb.getEnName() + "\t" + wb.getSoundmark() + "\t" + wb.getChName() + "\n"
							+ wb.getRemark());
					rsStr.append("\n" + "---------------------------------------------" + "\n");
					
				}
				rsTest.setText(rsStr.toString());
			}else{
				rsTest.setText("请输入查询内容!");
			}
			
			
		}
	};

	ItemListener itemListerner = new ItemListener() {

		@SuppressWarnings("unchecked")
		@Override
		public void itemStateChanged(ItemEvent e) {

			String tmp = (String) cboxFontSize.getSelectedItem();
			if (StringJudge.isIngeter(tmp)) {
				boolean isaddItem = true;
				int fontSize = Integer.parseInt(tmp);
				
				//进行对比,如果存在相同字体大小 当前大小 就不加入下拉框列表
				for (int i = 0; i < cboxFontSize.getItemCount(); i++) {
					if (cboxFontSize.getItemAt(i).equals(tmp)) {
						isaddItem = false;
						break;
					}
				}
				if (isaddItem) {
					cboxFontSize.insertItemAt(tmp, 0);// 插入项目tmp到0索引位置(第一列中).
				}
				rsTest.setFont(MyStyle.myFont(fontSize));

			} else {
				cboxFontSize.getEditor().setItem("输入整数");
			}
		}
	};
}
