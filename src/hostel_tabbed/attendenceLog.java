package hostel_tabbed;

import static hostel_tabbed.HMS.DB_URL;
import static hostel_tabbed.HMS.JDBC_DRIVER;
import static hostel_tabbed.HMS.PASSWORD;
import static hostel_tabbed.HMS.USERNAME;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

/**
 *
 * @author JacksonIsaac
 */
public class attendenceLog extends JPanel
{
	
	JPanel attperc;
	
	public attendenceLog()
	{
		attperc = new JPanel();
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
				
//				attperc.pack();
//				attperc.setVisible(true);
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
	
	public JPanel getAttPerc()
	{
		return attperc;
	}
}
