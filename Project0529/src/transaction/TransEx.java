package transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import excel.ConnectionManager;

//����Ŭ �����ͺ��̽��� ���� Ʈ����� ó���� �ڹ� �ڵ�� �����غ���!!
//����� jdbc�� Ʈ������� �ڵ� Ŀ�ԵǾ� �ִ�!! �׷��� �׵��� ������ ������
//commit ������ ���������̴�............
public class TransEx {
	
	ConnectionManager connetionManager = ConnectionManager.getInstance();	
	
	public TransEx() {
		//�ΰ��� ���̺� ���� insert ��Ȳ�� ������!!
		//�� ��� ���ξ����� 2��¥���� Ʈ����� ��Ȳ�̴�!!
		Connection con = connetionManager.getConnection();
		PreparedStatement pstmt = null;
		
		//�μ��� �ֱ�
		String sql = "insert into dept(deptno,dname,loc) values(50,?,?)";
		try {
			con.setAutoCommit(false);//�ڵ�Ŀ�� ���ϰڴ�~!~!
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "MARKETING");
			pstmt.setString(2, "SEOUL");
			
			pstmt.executeUpdate();//���� ����
			
			sql="insert into emp(empno,ename,deptno) values(7777,'avery',50)";
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();//���� ����2
			
			//��� ���ξ����� ���������Ƿ�, ��ü�� �������� �����Ѵ�
			//commit
			con.commit();
			System.out.println("�ΰ��� ���ڵ� ��ϿϷ�");
			
		} catch (SQLException e) {
			e.printStackTrace();
			//������ �߻��� ����̹Ƿ�, ��� ���ξ����� ó������ ������������ 
			//���ư��� �Ѵ�!!! rollback
			try {
				con.rollback();
				System.out.println("���ξ����� �����Ѱ� �����Ƿ�, ��� ���󺹱�");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				con.setAutoCommit(true);
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		new TransEx();
	}

}









