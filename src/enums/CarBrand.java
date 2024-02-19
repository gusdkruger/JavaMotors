package enums;

public enum CarBrand {

	ALFA_ROMEO("Alfa Romeo"),
	ASTON_MARTIN("Aston Martin"),
	AUDI("Audi"),
	BENTLEY("Bentley"),
	BMW("BMW"),
	CADILLAC("Cadillac"),
	CHEVROLET("Chevrolet"),
	CHRYSLER("Chrysler"),
	CITROEN("Citroen"),
	DODGE("Dodge"),
	FERRARI("Ferrari"),
	FIAT("Fiat"),
	FORD("Ford"),
	HONDA("Honda"),
	HUMMER("Hummer"),
	HYUNDAI("Hyundai"),
	JAGUAR("Jaguar"),
	JEEP("Jeep"),
	LAMBORGHINI("Lamborghini"),
	LAND_ROVER("Land Rover"),
	LEXUS("Lexus"),
	MASERATI("Maserati"),
	MAZDA("Mazda"),
	MCLAREN("McLaren"),
	MERCEDES_BENZ("Mercedes-Benz"),
	MINI("Mini"),
	MITSUBISHI("Mitsubishi"),
	NISSAN("Nissan"),
	PEUGEOT("Peugeot"),
	PORSCHE("Porsche"),
	RENAULT("Renault"),
	ROLL_ROYCE("Rolls-Royce"),
	SHELBY("Shelby"),
	SUBARU("Subaru"),
	SUZUKI("Suzuki"),
	TESLA("Tesla"),
	TOYOTA("Toyota"),
	VOLKSWAGEN("Volkswagen"),
	VOLVO("Volvo");

	private final String displayName;

	CarBrand(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
