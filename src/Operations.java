import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Operations {
	
	public static void start() throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bank_db?createDatabaseIfNotExist=true", "root", "123456");
	}

}
