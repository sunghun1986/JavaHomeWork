package excel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
	
	public ExchangeData() {

		table = new JTable();
		scroll = new JScrollPane(table);
		xmlArea = new JTextArea();
		jsonArea = new JTextArea();
		bt_test = new JButton("�������ϸ����");
		bt_db = new JButton("DB�� ������ ����");
		bt_xml = new JButton("XML�� ������ ����");
		bt_json = new JButton("JSON�� ������ ����");
		
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
			
		}else if(obj == bt_xml) {
			
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
	
	public static void main(String[] args) {
		new ExchangeData();
	}

	
}
























