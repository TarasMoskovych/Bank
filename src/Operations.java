import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Operations {
	
	public static void start() throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bank_db?createDatabaseIfNotExist=true", "root", "123456");
		
		AccountDB.createAccountTable(connection);
		TransactionDB.createTransactionTable(connection);
		
		menu(connection);
	}
	
	public static void menu(Connection connection) throws SQLException{
		
		System.out.println("Hello, user! Welcome to my bank!");
		Scanner sc = new Scanner(System.in);
		int o = 0;
		
		while(true){
			System.out.println("\nPlease choose operation:\n0 - Exit;\n1 - Login;\n2 - Create new account;\n3 - Delete account;\n4 - Print all accounts;");
			System.out.print(">");
			try{
				o = sc.nextInt();
				if(o == 0){
					System.out.println("GoodBye:)");
					connection.close();
					System.exit(0);
				}
				
			}
			
			catch(Exception e){
				System.out.println("Incorrect input! Try again!\n");
				menu(connection);
			}	
		} 
	}
	
	public static void transactionOperations(Connection connection, Account account) throws SQLException{
		System.out.println("\nHello, " + account.getName() + " " + account.getSurName());
				
		Scanner sc = new Scanner(System.in);
		int o = 0;
		
		do{
			System.out.println("Choose operations:\n1 - Sign out;\n2 - Create new transaction;\n3 - Print account details;\n4 - Print Transactions;\n5 - Delete transaction;");
			System.out.print(">>");
			o = sc.nextInt();
			if(o == 1) menu(connection);
		
			
		} while(o != 0);
	}
	
	public static void printAccount(Account account){
		System.out.println(account);
	}

}
