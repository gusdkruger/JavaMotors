package dao;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;

public class DealershipDAO {

	public static String read(int dealershipId) {
		String sql = "SELECT name FROM dealership WHERE id = " + dealershipId + ";";
		ResultSet rs = ConnectionFactory.read(sql);
		if(rs != null) {
			try {
				if(rs.next()) {
					return rs.getString("name");
				}
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		return "JavaMotors";
	}
}
