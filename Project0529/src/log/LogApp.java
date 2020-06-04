package log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 일반파일을 package 경로로 접근하는법
 this.getClassLoader().getResourceAsStream() 
 */

public class LogApp {
	
	String className = this.getClass().getName();
	private static final String name = "apple";
	
	public LogApp() {
		
		//java.lnag 안에는 현재 객체의 인스턴스의 원본인 클래스
		//정보를 추출할 수 있는 class 클래스를 지원해준당	
		Class logAppClass = this.getClass();
		Constructor[] cons = logAppClass.getConstructors();
		System.out.println("생성자수는?"+cons.length);
		
		Method[] method = logAppClass.getMethods();
		
		for(int i = 0; i < method.length; i++) {
			System.out.println(method[i].getName());
		}
		
		//멤버변수 정보 조회
		Field[] field = logAppClass.getFields();
		
		for(int i = 0; i < field.length; i++) {
			System.out.println("이 클래스의 멤버변수:" + field[i]);
		}
						
		System.out.println(className+"의 로그");
		
	}
	
	public void showMsg() {
		
	}
	
	public void test() {
		
	}
	
	public static void main(String[] args) {
		new LogApp();					
	}
	
	
}
