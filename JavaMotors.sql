CREATE DATABASE JavaMotors;

USE JavaMotors;

CREATE TABLE dealership (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE user (
	username VARCHAR(30) PRIMARY KEY,
	password VARCHAR(30) NOT NULL,
	dealership_id INT NOT NULL,
	FOREIGN KEY (dealership_id) REFERENCES dealership (id)
);

CREATE TABLE car (
	id INT PRIMARY KEY AUTO_INCREMENT,
	dealership_id INT NOT NULL,
	brand VARCHAR(30) NOT NULL,
	model VARCHAR(30) NOT NULL,
	year INT NOT NULL,
	color VARCHAR(30) NOT NULL,
	engine VARCHAR(30) NOT NULL,
	gearbox VARCHAR(30) NOT NULL,
	mileage INT NOT NULL,
	fuel VARCHAR(30) NOT NULL,
	body_type VARCHAR(30) NOT NULL,
	about VARCHAR(1000) NOT NULL,
	date_bought DATE NOT NULL,
	price_bought DECIMAL(11, 2) NOT NULL,
	sold BOOLEAN NOT NULL,
	date_sold DATE,
	price_sold DECIMAL(11, 2),
	FOREIGN KEY (dealership_id) REFERENCES dealership (id)
);

INSERT INTO dealership VALUES(0, 'CarHouse');

INSERT INTO user VALUES('CarHouse', 'admin', 1);

INSERT INTO car VALUES(0, 1, 'Ford', 'Focus', 2015, 'White', '1.6 i4', 'Automatic', 79000, 'Gasoline', 'Hatchback', '', '2023-12-14', 55900, false, null, null);
INSERT INTO car VALUES(0, 1, 'Fiat', 'Toro', 2022, 'Red', '2.0 i4 Turbo', 'Automatic', 78889, 'Diesel', 'Pickup', '', '2023-12-14', 167900, false, null, null);
INSERT INTO car VALUES(0, 1, 'Nissan', 'Sentra', 2008, 'Gray', '2.0 i4', 'Automatic', 163000, 'Gasoline', 'Sedan', '', '2023-12-14', 35900, false, null, null);
INSERT INTO car VALUES(0, 1, 'Mercedes-Benz', 'C 180', 2016, 'White', '1.6 i4', 'Automatic', 41992, 'Gasoline', 'Sedan', '', '2023-12-14', 149000, false, null, null);
INSERT INTO car VALUES(0, 1, 'Volkswagen', 'Gol', 2014, 'Yellow', '1.6 i4', 'Manual', 62000, 'Gasoline', 'Hatchback', '', '2023-12-14', 38900, false, null, null);
INSERT INTO car VALUES(0, 1, 'Ford', 'Fusion', 2017, 'Black', '2.0 i4', 'Automatic', 97880, 'Gasoline', 'Sedan', '', '2023-12-14', 96900, false, null, null);
INSERT INTO car VALUES(0, 1, 'Jeep', 'Compass', 2021, 'Black', '2.0 i4', 'Automatic', 55098, 'Diesel', 'SUV', '', '2023-12-14', 127990, false, null, null);
INSERT INTO car VALUES(0, 1, 'Toyota', 'Corolla', 2023, 'Gray', '2.0 i4', 'Automatic', 41755, 'Gasoline', 'Sedan', '', '2023-12-14', 126990, false, null, null);