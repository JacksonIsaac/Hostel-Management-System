package hostel_tabbed;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Jackson Isaac
 */
public class HMS extends JFrame implements ActionListener{

    JButton okay, view, reg;    
    JTextField uname;//, pass;
	JPasswordField pass;
    JFrame f;
	Font font;
	
//	JTable tab;

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "jackson";

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new HMS().setVisible(true);
			}
		});
	}
	
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public HMS () {
		try {
			font = new Font("Times New Roman", Font.BOLD , 24);
			
			BufferedImage img = ImageIO.read(new File("/Users/JacksonIsaac/coll.jpg"));
						
			setTitle("Hostel Management System");
			setContentPane(new JLabel(new ImageIcon(img)));
			setLayout(new FlowLayout(10));
			setFont(font);
			
			JPanel un = new JPanel();
			JPanel pw = new JPanel();
			JPanel act = new JPanel();
			
			JLabel u = new JLabel("Username");
			u.setOpaque(false);
			uname = new JTextField(25);
			JLabel p = new JLabel("Password");
			p.setOpaque(false);
			pass = new JPasswordField(25);
			
			okay = new JButton("Login");
			okay.addActionListener(this);
			view = new JButton("View Students");
			view.addActionListener(this);
			reg = new JButton("Register");
			reg.addActionListener(this);
			
			JLabel cr = new JLabel("© Jackson Isaac");
			cr.setOpaque(false);
			
			un.setFont(font);
			pw.setFont(font);
			act.setFont(font);
						
			un.add(u);
			un.add(uname);
			pw.add(p);
			pw.add(pass);
			act.add(okay);
			act.add(view);
			act.add(reg);
			
			un.setOpaque(false);
			un.setBackground(new Color(0, 0, 0, 0));
			pw.setOpaque(true);
			pw.setOpaque(false);
			act.setOpaque(true);
			act.setOpaque(false);
						
			add(un);
			add(pw);
			add(act, FlowLayout.RIGHT);
			
//			cr.setHorizontalAlignment(JLabel.RIGHT);
			//cr.setLocation(600, 450);
			//cr.setBounds(620, 440, 50, 30);
			//add(cr, FlowLayout.RIGHT);
			
			this.pack();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = uname.getText();
		String cred;
		cred = new String(pass.getPassword());
        
		if(e.getSource() == reg)
		{
			Register r = new Register();
		}
		
        if(e.getSource() == okay)
        {
            boolean a = loginCheck(name, cred);
//            f = new JFrame("Login Status"); 
//			f.setLayout(new FlowLayout());
//			//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            f.addWindowListener(new WindowAdapter() {
//                public void windowClosing(WindowEvent e) {
//                    f.setVisible(false);
//                }
//            });
			
            if( a== true )
            {
				LoginSucceed ls = new LoginSucceed();
            }
            else
            {
                //showStatus("Username/Password do not match. Please Try Again.");
//                Label u = new Label("Username/Password do not match. Please Try Again.");
//                f.add(u);
//				f.pack();
//				f.setVisible(true);
				JOptionPane.showMessageDialog(this, "Incorrect Username/Password.\nPlease try again.");

            }
        }
        
        if(e.getSource() == view)
        {
            f = new JFrame("Student List");
//            f.setSize(400, 500);
			//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
            f.setLayout(new FlowLayout());
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    f.setVisible(false);
                }
            });
            viewAll();
        }
		
    }
	
	
	/* Function to view the ResultSet in Table form. */
    public void viewAll()
    {
        Connection conn = null;
        Statement st = null;
                
        try
        {
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            st = conn.createStatement();
            
            String sql, us;
//			DefaultTableModel model = new DefaultTableModel();
//			tab = new JTable();
            sql = "Select sid, sname, deptid, bid, year, sem from Student";
            	
            System.out.println(sql);
            
            try {
                ResultSet rs = st.executeQuery(sql);
				
				DataTable data_table;
				data_table = new DataTable(rs);
				JScrollPane tableScroll = new JScrollPane(data_table);
				
				data_table.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				
				f.add(data_table);
				f.pack();
                
            } catch (SQLException e)
            {
                System.out.println("Error "+e);
            }
            
            conn.close();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(st != null)
                st.close();
            } catch (SQLException e) {
            }
            try {
                if(conn != null)
                conn.close();
            } catch (SQLException e) {
            }
        }
        
    }
    
    public boolean loginCheck(String n, String c)
    {
        Connection conn = null;
        Statement st = null;
        
        int flag = 0;        
        
        try
        {
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            st = conn.createStatement();
            
            String sql;
            
            sql = "Select username from LOGIN where username = " + "'" + n + "'" + " and password = " + "'" + c + "'" +  ";";
            
            System.out.println(sql);
            
            try {
                
                ResultSet rs = st.executeQuery(sql);
//                System.out.println(rs);
                if( rs.next())
                    flag = 1;
                else
                    flag = 0;
                
            } catch (SQLException e)
            {
//                System.out.println("Error "+e);
            }
            
            conn.close();
            st.close();
            
//            System.out.println(flag);
            
            if(flag == 1) return true;
            else return false;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(st != null)
                st.close();
            } catch (SQLException e) {
            }
            try {
                if(conn != null)
                conn.close();
            } catch (SQLException e) {
            }
            
            if(flag == 1) return true;
            else return false;
        }
        
    }
}