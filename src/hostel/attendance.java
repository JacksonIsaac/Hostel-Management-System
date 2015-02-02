package hostel;

import static hostel.HMS.DB_URL;
import static hostel.HMS.JDBC_DRIVER;
import static hostel.HMS.PASSWORD;
import static hostel.HMS.USERNAME;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/**
 *
 * @author JacksonIsaac
 */
public class attendance extends JFrame implements ActionListener
{

	JButton pres, abs;
	JButton attlog, sh;
	JFrame atten, shortage;
	JTextField sid, wid;

	public attendance()
	{
		try {
			atten = new JFrame("Welcome to Attendence Log");
			BufferedImage img = ImageIO.read(new File("/Users/JacksonIsaac/paper.jpg"));
			atten.setContentPane(new JLabel(new ImageIcon(img)));

			atten.setLayout(new FlowLayout());

			JPanel ward = new JPanel();
			JPanel stud = new JPanel();
			JPanel attn_log = new JPanel();

			JLabel w_id = new JLabel("Warder ID");
			JLabel s_id = new JLabel("Student ID");

			wid = new JTextField(15);
			sid = new JTextField(15);

			w_id.setOpaque(false);
			s_id.setOpaque(false);
			wid.setOpaque(false);
			sid.setOpaque(false);

			ward.setOpaque(false);
			stud.setOpaque(false);
			attn_log.setOpaque(false);

			ward.add(w_id);
			ward.add(wid);
			stud.add(s_id);
			stud.add(sid);

			pres = new JButton("Present");
			abs = new JButton("Absent");
			attlog = new JButton("Attendence Records");
			sh = new JButton("Attendance Shortage");

			attn_log.add(attlog);

			pres.addActionListener(this);
			abs.addActionListener(this);
			attlog.addActionListener(this);
			sh.addActionListener(this);

			atten.add(ward);
			atten.add(stud);
			atten.add(pres);
			atten.add(abs);
			atten.add(attn_log);
			atten.add(sh);

			atten.pack();
			atten.setVisible(true);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String sql;
		String w = wid.getText();
		String s = sid.getText();
		if (e.getSource() == pres) {
			sql = "Select att_present('" + w + "', '" + s + "')";
			System.out.println("Executing -> " + sql);
			execQuery eq = new execQuery(sql);
		}
		if (e.getSource() == abs) {
			sql = "Select att_absent('" + w + "', '" + s + "')";
			System.out.println("Executing -> " + sql);
			execQuery eq = new execQuery(sql);
		}
		if (e.getSource() == attlog) {
			attendenceLog al = new attendenceLog();
		}
		if (e.getSource() == sh) {
			shortage = new JFrame("Students with Attendence Shortage");
			sql = "Select distinct(a.sid), sname, att_perc from att_percent a, student s "
				+ "where a.att_perc < 70 and s.sid = a.sid";
			shortageStud(sql);
		}
	}

	public void shortageStud(String sql)
	{
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

				shortage.add(data_table);
				shortage.pack();
				shortage.setVisible(true);
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
