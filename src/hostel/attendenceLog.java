package hostel;

import static hostel.HMS.DB_URL;
import static hostel.HMS.JDBC_DRIVER;
import static hostel.HMS.PASSWORD;
import static hostel.HMS.USERNAME;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

/**
 *
 * @author JacksonIsaac
 */
public class attendenceLog extends JFrame
{
	
	JFrame attperc;
	
	public attendenceLog()
	{
		attperc = new JFrame("Attendence Log Records");
		attperc.setLayout(new FlowLayout());
		
		Connection conn = null;
		Statement st = null;

		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			st = conn.createStatement();

			String sql;
			ResultSet rs;
			sql = "Select * from att_percent";
			
			System.out.println(sql);

			try {
				rs = st.executeQuery(sql);
				
				DataTable data_table;
				data_table = new DataTable(rs);
				JScrollPane tableScroll = new JScrollPane(data_table);
				
				data_table.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				
				attperc.add(data_table);
				attperc.pack();
				attperc.setVisible(true);
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
