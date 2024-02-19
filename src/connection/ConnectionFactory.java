package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionFactory {

	private static final String url = "jdbc:mysql://localhost:3306/javamotors";
	private static final String user = "root";
	private static final String password = "";

	public static boolean execute(String sql) {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			st.execute(sql);
			con.close();
			return true;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
			return false;
		}
	}

	public static int executeReturningKeys(String query) {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();
			ResultSet keys = pst.getGeneratedKeys();
			int id;
			if(keys.next()) {
				id = keys.getInt(1);
			}
			else {
				id = -1;
			}
			con.close();
			return id;
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
			return -1;
		}
	}

	public static ResultSet read(String sql) {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
			return null;
		}
	}
}
