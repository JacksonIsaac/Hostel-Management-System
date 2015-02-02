package hostel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author JacksonIsaac
 */
public class addStudent extends JFrame implements ActionListener
{

	JButton insert;
	JTextField id, n, s, c, h, rno, did, bid, year, sem;
	JFrame addStud;

	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;

	public addStudent()
	{
		
		addStud = new JFrame("Hostel Log");

		model = new UtilDateModel();
		model.setDate(1994, 11, 6);
		model.setSelected(true);
		datePanel = new JDatePanelImpl(model, new Properties());
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		GridLayout g = new GridLayout(12, 2);
		addStud.setLayout(g);

		JLabel stud_id = new JLabel("ID");
		JLabel name = new JLabel("Name");
		JLabel dob = new JLabel("Date of Birth");
		JLabel state = new JLabel("State");
		JLabel city = new JLabel("City");
		JLabel hostel = new JLabel("Hostel");
		JLabel room_no = new JLabel("Room No");
		JLabel ldid = new JLabel("Department Id");
		JLabel lbid = new JLabel("Batch Id");
		JLabel lyear = new JLabel("Year");
		JLabel lsem = new JLabel("Sem");

		JScrollPane frame_scroll = new JScrollPane();

		id = new JTextField(15);
		n = new JTextField(15);
		s = new JTextField(15);
		c = new JTextField(15);
		h = new JTextField(15);
		rno = new JTextField(15);
		did = new JTextField(15);
		bid = new JTextField(15);
		year = new JTextField(15);
		sem = new JTextField(15);

		JPanel s_id = new JPanel();
//		s_id.setOpaque(false);
		JPanel s_name = new JPanel();
		JPanel s_dob = new JPanel();
		JPanel s_state = new JPanel();
		JPanel s_city = new JPanel();
		JPanel s_hostel = new JPanel();
		JPanel s_room_no = new JPanel();
		JPanel s_did = new JPanel();
		JPanel s_bid = new JPanel();
		JPanel s_year = new JPanel();
		JPanel s_sem = new JPanel();

		s_id.add(stud_id);
		s_id.add(id);
		s_name.add(name);
		s_name.add(n);
		s_dob.add(dob);
		s_dob.add(datePicker);
		s_state.add(state);
		s_state.add(s);
		s_city.add(city);
		s_city.add(c);
		s_hostel.add(hostel);
		s_hostel.add(h);
		s_room_no.add(room_no);
		s_room_no.add(rno);
		s_did.add(ldid);
		s_did.add(did);
		s_bid.add(lbid);
		s_bid.add(bid);
		s_year.add(lyear);
		s_year.add(year);
		s_sem.add(lsem);
		s_sem.add(sem);

		addStud.add(s_id);
		addStud.add(s_name);
		addStud.add(s_dob);
		addStud.add(s_state);
		addStud.add(s_city);
		addStud.add(s_hostel);
		addStud.add(s_room_no);
		addStud.add(s_did);
		addStud.add(s_bid);
		addStud.add(s_year);
		addStud.add(s_sem);

		insert = new JButton("Add Student");
		insert.addActionListener(this);
		addStud.add(insert);
		addStud.pack();

		addStud.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == insert) 
		{
			Date dob = (Date) datePicker.getModel().getValue();
			String _id, _name, _state, _city, _hid, _rno, _did, _bid;
			Integer _yr, _sem;
			_id = id.getText();
			_name = n.getText();
			_state = s.getText();
			_city = c.getText();
			_hid = h.getText();
			_rno = rno.getText();
			_did = did.getText();
			_bid = bid.getText();
			_yr = Integer.parseInt(year.getText());
			_sem = Integer.parseInt(sem.getText());

			String sql;
			sql = "Insert into student values('" + _id + "','" + _name + "','" + dob + "','" + _state + "','" + _city + "','" + _hid + "','" + _rno + "','" + _did + "','" + _bid + "'," + _yr + "," + _sem + "); ";

			System.out.println("Executing -> " + sql);
			
			execQuery eq = new execQuery(sql);
		}
	}


}
