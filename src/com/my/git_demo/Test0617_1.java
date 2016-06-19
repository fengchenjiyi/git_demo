package com.my.git_demo;

class Test0617_1 {
	public static void main(String[] args) 
	{
		
		int height = 8848000;
		int count = 0;
		int num = 1;
		while(height - num >= 0){
			count ++;
			num = num*2;
		}
		//System.out.println(num);
		System.out.println(count);
	}
}

