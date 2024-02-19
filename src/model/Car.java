package model;

public class Car {

	private int id;
	private int dealershipId;
	private String brand;
	private String model;
	private int year;
	private String color;
	private String engine;
	private String gearbox;
	private int mileage;
	private String fuel;
	private String bodyType;
	private String about;
	private String dateBought;
	private double priceBought;
	private boolean sold;
	private String dateSold;
	private double priceSold;

	public Car(int id, int dealershipId, String brand, String model, int year, String color, String engine, String gearbox, int mileage, String fuel, String bodyType, String about, String dateBought, double priceBought, boolean sold, String dateSold, double priceSold) {
		this.id = id;
		this.dealershipId = dealershipId;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.color = color;
		this.engine = engine;
		this.gearbox = gearbox;
		this.mileage = mileage;
		this.fuel = fuel;
		this.bodyType = bodyType;
		this.about = about;
		this.dateBought = dateBought;
		this.priceBought = priceBought;
		this.sold = sold;
		this.dateSold = dateSold;
		this.priceSold = priceSold;
	}

	public int getId() {
		return id;
	}

	public int getDealershipId() {
		return dealershipId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getGearbox() {
		return gearbox;
	}

	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getDateBought() {
		return dateBought;
	}

	public void setDateBought(String dateBought) {
		this.dateBought = dateBought;
	}

	public double getPriceBought() {
		return priceBought;
	}

	public void setPriceBought(double priceBought) {
		this.priceBought = priceBought;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public String getDateSold() {
		return dateSold;
	}

	public void setDateSold(String dateSold) {
		this.dateSold = dateSold;
	}

	public double getPriceSold() {
		return priceSold;
	}

	public void setPriceSold(double priceSold) {
		this.priceSold = priceSold;
	}
}
