package generate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Application;


public class MySQLWriter implements Writer{

	@Override
	public void writeToDataBase(Application app, String tr) {
		 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return;
			}
		 
		
			Connection connection = null;
		 
			try {
			// wat heb je nodig: een servername bijv. localhost of ondora01.hu.nl,
			// een portnummer
			// en een database naam (schema naam)
			// wachtwoord 
			// username
				String url = "jdbc:mysql://" + app.getIpAdres() + ":" + app.getPort() + "/" + app.getName();
				connection = DriverManager
				.getConnection(url, app.getUsername() , app.getPassword());
		 
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
		 
			if (connection != null) {
				
				 Statement statement = null;
				try {
					statement = connection.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 try {
					statement.executeUpdate(tr);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				
			} 
		  }
	
		
	

}
