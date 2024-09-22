package fintech.com.swingproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fintech.com.swingproject.model.User;
import fintech.com.swingproject.MainPage;
import fintech.com.swingproject.pages.*;

/**
 * DB Connection 클래스
 */
public class DBCon {

	// DB 연결 객체
	private Connection conn = null;

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "fin_team";
	private static final String PASSWORD = "1234";

	// 생성자
	public DBCon() {
		try {
			// DriverManager 등록 - 오라클 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("드라이버 로딩 실패: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("DB 접속 실패: " + sqle.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 자원 해제 메서드
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫기 실패: " + e.getMessage());
			}
		}
		close(conn, ps);
	}

	// close 메소드 오버로딩
	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫기 실패: " + e.getMessage());
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("Connection 닫기 실패: " + e.getMessage());
			}
		}
	}

	// 유저 테이블 생성하는 메소드: 초기에는 SQL manager 로 직접 테이블 생성 후 테스트 함 - 구현필요
	public void createUserTable() {

	}

	// 해당 테이블이 없으면 생성하는 메서드 필요
	private void createTableIfNotExists() throws SQLException {
	}
	


	// 유저 정보 입력
	public void insertUser(User user) {
		PreparedStatement pstmt = null;
		try {
			// 유저 정보를 USERS 테이블에 삽입
			String sql = "INSERT INTO USERS(USERNAME, PASSWORD) VALUES(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
			System.out.println("유저 회원가입 완료");
		} catch (SQLException e) {
			System.out.println("유저 회원가입 실패: " + e.getMessage());
		} finally {
			close(conn, pstmt);
		}
	}

	// username을 기준으로 사용자 검색 메서드 
	//유저 검색 메서드 (ResultSet을 반환)
	public ResultSet searchUser(String username) throws SQLException {
		PreparedStatement pstmt = null; 
		ResultSet rs = null; // 쿼리 실행 결과를 담는 객체이다.
		try {
			// 유저 이름을 기준으로 검색하는 SQL 쿼리
			String sql = "SELECT USERNAME FROM USERS WHERE USERNAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username); // 첫 번째 ?(실제값) 자리에 유저 이름 설정
			System.out.println(pstmt);
			rs = pstmt.executeQuery(); // 쿼리 실행 후 결과를 ResultSet으로 받음
			System.out.println(rs);
		} catch (SQLException e) {
			System.err.println("유저 검색 싶패:" + e.getMessage());
			throw e;
		}
		return rs;
	}
	//리소스 해제 메서드 
	public void close(ResultSet rs, PreparedStatement pstmt) {
		try {
			if(rs != null) rs.close();
			if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
