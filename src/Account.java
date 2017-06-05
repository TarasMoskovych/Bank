
public class Account {

	private int id;
	private String name;
	private String surName;
	private String login;
	private String password;
	private float amount;
	
	public Account(int id, String name, String surName, String login, String password, float amount) {
		super();
		this.id = id;
		this.name = name;
		this.surName = surName;
		this.login = login;
		this.password = password;
		this.amount = amount;
	}
		
	public Account() {
		super();
	}

	public Account(String name, String surName, String login, String password, float amount) {
		super();
		this.name = name;
		this.surName = surName;
		this.login = login;
		this.password = password;
		this.amount = amount;
	}
	
	public Account(String name, String surName) {
		super();
		this.name = name;
		this.surName = surName;
	}
	
	public Account(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", surName=" + surName + ", login=" + login + ", password="
				+ password + ", amount=" + amount + "]";
	}
	
}
