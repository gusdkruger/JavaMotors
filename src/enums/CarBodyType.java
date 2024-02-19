package enums;

public enum CarBodyType {

	COUPE("Coupe"),
	CONVERTIBLE("Convertible"),
	CROSSOVER("Crossover"),
	HATCHBACK("Hatchback"),
	MINIVAN("Minivan"),
	PICKUP("Pickup"),
	ROADSTER("Roadster"),
	SEDAN("Sedan"),
	SPORT("Sport"),
	STATION_WAGON("Station Wagon"),
	SUV("SUV"),
	SUPER("Super"),
	VAN("Van");

	private final String displayName;

	CarBodyType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
