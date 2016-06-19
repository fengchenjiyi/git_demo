package com.my.git_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.dao.MySQL;
import com.my.dao.WordBean;
import com.my.swing.MainPanel;

public class HelloWord {
	

	public static void main(String[] args) {
		
		/*MainPanel mp = new MainPanel();
		mp.setVisible(true);*/
		
		MySQL ms = new MySQL();
		List<WordBean> rsList = new ArrayList<WordBean>();
		//listWb = ms.getWordBook();
		
		/*for(WordBean bean: rsList){
			System.out.println(bean.getIndex() + bean.getEnName() + bean.getSoundmark() + bean.getChName() + bean.getRemark());
		}*/
		
		//ms.getWordBook();
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入搜索内容:");
		HelloGit hg = new HelloGit();
		List<WordBean> listWb =  ms.getWordBook();
		rsList = hg.serchWord(sc.nextLine(), listWb);
		
		while(rsList.size() == 0){
			System.out.println("没有搜索到内容,请重新输入:");
			rsList = hg.serchWord(sc.nextLine(), listWb);
		}
		for(WordBean wb: rsList){
			System.out.println(wb.getIndex() + wb.getEnName() + wb.getSoundmark() + wb.getChName() + wb.getRemark() + wb.getCount() + "\n");
		}
		
		sc.close();
	}
}
