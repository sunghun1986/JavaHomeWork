package imagegallery;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JFrameJPanelPractice extends JFrame{
	
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JLabel label1;
	JLabel label2;
	JLabel label3;	
	JTextField f1;
	JTextField f2;
	JTextField f3;
	JButton bt;
	
	public JFrameJPanelPractice() {
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		
		label1 = new JLabel("이름");
		label2 = new JLabel("나이");
		label3 = new JLabel("주소");
		
		f1 = new JTextField();
		f2 = new JTextField();
		f3 = new JTextField();
		
		bt = new JButton("입력");
		
		setPreferredSize(new Dimension(400,150));		
		
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
		panel1.add(label1);
		panel1.add(f1);
		
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
		panel2.add(label2);
		panel2.add(f2);
		
		panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));
		panel3.add(label3);
		panel3.add(f3);
		
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		panel4.add(panel1);
		panel4.add(panel2);
		panel4.add(panel3);
	
		add(panel4,BorderLayout.CENTER);
		add(bt,BorderLayout.SOUTH);
//		setSize(300,300);
		pack();
		setLocation(200, 400);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new JFrameJPanelPractice();
	}

}

























