package network.uni;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame{
	
	JPanel p_north;
	Choice choice;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	Socket client;
	String ip="";
	int port=7777;
	
	public ChatClient() {
	
		p_north = new JPanel();
		choice = new Choice();
		choice.add("192.168.0.9");
		bt=new JButton("접속");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input = new JTextField();		

		//스타일 적용
		area.setBackground(Color.YELLOW);
		area.setFont(new Font("굴림", Font.BOLD, 16));
		
		//조립
		p_north.add(choice);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(t_input, BorderLayout.SOUTH);
		setBounds(100, 100, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}
	
}






















