package excel.exchange;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.xml.sax.SAXException;

import excel.ConnectionManager;

//�پ��� ������ �����͸� ���� ��ȯ�ϴ� ����
public class ExchangeData extends JFrame implements ActionListener{

	JTable table;
	JScrollPane scroll;
	JTextArea xmlArea;
	JTextArea jsonArea;
	JButton bt_test;
	JButton bt_db;
	JButton bt_xml;
	JButton bt_json;
	String path = "C:/Users/tjoeun/git/JavaHomeWork/Project0529/data/";
	EmpModel model;
	JFileChooser chooser;
	
	public ExchangeData() {

		table = new JTable(model = new EmpModel());
		scroll = new JScrollPane(table);
		xmlArea = new JTextArea("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		jsonArea = new JTextArea();
		bt_test = new JButton("�������ϸ����");
		bt_db = new JButton("DB�� ������ ����");
		bt_xml = new JButton("XML�� ������ ����");
		bt_json = new JButton("JSON�� ������ ����");
		chooser = new JFileChooser(path);
		
		//��Ÿ������
		table.setPreferredSize(new Dimension(330,500));
		xmlArea.setPreferredSize(new Dimension(330,500));
		jsonArea.setPreferredSize(new Dimension(330,500));
		
		//����
		setLayout(new FlowLayout());
		add(scroll);
		add(xmlArea);
		add(jsonArea);
		add(bt_test);
		add(bt_db);
		add(bt_xml);
		add(bt_json);
		
		loadDB();
		
		setSize(1200, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//��ư�� ������ ����
		bt_test.addActionListener(this);
		bt_db.addActionListener(this);
		bt_xml.addActionListener(this);
		bt_json.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_test) {
			createExcel();
		}else if(obj == bt_db) {
			convertDBToExcel();
		}else if(obj == bt_xml) {
			convertXMLToExcel();
		}else if(obj == bt_json) {
			
		}
	}
	//���� ������ �ڹ� ���α׷��������� �����غ���!!(���뵵 �����ϰ�...)
	//POI ���̺귯���� �ʿ���
	public void createExcel() {
		//����ִ� ��ũ�� ����
		HSSFWorkbook book = new HSSFWorkbook();
		
		//������ ���� ����
		HSSFSheet sheet = book.createSheet();//��Ʈ ����
		
		HSSFRow row = sheet.createRow(0);//row ����
		row.createCell(0).setCellValue("������ȣ");//car_id
		row.createCell(1).setCellValue("���̸�");//name
		row.createCell(2).setCellValue("�귣��");//brand
		row.createCell(3).setCellValue("����");//price
		
		//������ �����ϱ�
		HSSFRow data1 = sheet.createRow(1);
		data1.createCell(0).setCellValue(1);
		data1.createCell(1).setCellValue("Audi");
		data1.createCell(2).setCellValue("�ƿ��");
		data1.createCell(3).setCellValue(8500);
		
		
		//���ϰ� ���丮�� �����ϴ� /������ , �������ø� �˾Ƽ� ����!!!
		
		FileOutputStream fos = null;
		try {
			//���������� �������� �����Ѵ�!!
			book.write(new FileOutputStream(path+"/test.xls"));
		} catch (IOException e) {			
			e.printStackTrace();
		}finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}
		JOptionPane.showMessageDialog(this, "���ϻ����߾��");
	}
	
	//EMP ���̺� �ε�!!!
	public void loadDB() {
		ConnectionManager connectionManager =null;
		connectionManager = new ConnectionManager();
		Connection con = connectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from emp";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<Emp> list = new ArrayList<Emp>();			
			
			while(rs.next()) {
				
				Emp emp = new Emp();				
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("empno"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getString("hiredate"));
				emp.setSal(rs.getInt("sal"));
				emp.setComm(rs.getInt("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				list.add(emp);
			}
			
			//���� list �� ����!!
			model.list = list;
			table.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connectionManager.closeDB(con,pstmt,rs);
		}
		
		
	}
	
	//�����ͺ��̽��� ������ �����ϱ�!!
	public void convertDBToExcel() {
		int ans = chooser.showSaveDialog(this);
		FileOutputStream fos = null;//������ ��������� ������� ��Ʈ��
		
		if(ans == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();//������ ������ ���ϸ�!!
			
			//���� ���ϸ����� ���� ��������!!
			//���� ����ִ� ���� ����!!
			HSSFWorkbook book = new HSSFWorkbook();
			
			//���Ϸ� �����ϱ� ���� , ������ ä������!
			HSSFSheet sheet = book.createSheet();//����ִ� ��Ʈ ����!!
			
			//ArrayList�� ����ִ� ������ ����ŭ ������ row�� ����!!!
			
			for(int i = 0; i < model.list.size(); i++) {
				
				HSSFRow row = sheet.createRow(i);
				Emp emp = model.list.get(i);
				
				row.createCell(0).setCellValue(emp.getEmpno());//empno
				row.createCell(1).setCellValue(emp.getEname());//ename
				row.createCell(2).setCellValue(emp.getJob());//job
				row.createCell(3).setCellValue(emp.getMgr());//mgr
				row.createCell(4).setCellValue(emp.getHiredate());//hiredate
				row.createCell(5).setCellValue(emp.getSal());//sal
				row.createCell(6).setCellValue(emp.getComm());//comm
				row.createCell(7).setCellValue(emp.getDeptno());//deptno
			}
			
			//ä���� ������ ���� ������ ���Ϸ� ����!!!
			try {
				book.write(fos = new FileOutputStream(file));
				JOptionPane.showMessageDialog(this, "���� ���� �Ϸ�!");
			} catch (FileNotFoundException e) {			
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	//XML�� ������ ��ȯ�Ͽ� ����!!
	public void convertXMLToExcel() {
		//javaSE���� ��ü �����ϴ� �ļ��� �ִ�!!
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();//�ļ�����!
			
			//�Ľ��غ�Ϸ� �Ľ� ����!
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ExchangeData();
	}

	
}
























