package log;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
Class Ŭ������ �̿��ϸ�, Ŭ������ �ڵ��� �ε� ������ �����ڰ�
�ֵ��� �� �� �ִ�..
�Ϲ������� ���� Ŭ�������� ���Ǵ� ���� Ŭ������ ���������
�ڹٿ� ���� static ������ Ŭ���� �ڵ尡 �ö󰡴µ�,,, 
Class Ŭ������ �̿��ϸ�, ���� ������ �����ڰ� �������� Ŭ������
�ڵ带 static ������ �ø��� �ִ�..
�� �����ڰ� �ֵ��Ͽ� �������� �÷�����
����) �ڹٽ�ũ��Ʈ���� DOM�� �޸𸮿� �����ϴ� ��ü?
 		html ������ �ε�ɶ� 1:1 �����ϸ鼭 �������� �ø�...
 		�����ڰ� DOM�� �ø��� ���� ������... 
 		document.createElement("div");
 */
public class DynamicLoad extends JFrame{

	JTextField t_input;
	JButton bt;
	
	public DynamicLoad() {
		
		t_input = new JTextField(33);
		bt = new JButton("��������");
		
		
		setLayout(new FlowLayout());
		add(t_input);
		add(bt);
		
		setSize(500,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		bt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				createObject();
//				t_input.setText("");
			}
		});
	}
	
	public void createObject() {
		//�ؽ�Ʈ �ʵ忡 �Է��� ��Ʈ�������� �̿��Ͽ� ���� Ŭ����ȭ ���Ѽ�
		//�ν��Ͻ����� �����ϰ� ȭ�鿡 �ٿ�����~!������
		String classString = t_input.getText();
		System.out.println("����� �����ϰ���� Ŭ���� ���� : "+classString);
		
		try {
			Class myClass = Class.forName(classString);
			
			//myClass�� �ν��Ͻ��� ����!!
			Object obj = myClass.newInstance();
			this.add((Component)obj);
			this.revalidate();//update JFrame
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "API�� �����ض�!!");
			e.printStackTrace();
		} catch (InstantiationException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new DynamicLoad();
	}
	
}


































