package dao;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;

public class UserDAO {

	public static int authenticateLogin(String username, String password) {
		String sql = "SELECT dealership_id FROM user WHERE username = '" + username + "' AND password = '" + password + "';";
		ResultSet rs = ConnectionFactory.read(sql);
		if(rs != null) {
			try {
				if(rs.next()) {
					return rs.getInt("dealership_id");
				}
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		return -1;	
	}
}
