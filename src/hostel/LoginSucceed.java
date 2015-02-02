package hostel;

import static hostel.HMS.DB_URL;
import static hostel.HMS.JDBC_DRIVER;
import static hostel.HMS.PASSWORD;
import static hostel.HMS.USERNAME;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 *
 * @author JacksonIsaac
 */
public class LoginSucceed extends JFrame implements ActionListener
{

	JButton addstud, attn, allDept;
//	JButton select, update, delete;
	JFrame hms;

	@SuppressWarnings("LeakingThisInConstructor")
	public LoginSucceed()
	{
		try {
			hms = new JFrame("Welcome to Hostel Management System.");
			BufferedImage img = ImageIO.read(new File("/Users/JacksonIsaac/attn.jpg"));
			hms.setContentPane(new JLabel(new ImageIcon(img)));

			hms.setLayout(new FlowLayout());

			hms.setVisible(true);

//		JLabel sel = new JLabel("Select");
//		JLabel up = new JLabel("Update");
//		JLabel del = new JLabel("Delete");
			addstud = new JButton("Add Student");
			attn = new JButton("Attendence Log");
			allDept = new JButton("Student, Faulty Departments");
//		select = new JButton("Select");
//		update = new JButton("Update");
//		delete = new JButton("Delete");

			addstud.addActionListener(this);
			attn.addActionListener(this);
			allDept.addActionListener(this);
//		select.addActionListener(this);
//		update.addActionListener(this);
//		delete.addActionListener(this);

			hms.add(addstud);
			hms.add(attn);
			hms.add(allDept);
//		hms.add(select);
//		hms.add(update);
//		hms.add(delete);

			hms.pack();

			hms.setVisible(true);
		} catch (IOException ex) {
			Logger.getLogger(LoginSucceed.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == addstud) {
			addStudent ad = new addStudent();
		}
		if (e.getSource() == attn) {
			attendance att = new attendance();
		}
		if (e.getSource() == allDept) {
			showCommDept();
		}
	}

	public void showCommDept()
	{
		JFrame commDept = new JFrame("Student, Faculty Departments");
		String sql = "with all_dept as ((Select deptid from student) UNION (Select deptid from faculty)),"
			+ "	common_dept as ((Select deptid from student) INTERSECT (Select deptid from faculty))"
			+ "Select a.deptid as \"Student Department\", c.deptid as \"Faculty Department\""
			+ " from all_dept a left outer join common_dept c on a.deptid = c.deptid;";
		Connection conn = null;
		Statement st = null;

		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			st = conn.createStatement();

			ResultSet rs;

			System.out.println(sql);

			try {
				rs = st.executeQuery(sql);

				DataTable data_table;
				data_table = new DataTable(rs);
				JScrollPane tableScroll = new JScrollPane(data_table);

				data_table.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

				commDept.add(data_table);
				commDept.pack();
				commDept.setVisible(true);
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
