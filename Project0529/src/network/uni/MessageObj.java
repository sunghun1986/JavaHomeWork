package network.uni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

public class MessageObj extends Thread{
	
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	JTextArea area;
	
	public MessageObj(JTextArea area,Socket socket) {
		
		this.area = area;
		this.socket = socket;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			area.append(msg + "\n");
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void send(String msg) {
		try {
			buffw.write(msg);
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		listen();
	}
	

}










