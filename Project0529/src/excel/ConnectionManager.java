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

	//�ܺ��� Ŭ������ �Ժη� new ���� ���ϵ��� ���´�!!
	private ConnectionManager() {

		props = new Properties();

		InputStream is = this.getClass().getResourceAsStream("/database/dbms.db");

		try {
			props.load(is);
			System.out.println(is);
			
			//key������ ������ �о����!
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

//   ������ â ���� �� ȣ��
	public void closeDB(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

//   DML(insert, update, delete) ������ �� ȣ��
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

//   select ������ �� ȣ��
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
	
	
	//�ν��Ͻ��� ��ȯ�ϴ� �޼��� ����!
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






















