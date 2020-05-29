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
		bt = new JButton("��������");
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
		// �޼����� �����ɶ�, Ŀ�� ��Ŀ���� ��ũ���� ���� �ϴܿ� ���� ó��
				DefaultCaret caret = (DefaultCaret) area.getCaret();
				caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}
	
	public void startServer() {
		
		int port = Integer.parseInt(t_port.getText());
		
		try {
			server = new ServerSocket(port);
			area.append("���� ���� �� ������ ��ٸ�����..\n");
			
			Socket socket = server.accept();// �����ڰ� �߰ߵɶ����� ���Ѵ��!!
			
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip + "�� ������\n");
			
			MessageObj messageObj= new MessageObj(area, socket);
			messageObj.start();
			
			clientList.add(messageObj);
			area.append("������� ������ Ŭ���̾�Ʈ ����"+clientList.size()+"\n");
			
			
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
