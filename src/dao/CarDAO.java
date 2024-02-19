package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Car;

public class CarDAO {

	public static int create(Car car) {
		String sql = "INSERT INTO car VALUES(0, " + car.getDealershipId() + ", '" + car.getBrand() + "', '" + car.getModel() + "', " + car.getYear() + ", '" + car.getColor() + "', '" + car.getEngine() + "', '" + car.getGearbox() + "', '" + car.getMileage() + "', '" + car.getFuel() + "', '" + car.getBodyType() + "', '" + car.getAbout() + "', '" + car.getDateBought() + "', " + car.getPriceBought() + ", ";
		if(car.isSold()) {
			sql += "true, '" + car.getDateSold() + "', " + car.getPriceSold() + ");";
		}
		else {
			sql += "false, null, null);";
		}
		return ConnectionFactory.executeReturningKeys(sql);
	}

	public static ArrayList<Car> read(int dealershipId) {
		ArrayList<Car> list = new ArrayList<>();
		String sql = "SELECT * FROM car WHERE dealership_id = " + dealershipId + ";";
		ResultSet rs = ConnectionFactory.read(sql);
		if(rs != null) {
			try {
				while(rs.next()) {
					Car car = new Car(rs.getInt("id"), rs.getInt("dealership_id"), rs.getString("brand"), rs.getString("model"), rs.getInt("year"), rs.getString("color"), rs.getString("engine"), rs.getString("gearbox"), rs.getInt("mileage"), rs.getString("fuel"), rs.getString("body_type"), rs.getString("about"), rs.getString("date_bought"), rs.getDouble("price_bought"), rs.getBoolean("sold"), rs.getString("date_sold"), rs.getDouble("price_sold"));
					list.add(car);
				}
				return list;
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		return null;
	}

	public static boolean update(Car car) {
		String sql = "UPDATE car SET brand = '" + car.getBrand() + "', model = '" + car.getModel() + "', year = " + car.getYear() + ", color = '" + car.getColor() + "', engine = '" + car.getEngine() + "', gearbox = '" + car.getGearbox() + "', mileage = '" + car.getMileage() + "', fuel = '" + car.getFuel() + "', body_type = '" + car.getBodyType() + "', about = '" + car.getAbout() + "', date_bought = '" + car.getDateBought() + "', price_bought = " + car.getPriceBought();
		if(car.isSold()) {
			sql += ", sold = true, date_sold = '" + car.getDateSold() + "', price_sold = " + car.getPriceSold() + " WHERE id = " + car.getId() + ";";
		}
		else {
			sql += ", sold = false, date_sold = null, price_sold = null WHERE id = " + car.getId() + ";";
		}
		return ConnectionFactory.execute(sql);
	}

	public static void delete(int id) {
		String sql = "DELETE FROM car WHERE id = " + id + ";";
		ConnectionFactory.execute(sql);
	}
}
