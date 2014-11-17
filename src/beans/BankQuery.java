package beans;

import java.io.Serializable;

public class BankQuery implements Serializable{
	private static final long serialVersionUID = 1L;
	private String holder = "";
	private String account = "";
	private String routing = "";
	private String feedback = "";
	
	public BankQuery() {
		super();
	}

	public BankQuery(String holder, String account, String routing, String feedback) {
		super();
		this.holder = holder;
		this.account = account;
		this.routing = routing;
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

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "BankQuery [holder=" + holder + ", account=" + account
				+ ", routing=" + routing + ", feedback=" + feedback + "]";
	}
}
