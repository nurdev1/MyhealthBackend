package Myhealth.myhealth.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
  private String username;
	@NotBlank
	private String codePatient;

	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCodePatient() {
		return codePatient;
	}

	public void setCodePatient(String codePatient) {
		this.codePatient = codePatient;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
