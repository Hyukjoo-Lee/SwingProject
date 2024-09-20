package fintech.com.swingproject.model;

/**
 * 유저( 클래스: 회원가입 및 정보 DTO
 */
public class User {
	private String username; // ID - Primary key 로 만들기 (테이블 생성 시)
	private String password;
	private String department;
	private String position;
	private String id; // 삭제 예정: username 을 조회 할때 ID 로 쓸거기 때문에
	private String email;
	private String phone;
	private String status;

	public User(String username, String password, String department) {
		this.username = username;
		this.password = password;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
