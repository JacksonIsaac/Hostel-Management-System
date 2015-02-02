package hostel_tabbed;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author JacksonIsaac
 */
public class Register extends JFrame implements ActionListener
{

	JButton insert;
	JTextField uname;
	JPasswordField pass;
	
	public Register()
	{
		setLayout(new GridLayout(3,2));

		JLabel id = new JLabel("Username");

		uname = new JTextField(15);
		pass = new JPasswordField(15);

		JPanel un = new JPanel();
		add(id);
		add(uname);
		JPanel pw = new JPanel();
		JLabel pword = new JLabel("Password");
		add(pword);
		add(pass);
		
		insert = new JButton("Register");
		insert.addActionListener(this);
		
//		this.add(un);
//		this.add(pass);
		this.add(insert);
		
		this.pack();
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == insert) 
		{
			String _username = uname.getText();
			String _password;
			_password = new String(pass.getPassword());
			String sql;
			sql = "Insert into login values('" + _username + "','" + _password + "'); ";

			System.out.println("Executing -> " + sql);
			
			try{
				execQuery eq = new execQuery(sql);
				JOptionPane.showMessageDialog(this, "Successfully Registered.");
			} catch (Exception exc)
			{
				JOptionPane.showMessageDialog(this, "Already Registered.");
			}
			
			
			
			this.dispose();
		}
	}


}
