import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Test {

	public static void main(String[] args) {

		String configFile = "D:\\JavaApps\\Hikaricp-ConnectionPooling\\db.properties";

		HikariConfig config = new HikariConfig(configFile);
		Connection connection = null;
		ResultSet resultSet = null;
		try (HikariDataSource dataSource = new HikariDataSource(config)) {

			connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			String selectQuery = "select id,firstname, lastname, city from friends";
			resultSet = stmt.executeQuery(selectQuery);

			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
						+ "\t" + resultSet.getString(4));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null)
				connection.close();
				if(resultSet!=null)
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
