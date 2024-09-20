package fintech.com.swingproject.model;

/**
 * 유저( 클래스: 회원가입 및 정보 DTO
 */
public class User {
	private String username; // ID - Primary key 로 만들기 (테이블 생성 시)
	private String password;
	private String department;
	private String position;
	private String email;
	private String phone;
	private String status; // boolean 으로 변환 예정: 체크박스로 표현 예정
	
//	 public User(String username, String password, String department, String position, String email, String phone, String status) {
//		this.username = username;
//		this.password = password;

//	}

	public User(String username, String password, String department, String position, String email, String phone,
			String status) {
		this.username = username;
		this.password = password;
		this.department = department;
		this.position = position;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}

	public User(String username, String password, String phone, String department) {
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.department = department;
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
