package transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import excel.ConnectionManager;

//오라클 데이터베이스에 대한 트랜잭션 처리를 자바 코드로 구현해본다!!
//참고로 jdbc는 트랜잭션이 자동 커밋되어 있다!! 그래서 그동안 쿼리문 수행후
//commit 한적이 없었던것이다............
public class TransEx {
	
	ConnectionManager connetionManager = ConnectionManager.getInstance();	
	
	public TransEx() {
		//두개의 테이블에 대한 insert 상황을 만들어보자!!
		//이 경우 세부업무가 2개짜리인 트랜잭션 상황이다!!
		Connection con = connetionManager.getConnection();
		PreparedStatement pstmt = null;
		
		//부서에 넣기
		String sql = "insert into dept(deptno,dname,loc) values(50,?,?)";
		try {
			con.setAutoCommit(false);//자동커밋 안하겠다~!~!
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "MARKETING");
			pstmt.setString(2, "SEOUL");
			
			pstmt.executeUpdate();//쿼리 수행
			
			sql="insert into emp(empno,ename,deptno) values(7777,'avery',50)";
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();//쿼리 수행2
			
			//모든 세부업무가 성공했으므로, 전체를 성공으로 간주한다
			//commit
			con.commit();
			System.out.println("두개의 레코드 등록완료");
			
		} catch (SQLException e) {
			e.printStackTrace();
			//에러가 발생한 경우이므로, 모든 세부업무가 처음부터 없었던것으로 
			//돌아가야 한다!!! rollback
			try {
				con.rollback();
				System.out.println("세부업무중 실패한게 있으므로, 모두 원상복귀");
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









