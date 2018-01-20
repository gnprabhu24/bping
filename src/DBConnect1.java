import java.sql.*;
import java.net.*;

import javax.swing.JOptionPane;

public class DBConnect1
{
	Connection c ;
	Statement s ;
	
	DBConnect1()
	{
		try
		{
		
		Class.forName("com.mysql.jdbc.Driver");
		c= DriverManager.getConnection("jdbc:mysql://localhost:3306/tfft","root","");
		
		
		s=c.createStatement();
		
		System.out.println("Connected");
		}

		catch(Exception e)
		{
		int em3=JOptionPane.ERROR_MESSAGE;
		JOptionPane.showMessageDialog(null, "SQL connection has not been established.", "ERROR!!!", em3 );
		e.printStackTrace();
		}
		
	}

	public static void main(String args[])
	{
	new DBConnect1();
	}
}
