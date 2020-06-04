package log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 �Ϲ������� package ��η� �����ϴ¹�
 this.getClassLoader().getResourceAsStream() 
 */

public class LogApp {
	
	String className = this.getClass().getName();
	private static final String name = "apple";
	
	public LogApp() {
		
		//java.lnag �ȿ��� ���� ��ü�� �ν��Ͻ��� ������ Ŭ����
		//������ ������ �� �ִ� class Ŭ������ �������ش�	
		Class logAppClass = this.getClass();
		Constructor[] cons = logAppClass.getConstructors();
		System.out.println("�����ڼ���?"+cons.length);
		
		Method[] method = logAppClass.getMethods();
		
		for(int i = 0; i < method.length; i++) {
			System.out.println(method[i].getName());
		}
		
		//������� ���� ��ȸ
		Field[] field = logAppClass.getFields();
		
		for(int i = 0; i < field.length; i++) {
			System.out.println("�� Ŭ������ �������:" + field[i]);
		}
						
		System.out.println(className+"�� �α�");
		
	}
	
	public void showMsg() {
		
	}
	
	public void test() {
		
	}
	
	public static void main(String[] args) {
		new LogApp();					
	}
	
	
}
