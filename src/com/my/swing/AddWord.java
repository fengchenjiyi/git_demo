package com.my.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.my.Tool.DateTime;
import com.my.Tool.JPanelControl;
import com.my.dao.FileStream;
import com.my.dao.WordBean;

public class AddWord extends JPanel{

	private static final long serialVersionUID = 1L;
	JPanel pane;
	
	private List<WordBean> listWb;
	private JTextField enInput;
	private JTextField smInput;
	private JTextField chInput;
	private JTextField rmInput;
	private DefaultTableModel model;
	private JTable table;
	private FileStream files ;
	private int tableRow;

	public AddWord() {
		pane = new JPanel();
		// 流失布局,向左对齐,横向控件间距20,垂直40
		//FlowLayout flowLy = new FlowLayout(FlowLayout.CENTER, 5, 5);
		// 布局方式
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		//pane.setBounds(100, 100, 100, 100);
		pane.add(Box.createVerticalStrut(10));
		pane.add(setPaneOne());
		pane.add(Box.createVerticalStrut(10));
		pane.add(setPaneTwo());
		pane.add(Box.createVerticalStrut(20));
		pane.add(setPaneThree());
		pane.add(Box.createVerticalStrut(10));
		pane.add(setPaneFour());
		pane.add(Box.createVerticalStrut(10));
		pane.setVisible(false);
		
		//把当前 pane 对象添加到管理器
	    JPanelControl.getJpControl().addJPanel(pane);
	}
	
	private JPanel setPaneOne(){
		
		JPanel paneOne = new JPanel();
		paneOne.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		
		JLabel enLable = new JLabel("英 文:");
		paneOne.add(enLable);

		enInput = new JTextField();
		enInput.setPreferredSize(new Dimension(120, 28));
		paneOne.add(enInput);

		JLabel smLable = new JLabel("音 标:");
		paneOne.add(smLable);

		smInput = new JTextField();
		smInput.setPreferredSize(new Dimension(120, 28));
		paneOne.add(smInput);

		JLabel chLable = new JLabel("译 文:");
		paneOne.add(chLable);

		chInput = new JTextField();
		chInput.setPreferredSize(new Dimension(120, 28));
		paneOne.add(chInput);

		JLabel rmLable = new JLabel("备 注:");
		paneOne.add(rmLable);

		rmInput = new JTextField();
		rmInput.setPreferredSize(new Dimension(160, 28));
		paneOne.add(rmInput);
		
		return paneOne;

	}
	
	private JPanel setPaneTwo(){
		JPanel paneTwo = new JPanel();
		//paneTwo.setLayout(new GridBagLayout());
		paneTwo.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));
		
		JButton addBtn = new JButton("添加单词");
		paneTwo.add(addBtn);
		

		JButton upBtn = new JButton("修改单词");
		paneTwo.add(upBtn);

		JButton delBtn = new JButton("删除单词");
		paneTwo.add(delBtn);
		
		addBtn.addActionListener(addBtnListener);
		upBtn.addActionListener(upBtnListener);
		delBtn.addActionListener(delBtnListener);
		return paneTwo;
	}
	
	private JPanel setPaneThree(){
		
		JPanel paneThree = new JPanel();
		
		paneThree.setLayout(new BoxLayout(paneThree, BoxLayout.X_AXIS));
		
		files = new FileStream();
		listWb = files.getWordBook();
		String[][] cellData = files.getWordBookStr(listWb);
		String[] headers = { "序号", "英文", "音标", "译文", "备注", "日期" };

		//设置单元格是否可编辑
		model = new DefaultTableModel(cellData, headers) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return true;
			}
		};
		
		//设置表显示行数
		model.setRowCount(10);
		table = new JTable(model);
		//设置表行的高度
		table.setRowHeight(30);
		//设置首列宽度
		TableColumn tableCumn = table.getColumnModel().getColumn(0);
		tableCumn.setPreferredWidth(15);
		tableCumn.setResizable(false);
		//监听表格修改事件
		model.addTableModelListener(tableListener);
		
		//江表格放入可滚动的容器
		JScrollPane jspTab = new JScrollPane(table);
		paneThree.add(jspTab);
		
		return paneThree;
	}
	
	private JPanel setPaneFour(){
		JPanel paneFour = new JPanel();
		paneFour.setLayout(new GridBagLayout());
		
		JButton upBtn = new JButton("上一页");
		final GridBagConstraints gbcUpBtn = new GridBagConstraints();
		gbcUpBtn.gridy = 0;// 起始点为第1行
		gbcUpBtn.gridx = 0;// 起始点为第1列
		gbcUpBtn.insets = new Insets(0, 0, 0, 15);// 设置组件左侧的最小距离
		gbcUpBtn.weightx = 0;// 第一列的分布方式为10%
		gbcUpBtn.fill = GridBagConstraints.CENTER;
		paneFour.add(upBtn, gbcUpBtn);

		JButton onBtn = new JButton("下一页");
		final GridBagConstraints gbcOnBtn = new GridBagConstraints();
		gbcOnBtn.gridy = 0;// 起始点为第1行
		gbcOnBtn.gridx = 1;// 起始点为第2列
		gbcOnBtn.insets = new Insets(0, 15, 0, 15);// 设置组件左侧的最小距离
		gbcOnBtn.weightx = 0;// 第一列的分布方式为10%
		gbcOnBtn.fill = GridBagConstraints.CENTER;
		paneFour.add(onBtn, gbcOnBtn);
		
		return paneFour;
	}
	
	TableModelListener tableListener = new TableModelListener() {
		@Override
		public void tableChanged(TableModelEvent e) {
			//表格列
			int column = e.getColumn();
			//表格行
			tableRow = e.getFirstRow();
			System.out.println("你选择:"+tableRow);
			WordBean wb = listWb.get(tableRow);
			String str = table.getValueAt(tableRow, column).toString();
			switch(column){
			case 1:
				wb.setEnName(str);
				break;
			case 2:
				wb.setSoundmark(str);
				break;
			case 3:
				wb.setChName(str);
				break;
			case 4:
				wb.setRemark(str);
				break;
			case 5:
				wb.setDateTime(str);
				break;
			}
			
			/*for (int i = 0; i < listWb.size(); i++) {
				wb = listWb.get(i);
				System.out.println(wb.getIndex() +"\t"+ wb.getEnName()  +"\t"+ wb.getSoundmark()  +"\t"+ wb.getChName()  +"\t"+ wb.getRemark()
				 +"\t"+ wb.getDateTime());
			}*/
		}
	};
	
	ActionListener  addBtnListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int index = Integer.valueOf(listWb.get(listWb.size()-1).getIndex());
			WordBean wb = new WordBean();
			wb.setIndex(String.valueOf(++index));
			wb.setEnName(enInput.getText().trim());
			wb.setSoundmark(smInput.getText().trim());
			wb.setChName(chInput.getText().trim());
			wb.setRemark(rmInput.getText().trim());
			wb.setDateTime(DateTime.getSystemDateTime());
			listWb.add(wb);
			files.setWordBook(listWb);
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.removeRow(0);
			//dtm.addRow(rowData);
			table.validate();
			System.out.println("添加完成");
			table.updateUI();
			
			table.repaint();
		}
	};
	
	ActionListener  upBtnListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			files.setWordBook(listWb);
			
			table.updateUI();
			//table.repaint();
			
		}
	};
	
	ActionListener  delBtnListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println(model.getRowCount());
			if(tableRow != 0){
				listWb.remove(tableRow);
				files.setWordBook(listWb);
				
				table.updateUI();
				//table.repaint();
			}else{
				JOptionPane.showConfirmDialog(null,"请选择要删除的行","删除失败", JOptionPane.CLOSED_OPTION);
			}
			
		}
	};
}
