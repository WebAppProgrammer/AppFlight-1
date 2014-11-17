package beans;

import java.io.Serializable;

public class TransactionQuery implements Serializable{
	private static final long serialVersionUID = 1L;
	private String holder = "";
	private String account = "";
	private String routing = "";
	private String passenger = "";
	private String dob = "";
	private String gender = "";
	private String feedback = "";
	
	public TransactionQuery() {
		super();
	}

	public TransactionQuery(String holder, String account, String routing,
			String passenger, String dob, String feedback) {
		super();
		this.holder = holder;
		this.account = account;
		this.routing = routing;
		this.passenger = passenger;
		this.dob = dob;
		this.feedback = feedback;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRouting() {
		return routing;
	}

	public void setRouting(String routing) {
		this.routing = routing;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "TransactionQuery [holder=" + holder + ", account=" + account
				+ ", routing=" + routing + ", passenger=" + passenger
				+ ", dob=" + dob + ", gender=" + gender + ", feedback="
				+ feedback + "]";
	}
}
