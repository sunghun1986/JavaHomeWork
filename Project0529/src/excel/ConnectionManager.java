package excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	static ConnectionManager instance;
	String url;
	String user;
	String password;

	Properties props;
	FileInputStream fis;

	//외부의 클래스가 함부로 new 하지 못하도록 막는다!!
	private ConnectionManager() {

		props = new Properties();

		InputStream is = this.getClass().getResourceAsStream("/database/dbms.db");

		try {
			props.load(is);
			System.out.println(is);
			
			//key값으로 데이터 읽어오기!
			url = (String)props.get("oracle_url");
			user = (String)props.get("oracle_userid");
			password = (String)props.get("oracle_password");		
			
			System.out.println(url);
			System.out.println(user);
			System.out.println(password);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Connection getConnection() {
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return con;
	}

//   윈도우 창 닫을 때 호출
	public void closeDB(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

//   DML(insert, update, delete) 수행할 때 호출
	public void closeDB(Connection con, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

//   select 수행할 때 호출
	public void closeDB(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		closeDB(con, pstmt);
	}
	
	
	//인스턴스를 반환하는 메서드 정의!
	public static ConnectionManager getInstance() {
		if(instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		new ConnectionManager();
	}

}






















