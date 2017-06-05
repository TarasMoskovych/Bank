
public class Transaction {

	private int id;
	private String name;
	private String type;
	private String date;
	private float amount;
	private int id_Account;
	
	public Transaction(int id, String name, String type, String date, float amount, int id_Account) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.date = date;
		this.amount = amount;
		this.id_Account = id_Account;
	}
	
	public Transaction(String name, String type, String date, float amount) {
		super();
		this.name = name;
		this.type = type;
		this.date = date;
		this.amount = amount;
			}
	
	public Transaction(String name, String type, String date, float amount, int id_Account) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.date = date;
		this.amount = amount;
		this.id_Account = id_Account;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getId_Account() {
		return id_Account;
	}

	public void setId_Account(int id_Account) {
		this.id_Account = id_Account;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", name=" + name + ", type=" + type + ", date=" + date + ", amount=" + amount
				+ ", id_Account=" + id_Account + "]";
	}

}
