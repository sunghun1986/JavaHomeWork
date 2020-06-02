package db.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleTest {
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##java";
	String pw = "1234";
	
	Connection con; //������ �����Ǹ�, �� ���������� ���� ��ü�̴�
	//���� �� ��ü�� null�� ���� ������ �ȵȰ��̴�.
	PreparedStatement pstmt; //������ ���� ��ü
	//�������̽��̹Ƿ�, Connection��ü�κ��� �ν��Ͻ��� ����!!
	//���� ������ �����Ǿ�� �� ��ü�� ����� �� �ִ�!
	
	public OracleTest() {
		
		/*1�ܰ�: �ش� ��ǰ�� ���� ����̹� �ε� 
		 2�ܰ�: ���� 
		 3�ܰ�: ������ ���� 
		 4�ܰ�: �������� 
		 */
		
		//1�ܰ� ! ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����!!!");
			
			//2�ܰ� ����Ŭ�� ����!!
			con = DriverManager.getConnection(url,user,pw);
			
			if(con == null) {
				System.out.println("���� ����...");
			}else {
				System.out.println("���Ӽ���");
				//3�ܰ� ������ ����
				//������ ��������.
				String sql = "insert into member(member_id, name, phone, age)";
				sql+=" values(seq_member.nextval, '�����' , '010-9999-6710' , 5)";
				pstmt = con.prepareStatement(sql);
				int result = pstmt.executeUpdate();
				if(result == 0) {
					System.out.println("��Ͻ���...");
				}else {
					System.out.println("��ϼ���!");
				}
			}		
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����...");
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new OracleTest();
	}

}










