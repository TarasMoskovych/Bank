import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AccountDB {
	
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	
	public static void createAccountTable(Connection connection) throws SQLException{
		preparedStatement = connection.prepareStatement("create table if not exists account(id int primary key auto_increment, name varchar(20) not null, surName varchar(20) not null, login varchar(20) unique not null, password varchar(20) not null, amount float)");
		preparedStatement.execute();
	}
	
	public static void createNewAccount(Connection connection) throws SQLException{
		preparedStatement = connection.prepareStatement("insert into account(name, surname, login, password) values(?,?,?,?)");
		
		Scanner sc = new Scanner(System.in);

		System.out.print("Input your Name:");
		String name = sc.nextLine();
		
		System.out.print("Input your Surname:");
		String surName = sc.nextLine();
		
		System.out.print("Input your Login:");
		String login = sc.nextLine();
		
		System.out.print("Input your Password:");
		String password = sc.nextLine();
		
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, surName);
		preparedStatement.setString(3, login);
		preparedStatement.setString(4, password);
		
		try{
			preparedStatement.execute();
			System.out.println("Done!");
		}
		catch (Exception e) {
			System.out.println("Exception! Try again");
			createNewAccount(connection);
		}
	}
	
	public static void deleteAccount(Connection connection) throws SQLException{
		System.out.print("Input your login: ");
		Scanner sc = new Scanner(System.in);
		String login = sc.nextLine();
		
		preparedStatement = connection.prepareStatement("select * from account");
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()){
			if(resultSet.getString("login").equals(login)){
				System.out.print("Input your password: ");
				String password = sc.nextLine();
				if(resultSet.getString("password").equals(password)){
					Account account = new Account(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surName"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getFloat("amount"));
					preparedStatement = connection.prepareStatement("delete from transaction where id_Account = ?");
					preparedStatement.setInt(1, account.getId());
					preparedStatement.execute();
					
					preparedStatement = connection.prepareStatement("delete from account where login = ?");
					preparedStatement.setString(1, login);
					preparedStatement.execute();
					
					System.out.println("Account '" + account.getName() + " " + account.getSurName() + "' has been deleted!");
					
				}
				else{
					System.out.println("Incorrect password!");
					break;
				}
			}
			
			else System.out.println("Account doesn't exist!");
		}
		
		Operations.menu(connection);
	}
	
}
