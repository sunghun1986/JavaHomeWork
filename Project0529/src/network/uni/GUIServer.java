package network.uni;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIServer extends JFrame implements Runnable{
	
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;	 
	ServerSocket server;
	Thread thread;
	Vector<MessageObj> clientList = new Vector<MessageObj>();
	
	public GUIServer() {
	
		p_north = new JPanel();
		t_port = new JTextField("7777", 10);
		bt = new JButton("서버가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_north.add(t_port);
		p_north.add(bt); 

		add(p_north, BorderLayout.NORTH);
		add(scroll);
		setBounds(400, 100, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
			
	}
	
	@Override
	public void run() {
		
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}


}
