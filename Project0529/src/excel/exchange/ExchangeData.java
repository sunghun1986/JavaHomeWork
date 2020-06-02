package excel.exchange;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
	
	public ExchangeData() {

		table = new JTable(model = new EmpModel());
		scroll = new JScrollPane(table);
		xmlArea = new JTextArea();
		jsonArea = new JTextArea();
		bt_test = new JButton("엑셀파일만들기");
		bt_db = new JButton("DB를 엑셀로 저장");
		bt_xml = new JButton("XML를 엑셀로 저장");
		bt_json = new JButton("JSON를 엑셀로 저장");
		
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
			
		}else if(obj == bt_xml) {
			
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
	
	public static void main(String[] args) {
		new ExchangeData();
	}

	
}
























