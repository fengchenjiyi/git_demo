package com.my.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.my.Tool.MyStyle;
import com.my.dao.FileStream;

public class Login extends JPanel implements ActionListener{
	private JFrame loginFrame;
	private JButton logBtn;
	private JButton cleBtn;
	private final JTextField userInput;
	private final JPasswordField pwdInput;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/// WIDTH是指整个顶层框架的宽度。
	static final int WIDTH = 300;
	/// HEIGHT是指整个顶层框架的长度。
	static final int HEIGHT = 180;
	
	//静态代码块  进行初始化数据 和 样式设置
	static{
		MyStyle.initGlobalFont();
		new FileStream().setWordBook();
	}
	
	public Login() {
		
		loginFrame = new JFrame("登录秘书系统");
		//设置用户在此窗体上发起 "close" 时默认执行的操作。    EXIT_ON_CLOSE:退出应用程序后的默认窗口关闭操作
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		////灵活的布局管理器
		GridBagLayout lay=new GridBagLayout();
		setLayout(lay);
		//添加到容器中的组件放在一个列表中
		loginFrame.add(this, BorderLayout.CENTER);
		loginFrame.setSize(WIDTH,HEIGHT);
		loginFrame.setResizable(false);
		
		///通过下面的代码来设置登陆窗口的位置
        Toolkit kit=Toolkit.getDefaultToolkit();
        //获取屏幕的大小
        Dimension screenSize=kit.getScreenSize();
        int width=screenSize.width;
        int height=screenSize.height;
        int x=(width-WIDTH)/2;
        int y=(height-HEIGHT)/2;
        loginFrame.setLocation(x,y);
        
        
        //界面上的控件
        JLabel name = new JLabel("账   号:");
        //name.setPreferredSize(new Dimension(70, 28));
        JLabel password = new JLabel("密   码:");
        //password.setPreferredSize(new Dimension(70, 28));
        userInput = new JTextField();
        userInput.setPreferredSize(new Dimension(130, 26));
        pwdInput = new JPasswordField();
        pwdInput.setPreferredSize(new Dimension(130, 26));
        
        logBtn = new JButton("登  录");
        cleBtn = new JButton("退  出");
        
        final GridBagConstraints gbcName = new GridBagConstraints();
        gbcName.gridy = 0;// 起始点为第1行
        gbcName.gridx = 0;// 起始点为第1列
        gbcName.insets = new Insets(15, 20, 15, 0);// 设置组件左侧的最小距离
        gbcName.weightx = 10;// 第一列的分布方式为10%
        gbcName.fill = GridBagConstraints.CENTER;
		add(name, gbcName);
		
		final GridBagConstraints gbcUserInput = new GridBagConstraints();
		gbcUserInput.gridy = 0;// 起始点为第1行
		gbcUserInput.gridx = 1;// 起始点为第2列
		gbcUserInput.gridwidth = 4;// 组件占用两列
		gbcUserInput.insets = new Insets(15, 0, 15, 35);
		//gbcUserInput.weightx = 10;// 第一列的分布方式为10%
		gbcUserInput.fill = GridBagConstraints.CENTER;
		add(userInput, gbcUserInput);
		
		final GridBagConstraints gbcPwd = new GridBagConstraints();
		gbcPwd.gridy = 1;// 起始点为第2行
		gbcPwd.gridx = 0;// 起始点为第1列
		gbcPwd.insets = new Insets(0, 20, 0, 0);
		gbcPwd.weightx = 10;// 第一列的分布方式为10%
		gbcPwd.fill = GridBagConstraints.CENTER;
		add(password, gbcPwd);
		
		final GridBagConstraints gbcPwdInput = new GridBagConstraints();
		gbcPwdInput.gridy = 1;// 起始点为第2行
		gbcPwdInput.gridx = 1;// 起始点为第2列
		gbcUserInput.gridwidth = 4;// 组件占用两列
		gbcPwdInput.insets = new Insets(0, 0, 0, 35);
		//gbcPwdInput.weightx = 10;// 第一列的分布方式为10%
		gbcPwdInput.fill = GridBagConstraints.CENTER;
		add(pwdInput, gbcPwdInput);
        
        final GridBagConstraints gridLogBtn = new GridBagConstraints();
        gridLogBtn.gridy = 2;// 起始点为第1行
        gridLogBtn.gridx = 0;// 起始点为第1列
        gridLogBtn.insets = new Insets(15, 45, 15, 0);
        gridLogBtn.weightx = 10;// 第一列的分布方式为10%
        gridLogBtn.fill = GridBagConstraints.CENTER;
		add(logBtn, gridLogBtn);
		
		final GridBagConstraints gridCleBtn = new GridBagConstraints();
		gridCleBtn.gridy = 2;// 起始点为第1行
		gridCleBtn.gridx = 1;// 起始点为第1列
		gridCleBtn.insets = new Insets(15, 0, 15, 0);
		gridCleBtn.weightx = 10;// 第一列的分布方式为10%
		gridCleBtn.fill = GridBagConstraints.CENTER;
		add(cleBtn, gridCleBtn);
        
		loginFrame.setVisible(true);
        
        //监听事件
        logBtn.addActionListener(this);
        cleBtn.addActionListener(this);
	}

	/// 此方法用来添加控件到容器中 按照网格组布局方式排列组件的方法
	// x指控件位于第几列。 y指控件位于第几行。 w指控件需要占几列。h指控件需要占几行。
	/*public void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		add(c, constraints);
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "登  录":
			new MainFrame();
			loginFrame.dispose();
			break;
		case "退  出":
			loginFrame.dispose();
			break;
		}
		
	}

}
