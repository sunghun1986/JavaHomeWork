package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MakeMemo extends JFrame{
	
	JMenuBar bar;
	JMenu m_file;
	JMenuItem item_open, item_save, item_exit;
	JTextArea area;
	JScrollPane scroll;
	JFileChooser chooser;
	FileInputStream fis; //1byte���� ���� (������ ����)
	InputStreamReader reader; //���ڱ�ݽ�Ʈ�� 2byte�� ���ذ���.(��繮�ڰ���)
	FileOutputStream fos;
	BufferedReader buffr; //����ó���� ���ڱ�� ��Ʈ��
	
	public MakeMemo() {
	
		setJMenuBar(bar = new JMenuBar());
		bar.setBackground(Color.BLACK);//������	

		m_file = new JMenu("����");	
		
		item_open = new JMenuItem("����");
		item_save = new JMenuItem("����");
		item_exit = new JMenuItem("����");
		chooser = new JFileChooser("D:/web_app/java_workspace");

		m_file.add(item_open);
		m_file.add(item_save);
		m_file.addSeparator();//���м�
		m_file.add(item_exit);

		m_file.setForeground(Color.WHITE);//�۾���
		m_file.setPreferredSize(new Dimension(100,40));// �޴��ٻ���� �� Ű��		

		area = new JTextArea();
		scroll = new JScrollPane(area);

		area.setBackground(Color.YELLOW);
		bar.add(m_file);
		add(scroll);	

		setSize(600,500);
		setVisible(true);
		setLocationRelativeTo(null);
		
		item_open.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		
		item_save.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		
		item_exit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				exitMemo();
			}
		});
		
	}
	
	public void openFile() {
		int result = chooser.showOpenDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			//����ڰ� ��� ������ ���Ͽ� ���� ������ ���� File �ν��Ͻ� ���!
			File file = chooser.getSelectedFile();
			this.setTitle(file.getName());
			
			
//				buffr = new BufferedReader(new InputStreamReader(fis, file.getAbsolutePath()));
				try {
					fis = new FileInputStream(file.getAbsolutePath());
					reader = new InputStreamReader(fis);
					buffr = new BufferedReader(reader);
					String data = null;	
										
					while(true) {
						data = buffr.readLine();
						if(data == null)break;
						area.append(data+"\n");					
					}					
				} catch (FileNotFoundException e) {				
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(buffr == null) {
						try {
							buffr.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		}		
	}
	
	public void saveFile() {
		
	}
	
	public void exitMemo() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new MakeMemo();
	}

}






























