package generate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Application;


public class OracleWriter implements Writer{

	@Override
	public void writeToDataBase(Application app, String tr) {

		 try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	        String hostName = app.getIpAdres();
	        int portNumber = app.getPort();
	        String serviceName = app.getSID();
	        String url = "jdbc:oracle:thin:@//"+ hostName+ ":"+portNumber+ "/" + serviceName;
	        String username = app.getUsername();
	        String password = app.getPassword();
	        
	        try {
				Connection conn = DriverManager.getConnection(url, username, password);
				 Statement statement = conn.createStatement();
				 statement.executeUpdate(tr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
