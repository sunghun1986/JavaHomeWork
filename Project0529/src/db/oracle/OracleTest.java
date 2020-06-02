package db.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleTest {
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##java";
	String pw = "1234";
	
	Connection con; //접속이 성공되면, 그 접속정보를 가진 객체이다
	//따라서 이 객체가 null일 경우는 접속이 안된것이다.
	PreparedStatement pstmt; //쿼리문 수행 객체
	//인터페이스이므로, Connection객체로부터 인스턴스를 얻어옴!!
	//따라서 접속이 성공되어야 이 객체를 사용할 수 있다!
	
	public OracleTest() {
		
		/*1단계: 해당 제품에 대한 드라이버 로드 
		 2단계: 접속 
		 3단계: 쿼리문 실행 
		 4단계: 접속해제 
		 */
		
		//1단계 ! 드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공!!!");
			
			//2단계 오라클에 접속!!
			con = DriverManager.getConnection(url,user,pw);
			
			if(con == null) {
				System.out.println("접속 실패...");
			}else {
				System.out.println("접속성공");
				//3단계 쿼리문 실행
				//가상의 쿼리문임.
				String sql = "insert into member(member_id, name, phone, age)";
				sql+=" values(seq_member.nextval, '고양이' , '010-9999-6710' , 5)";
				pstmt = con.prepareStatement(sql);
				int result = pstmt.executeUpdate();
				if(result == 0) {
					System.out.println("등록실패...");
				}else {
					System.out.println("등록성공!");
				}
			}		
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패...");
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










