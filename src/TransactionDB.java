import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
