package network.uni;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

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
		
		bt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				thread = new Thread(GUIServer.this);
				thread.start();
			}
		});
		// 메세지가 누적될때, 커서 포커스가 스크롤의 제일 하단에 오게 처리
				DefaultCaret caret = (DefaultCaret) area.getCaret();
				caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}
	
	public void startServer() {
		
		int port = Integer.parseInt(t_port.getText());
		
		try {
			server = new ServerSocket(port);
			area.append("서버 생성 및 접속자 기다리는중..\n");
			
			Socket socket = server.accept();// 접속자가 발견될때까지 무한대기!!
			
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip + "님 접속함\n");
			
			MessageObj messageObj= new MessageObj(area, socket);
			messageObj.start();
			
			clientList.add(messageObj);
			area.append("현재까지 접속한 클라이언트 수는"+clientList.size()+"\n");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void run() {
		startServer();
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}


}
