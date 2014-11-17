package beans;

import java.io.Serializable;

public class RegistrationEntry implements Serializable{
	private static final long serialVersionUID = 1L;
	private String first = "";
	private String last = "";
	private String email= "";
	private String phone = "";
	private String feedback = "";
	
	public RegistrationEntry() {
		super();
	}

	public RegistrationEntry(String first, String last, String email,
			String phone, String feedback) {
		super();
		this.first = first;
		this.last = last;
		this.email = email;
		this.phone = phone;
		this.feedback = feedback;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
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

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "RegistrationEntry [first=" + first + ", last=" + last
				+ ", email=" + email + ", phone=" + phone + ", feedback="
				+ feedback + "]";
	}
}
