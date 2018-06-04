package media.co.kr.dto;
public class UserDto {
	private int idx;
	private String password;
	private String email;
	private String name;
	private String message;
	private int problem;
	public int getIdx() {
		return idx;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getProblem() {
		return problem;
	}
	public void setProblem(int problem) {
		this.problem = problem;
	}
	public UserDto( String pwd, String name,String email, String message, int problem) {
		super();
		this.password = pwd;
		this.email = email;
		this.name = name;
		this.message = message;
		this.problem = problem;
	}
	public UserDto() {
		
	}

}
