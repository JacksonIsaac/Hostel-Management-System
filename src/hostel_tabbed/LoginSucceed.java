package hostel_tabbed;

import static hostel_tabbed.HMS.DB_URL;
import static hostel_tabbed.HMS.JDBC_DRIVER;
import static hostel_tabbed.HMS.PASSWORD;
import static hostel_tabbed.HMS.USERNAME;
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
	JFrame hms, tabFrame;
	JTabbedPane tab;
	private ImageIcon closeImage = new ImageIcon("/Users/JacksonIsaac/close-icon.png");

	@SuppressWarnings("LeakingThisInConstructor")
	public LoginSucceed()
	{
		try {
			hms = new JFrame("Welcome to Hostel Management System.");
			BufferedImage img = ImageIO.read(new File("/Users/JacksonIsaac/attn.jpg"));
			hms.setContentPane(new JLabel(new ImageIcon(img)));

			tabFrame = new JFrame("Tabbed View");

			hms.setLayout(new FlowLayout());
			tabFrame.setLayout(new BorderLayout());

			tab = new JTabbedPane();
			tab.setOpaque(false);
			tabFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			tabFrame.add(tab, BorderLayout.CENTER);
			BufferedImage tabImage = ImageIO.read(new File("/Users/JacksonIsaac/paperLog.jpg"));
			tabFrame.setSize(800, 600);
			//tabFrame.setContentPane(new JLabel(new ImageIcon(tabImage)));

			//hms.setVisible(true);
			//tabFrame.setVisible(true);
//		JLabel sel = new JLabel("Select");
//		JLabel up = new JLabel("Update");
//		JLabel del = new JLabel("Delete");
			addstud = new JButton("Add Student");
			attn = new JButton("Attendence Log");
			allDept = new JButton("Count of Student and Faculty");
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
			JPanel addStu = ad.getaddStud();
			
//			JButton tabCloseButton = new JButton(closeImage);
//			tabCloseButton.setPreferredSize(new Dimension(1, 1));
//
//			addStu.add(tabCloseButton);
//			tabCloseButton.addActionListener(new ActionListener()
//			{
//
//				public void actionPerformed(ActionEvent e)
//				{
//					int closeTabNumber = tab.indexOfComponent(addStu);
//					tab.removeTabAt(closeTabNumber);
//				}
//			});
			
			tab.addTab("Add Student", addStu);
			tabFrame.pack();
			tabFrame.setVisible(true);
		}
		if (e.getSource() == attn) {
			attendance att = new attendance();
		}
		if (e.getSource() == allDept) {
			JPanel allDep = showCommDept();
			tab.addTab("Count of Student and Faculty", allDep);
			
//			JButton tabCloseButton = new JButton(closeImage);
//			tabCloseButton.setPreferredSize(new Dimension(1, 1));
//
//			allDep.add(tabCloseButton);
//			tabCloseButton.addActionListener(new ActionListener()
//			{
//
//				public void actionPerformed(ActionEvent e)
//				{
//					int closeTabNumber = tab.indexOfComponent(allDep);
//					tab.removeTabAt(closeTabNumber);
//				}
//			});
			
			tabFrame.pack();
			tabFrame.setVisible(true);
		}
	}

	public JPanel showCommDept()
	{
		JPanel commDept = new JPanel();
//		String sql = "with all_dept as ((Select deptid from student) UNION (Select deptid from faculty)),"
//			+ "	common_dept as ((Select deptid from student) INTERSECT (Select deptid from faculty))"
//			+ "Select a.deptid as \"Student Department\", c.deptid as \"Faculty Department\""
//			+ " from all_dept a left outer join common_dept c on a.deptid = c.deptid;";
		String sql = "with stud_count as (Select hid, \n"
			+ "	deptid, count(sid) as \"studcount\"\n"
			+ "	from student group by deptid, hid),\n"
			+ "	faculty_count as (Select hid, \n"
			+ "	deptid as \"Department Name\", count(fid) as \"fac_count\"\n"
			+ "	from faculty group by deptid, hid)\n"
			+ "Select s.hid as \"Hostel\", s.deptid as \"Department Name\", s.studcount as \"Number of Students\", \n"
			+ "	f.fac_count as \"Number of Faculty\" from stud_count s left outer join faculty_count f on s.hid = f.hid"
			+ " order by s.hid;";
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
				return commDept;
				//commDept.pack();
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
		
		return commDept;
	}
}
