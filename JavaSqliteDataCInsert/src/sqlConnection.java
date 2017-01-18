import java.sql.*;
import javax.swing.*;

public class sqlConnection {
	Connection conn=null;
	public static Connection dbConnector()
	{
		try{
			
			 //only used sqlite//
			 
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:DB//SqlData.sqlite");
			JOptionPane.showMessageDialog(null, "connect successfull");
			return conn;
			//endof sqlite connect
			 
			 
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}

}
