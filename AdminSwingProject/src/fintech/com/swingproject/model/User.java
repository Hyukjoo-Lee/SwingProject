package fintech.com.swingproject.model;

/**
 * 유저( 클래스: 회원가입 및 정보 DTO
 */
public class User {
	private String username;
	private String password;
	private String department;

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

}
