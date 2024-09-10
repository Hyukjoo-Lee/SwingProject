package fintech.com.swingproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fintech.com.swingproject.model.User;

/**
 * DB Connection 클래스
 */
public class DBCon {

	// DB 연결 객체
	private Connection conn = null;

	private static final String URL = "jdbc:oracle:thin:@192.168.41.67:1521:xe";
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


	// 연결 객체 가져오기
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
