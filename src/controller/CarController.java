package controller;

import java.util.ArrayList;

import dao.CarDAO;
import model.Car;

public class CarController {

	public static int create(int dealershipId, String brand, String model, String year, String color, String engine, String gearbox, String mileage, String fuel, String bodyType, String about, String dateBought, String priceBought, boolean sold, String dateSold, String priceSold) {
		validateData(model, year, color, engine, mileage, about, priceBought, sold, priceSold);
		Car car = new Car(0, dealershipId, brand, model, Integer.parseInt(year), color, engine, gearbox, Integer.parseInt(mileage), fuel, bodyType, about, dateBought, Double.parseDouble(priceBought), sold, dateSold, Double.parseDouble(priceSold));
		return CarDAO.create(car);
	}

	public static ArrayList<Car> read(int dealershipId) {
		return CarDAO.read(dealershipId);
	}

	public static boolean update(int id, int dealershipId, String brand, String model, String year, String color, String engine, String gearbox, String mileage, String fuel, String bodyType, String about, String dateBought, String priceBought, boolean sold, String dateSold, String priceSold) {
		validateData(model, year, color, engine, mileage, about, priceBought, sold, priceSold);
		Car car = new Car(id, dealershipId, brand, model, Integer.parseInt(year), color, engine, gearbox, Integer.parseInt(mileage), fuel, bodyType, about, dateBought, Double.parseDouble(priceBought), sold, dateSold, Double.parseDouble(priceSold));
		return CarDAO.update(car);
	}

	public static void delete(int id) {
		CarDAO.delete(id);
	}

	public static void validateData(String model, String year, String color, String engine, String mileage, String about, String priceBought, boolean sold, String priceSold) {
		if(model.isBlank()) {
			throw new RuntimeException("Model can't be empty");
		}
		if(year.isBlank()) {
			throw new RuntimeException("Year can't be empty");
		}
		if(Integer.parseInt(year) < 1886) {
			throw new RuntimeException("Year must be greater than or equal to 1886");
		}
		if(color.isBlank()) {
			throw new RuntimeException("Color can't be empty");
		}
		if(engine.isBlank()) {
			throw new RuntimeException("Engine can't be empty");
		}
		if(mileage.isBlank()) {
			throw new RuntimeException("Mileage can't be empty");
		}
		if(about.isBlank()) {
			throw new RuntimeException("About can't be empty");
		}
		if(priceBought.isBlank()) {
			throw new RuntimeException("Price Bought can't be empty");
		}
		else if(priceBought.equals(".")) {
			throw new RuntimeException("Price Bought must be a number");
		}
		if(Double.parseDouble(priceBought) > 999999999.99) {
			throw new RuntimeException("Price Bought must be equal to or lower than 999999999.99");
		}
		if(sold) {
			if(priceSold.isBlank()) {
				throw new RuntimeException("Price Sold can't be empty");
			}
			else if(priceSold.equals(".")) {
				throw new RuntimeException("Price Bought must be a number");
			}
			if(Double.parseDouble(priceSold) > 999999999.99) {
				throw new RuntimeException("Price Sold must be equal to or lower than 999999999.99");
			}
		}
	}
}
