package hostel_tabbed;

import static hostel_tabbed.HMS.DB_URL;
import static hostel_tabbed.HMS.JDBC_DRIVER;
import static hostel_tabbed.HMS.PASSWORD;
import static hostel_tabbed.HMS.USERNAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JacksonIsaac
 */
public class execQuery
{

	public execQuery(String sql)
	{
		Connection conn = null;
		Statement st = null;

		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			st = conn.createStatement();

			
			System.out.println(sql);

			try {
				st.executeUpdate(sql);
			} catch (SQLException sqe) {
				System.out.println("Error " + sqe);
			}

			conn.close();
			st.close();
		} catch (Exception sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException sqe) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqe) {
			}
		}
	}
	
}
