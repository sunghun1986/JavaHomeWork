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

//다양한 형태의 데이터를 서로 전환하는 예제
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
		bt_test = new JButton("엑셀파일만들기");
		bt_db = new JButton("DB를 엑셀로 저장");
		bt_xml = new JButton("XML를 엑셀로 저장");
		bt_json = new JButton("JSON를 엑셀로 저장");
		chooser = new JFileChooser(path);
		
		//스타일적용
		table.setPreferredSize(new Dimension(330,500));
		xmlArea.setPreferredSize(new Dimension(330,500));
		jsonArea.setPreferredSize(new Dimension(330,500));
		
		//조립
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
		
		//버튼과 리스너 연결
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
	//엑셀 파일을 자바 프로그래밍적으로 생성해보자!!(내용도 구성하고...)
	//POI 라이브러리가 필요함
	public void createExcel() {
		//비어있는 워크북 생성
		HSSFWorkbook book = new HSSFWorkbook();
		
		//엑셀의 내용 구성
		HSSFSheet sheet = book.createSheet();//쉬트 생성
		
		HSSFRow row = sheet.createRow(0);//row 생성
		row.createCell(0).setCellValue("고유번호");//car_id
		row.createCell(1).setCellValue("차이름");//name
		row.createCell(2).setCellValue("브랜드");//brand
		row.createCell(3).setCellValue("가격");//price
		
		//데이터 구성하기
		HSSFRow data1 = sheet.createRow(1);
		data1.createCell(0).setCellValue(1);
		data1.createCell(1).setCellValue("Audi");
		data1.createCell(2).setCellValue("아우디");
		data1.createCell(3).setCellValue(8500);
		
		
		//파일과 디렐토리를 구분하는 /슬래시 , 역슬래시를 알아서 생성!!!
		
		FileOutputStream fos = null;
		try {
			//엑셀파일을 동적으로 생성한다!!
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
		JOptionPane.showMessageDialog(this, "파일생성했어요");
	}
	
	//EMP 테이블 로드!!!
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
			
			//모델의 list 에 대입!!
			model.list = list;
			table.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connectionManager.closeDB(con,pstmt,rs);
		}
		
		
	}
	
	//데이터베이스를 엑셀로 저장하기!!
	public void convertDBToExcel() {
		int ans = chooser.showSaveDialog(this);
		FileOutputStream fos = null;//빈파일 만들기위한 파일출력 스트림
		
		if(ans == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();//유저가 저장할 파일명!!
			
			//위의 파일명으로 엑셀 생성하자!!
			//먼제 비어있는 엑셀 생성!!
			HSSFWorkbook book = new HSSFWorkbook();
			
			//파일로 저장하기 전에 , 내용을 채워넣자!
			HSSFSheet sheet = book.createSheet();//비어있는 쉬트 생성!!
			
			//ArrayList에 들어있는 데이터 수만큼 엑셀의 row도 생성!!!
			
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
			
			//채워진 엑셀을 실제 물리적 파일로 생성!!!
			try {
				book.write(fos = new FileOutputStream(file));
				JOptionPane.showMessageDialog(this, "파일 생성 완료!");
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
	
	//XML을 엑셀로 변환하여 저장!!
	public void convertXMLToExcel() {
		//javaSE에는 자체 지원하는 파서가 있다!!
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();//파서생성!
			
			//파싱준비완료 파싱 시작!
			
			
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
























