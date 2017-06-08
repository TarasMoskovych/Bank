import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionDB {

	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	
	public static void createTransactionTable(Connection connection) throws SQLException{
		preparedStatement = connection.prepareStatement("create table if not exists transaction(id int primary key auto_increment, name varchar(20) not null, type varchar(20) not null, date varchar(20) not null, amount float, id_Account int)");
		preparedStatement.execute();
		constraint(connection);
	}
	
	public static void constraint(Connection connection) throws SQLException{
		preparedStatement = connection.prepareStatement("alter table transaction add constraint foreign key(id_Account) references account(id)");
		preparedStatement.execute();
	}
	
	public static void createNewTransaction(Account account, Connection connection) throws SQLException{
		preparedStatement = connection.prepareStatement("insert into transaction(name, type, date, amount, id_Account) values(?,?,?,?,?)");
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input transaction name: ");
		String name = sc.nextLine();
		System.out.println("Input transaction type:\n1 - Income\n2 - Outcome");
		System.out.print(">");
		
		int o = sc.nextInt();
		float amount = 0;
		
		if(o == 1){
			amount = income(account);
			String type = "Income " + amount;
			preparedStatement.setString(2, "Income");
		}
		if(o == 2){
			amount = outcome(connection, account);
			String type = "Outcome " + amount;
			preparedStatement.setString(2, "Outcome");
		}
		
		System.out.print("Input transaction date: ");
		String date = sc.nextLine();
		
		preparedStatement.setString(1, name);
		
		System.out.println("Input transaction date: ");
		preparedStatement.setString(3, date);
		preparedStatement.setFloat(4, amount);
		
		int id = account.getId();
		preparedStatement.setInt(5, id);
		
		preparedStatement.execute();
		
		
		System.out.println("Amount:" + amount);
		
		AccountDB.updateAmount(amount, connection, account.getId());
		System.out.println("Done!");	
	}
	
	public static float income(Account account){
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input amount: ");
		float value = sc.nextFloat();
		float getValue = account.getAmount();
		account.setAmount(account.getAmount() + value);
		return getValue + value;
	}
	
	public static float outcome(Connection connection, Account account) throws SQLException{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input amount: ");
		float value = sc.nextFloat();
		
		float getValue = account.getAmount();
		if((account.getAmount() - value) < 0){
			try {
				throw new NoMoneyException("You don't have enough money to withdraw!");
			} catch (NoMoneyException e) {
				Operations.transactionOperations(connection, account);
			}
			
		}
		account.setAmount(account.getAmount() - value);
		return getValue - value;
	}
	
	public static void printTransactions(Connection connection, Account account) throws SQLException{
		preparedStatement = connection.prepareStatement("select * from transaction where id_Account = ?");
		preparedStatement.setInt(1, account.getId());
		resultSet = preparedStatement.executeQuery();
		
		List<Transaction> trList = new ArrayList<>();
		
		while(resultSet.next()){
			Transaction transaction = new Transaction(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("type"), resultSet.getString("date"), resultSet.getFloat("amount"), resultSet.getInt("id_Account"));
			trList.add(transaction);
		}
		
		for(Transaction t: trList){
			System.out.println(t);
		}	
	}
	
	public static void deleteTransaction(Connection connection) throws SQLException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Input transaction id: ");
		int id = sc.nextInt();
		
		preparedStatement = connection.prepareStatement("delete from transaction where id = ?");
				
		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		
		System.out.println("Done!");
	}
}
