package tri.engineering.sarl.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnexionMysql {
	static Connection connection ;
	public static Connection Connectedb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/triengineering?useSSL=false","root","");
			return connection;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
			//e.printStackTrace();
		}
		
	}
}
