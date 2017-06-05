import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDB {

	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	
	public static void createAccountTable(Connection connection) throws SQLException{
		preparedStatement = connection.prepareStatement("create table if not exists account(id int primary key auto_increment, name varchar(20) not null, surName varchar(20) not null, login varchar(20) unique not null, password varchar(20) not null, amount float)");
		preparedStatement.execute();
	}
}
