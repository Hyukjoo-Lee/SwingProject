package fintech.com.swingproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fintech.com.swingproject.model.User;

/**
 * DB Connection 클래스
 */
public class DBCon {

	// DB 연결 객체
	private Connection conn = null;

//	private static final String URL = "jdbc:oracle:thin:@192.168.41.67:1521:xe";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USERNAME = "fin_team";
	private static final String PASSWORD = "1234";

	// 생성자
	public DBCon() {
		connectDB(URL, USERNAME, PASSWORD);
	}

	public void connectDB(String URL, String USERNAME, String PASSWORD) {
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

	// 유저 테이블 생성하는 메소드: USERS 테이블 존재 할 시 테이블을 삭제하여 초기화
	public void createUserTable() {
		String sql = "CREATE TABLE USERS (" + "USERNAME VARCHAR(50) NOT NULL PRIMARY KEY, "
				+ "PASSWORD VARCHAR(255) NOT NULL, " + "DEPARTMENT VARCHAR(50), " + "POSITION VARCHAR(50), "
				+ "ID NUMBER(20), " + "EMAIL VARCHAR(100), " + "PHONE VARCHAR(15), " + "STATUS VARCHAR(20)" + ")";

		String dropTableSql = "DROP TABLE USERS";
		String checkTableSql = "SELECT COUNT(*) FROM user_tables WHERE table_name = 'USERS'";

		try (Connection conn = getConnection()) {
			// 테이블 존재 여부 확인
			try (PreparedStatement checkStmt = conn.prepareStatement(checkTableSql);
					ResultSet rs = checkStmt.executeQuery()) {

				rs.next();
				// 테이블이 존재하면 삭제
				if (rs.getInt(1) > 0) {
					try (PreparedStatement dropStmt = conn.prepareStatement(dropTableSql)) {
						dropStmt.executeUpdate();
						System.out.println("기존 USERS 테이블 삭제완료");
					}
				}
			}

			// 테이블 생성
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.executeUpdate();
				System.out.println("USERS 테이블 생성 완료");
			}

		} catch (SQLException e) {
			System.out.println("테이블 생성 실패: " + e.getMessage());
		}
	}

	// 유저 테이블 생성하는 메소드: USERS 테이블 존재하지 않을 시에만 테이블을 생성
	public void createUserTableIfNotExists() {
		String sql = "CREATE TABLE USERS (" + "USERNAME VARCHAR(50) NOT NULL PRIMARY KEY, "
				+ "PASSWORD VARCHAR(255) NOT NULL, " + "DEPARTMENT VARCHAR(50), " + "POSITION VARCHAR(50), "
				+ "EMAIL VARCHAR(100), " + "PHONE VARCHAR(15), " + "STATUS VARCHAR(20)" + ")";

		String checkTableSql = "SELECT COUNT(*) FROM user_tables WHERE table_name = 'USERS'";

		try (Connection conn = getConnection()) {
			// 테이블 존재 여부 확인
			try (PreparedStatement checkStmt = conn.prepareStatement(checkTableSql);
					ResultSet rs = checkStmt.executeQuery()) {

				rs.next();
				// 테이블이 존재하지 않으면 생성
				if (rs.getInt(1) == 0) {
					try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
						pstmt.executeUpdate();
						System.out.println("USERS 테이블 생성 완료");
					}
				} else {
					System.out.println("USERS 테이블 이미 존재");
				}
			}

		} catch (SQLException e) {
			System.out.println("테이블 생성 실패: " + e.getMessage());
		}
	}

	// 유저 정보 입력 메서드
	public void insertUser(User user) {
		PreparedStatement pstmt = null;
		try {
			connectDB(URL, USERNAME, PASSWORD);
			// 유저 정보를 USERS 테이블에 삽입
			String sql = "INSERT INTO USERS (USERNAME, PASSWORD, DEPARTMENT, POSITION, EMAIL, PHONE, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getDepartment());
			pstmt.setString(4, user.getPosition());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getPhone());
			pstmt.setInt(7, user.getStatus() ? 1:0);
			pstmt.executeUpdate();
			System.out.println("유저 추가 완료");
		} catch (SQLException e) {
			System.out.println("유저 추가 실패: " + e.getMessage());
		} finally {
			close(conn, pstmt); // 닫아 줌
		}
	}
	
	// 유저 회원가입 메서드
	public void joinUser(User user) {
		PreparedStatement pstmt = null;
		try {
			connectDB(URL, USERNAME, PASSWORD);
			// 유저 정보를 USERS 테이블에 삽입
			String sql = "INSERT INTO USERS (USERNAME, PASSWORD, PHONE, DEPARTMENT) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getDepartment());
			pstmt.executeUpdate();
			System.out.println("유저 회원가입 완료");
		} catch (SQLException e) {
			System.out.println("유저 회원가입 실패: " + e.getMessage());
		} finally {
			close(conn, pstmt); // 닫아 줌
		}
	}

	// 유저 정보를 삭제하는 메서드
	public void deleteUser(String username) {
		PreparedStatement pstmt = null;
		try {
			connectDB(URL, USERNAME, PASSWORD);
			String sql = "DELETE FROM USERS WHERE USERNAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("유저 삭제 완료: " + username);
			} else {
				System.out.println("유저가 존재하지 않거나 삭제되지 않았습니다: " + username);
			}
		} catch (SQLException e) {
			System.out.println();
			System.out.println("유저 삭제 실패: " + e.getMessage());
		} finally {
			close(conn, pstmt); // 닫아줌
		}
	}
	
	/**
	 * 유저 정보 조회하는 메서드
	 * 검색된 유저 리스트를 반환하는 메서드
	 * 중복이 되지 않지만 나중에 추가 기능을 위한 ArrayList<User> 반환값 설정
	 */
	public ArrayList<User> searchUser(String username) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> list = new ArrayList<>(); // 검색된 유저를 저장할 리스트
		try {
			connectDB(URL, USERNAME, PASSWORD);
			// 유저 이름을 기준으로 검색하는 SQL 쿼리
			String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			// 검색된 데이터를 리스트에 저장
			while (rs.next()) {
				User user = new User(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("DEPARTMENT"),
						rs.getString("POSITION"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getBoolean("STATUS"));

				list.add(user);
			}
		} catch (SQLException e) {
			System.err.println("유저 검색 실패:" + e.getMessage());
			throw e;
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	// 연결 객체 가져오기
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
