package Login;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jackson Isaac u4cse12021
 */
public class Login {
    
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "jackson";
    
    public static void main(String[] args){
        // TODO code application logic here
        
        Connection conn = null;
        Statement stmt = null;
        
        Scanner in = new Scanner(System.in);
        
        String sql;
        
        try
        {
            Class.forName(JDBC_DRIVER);            
            
            System.out.println("Connecting to Database...");
            
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            
            sql = "CREATE TABLE if not exists LOGIN(username varchar(25), password varchar(30), primary key(username, password) );";
            
            try {
                stmt.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sql = "INSERT INTO LOGIN VALUES('Jackson', '12021')";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                                e.printStackTrace();
            }
            sql = "INSERT INTO LOGIN VALUES('Phil', '12020')";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {                e.printStackTrace();

            }     

           sql = "Select username, password from LOGIN;";
            
            try {
                ResultSet rs = stmt.executeQuery(sql);
                
                while(rs.next())
                {
                    String user  = rs.getString("username");
                    String pass = rs.getString("password");

                    //Display values
                    System.out.print("ID: " + user);
                    System.out.print(", Pass: " + pass);
                    System.out.print("\n");

                }
                rs.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            
            stmt.close();
            conn.close();
            
        } catch(SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(stmt != null)
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                if(conn != null)
                conn.close();
            } catch (SQLException e) {
            }
        }
        
        System.out.println("GoodBye");
        
    }
}
