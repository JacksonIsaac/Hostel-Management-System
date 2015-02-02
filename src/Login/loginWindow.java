/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.applet.Applet;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author u4cse12021
 */
public class loginWindow extends Applet implements ActionListener{

    Button okay, view;    
    TextField uname, pass;
    
    Frame f;
    
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://saktimayi:5432/examuser21";
    static final String USERNAME = "examuser21";
    static final String PASSWORD = "examuser21";
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        
        GridLayout g = new GridLayout(3, 2);
        setLayout(g);
        Label u = new Label("Username");
        uname = new TextField(25);
        
        Label p = new Label("Password");
        pass = new TextField(25);
        
        okay = new Button("Login");       
        okay.addActionListener(this);
        
        view = new Button("View Students");
        view.addActionListener(this);
        
        add(u);
        add(uname);
        add(p);
        add(pass);
        add(okay);
        add(view);
    }
    // TODO overwrite start(), stop() and destroy() methods

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = uname.getText();
        String cred = pass.getText();
        
        if(e.getSource() == okay)
        {
            boolean a = loginCheck(name, cred);
            f = new Frame("Login Status");
            f.setVisible(true);
            f.setSize(200, 200);
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    f.setVisible(false);
                }
            });
            if( a== true )
            {
                showStatus("Login Successful");
                Label s = new Label("Login Successful");
                f.add(s);
            }
            else
            {
                showStatus("Username/Password do not match. Please Try Again.");
                Label u = new Label("Username/Password do not match. Please Try Again.");
                f.add(u);
            }
        }
        
        if(e.getSource() == view)
        {
            f = new Frame("Student List");
            f.setVisible(true);
            f.setSize(400, 500);
            f.setLayout(new FlowLayout());
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    f.setVisible(false);
                }
            });
            viewAll();
        }
    }
    
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
            
            sql = "Select username from LOGIN";
            
            System.out.println(sql);
            
            try {
                
                List l = new List();

                ResultSet rs = st.executeQuery(sql);
                while( rs.next())
                {
                    us = rs.getString("username");
                    
                    l.add(us);
                }
                
                f.add(l);
                
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
                System.out.println(rs);
                if( rs.next())
                    flag = 1;
                else
                    flag = 0;
                
            } catch (SQLException e)
            {
                System.out.println("Error "+e);
            }
            
            conn.close();
            st.close();
            
            System.out.println(flag);
            
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
