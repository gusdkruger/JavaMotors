package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.CarController;
import enums.CarBodyType;
import enums.CarBrand;
import model.Car;
import model.Photo;
import view.consts.Const;
import view.jdialog.ConfirmJDialog;
import view.jdialog.MessageJDialog;
import view.textFieldDocument.DoubleOnly;
import view.textFieldDocument.IntegerOnly;
import view.textFieldDocument.StringOnly;

@SuppressWarnings("serial")
public class CarPageJPanel extends JPanel {

	private Car car;
	private ArrayList<Photo> photos;
	private int selectedPhoto;

	private JLabel labelCenterPhoto;
	private JButton buttonEdit;
	private JButton buttonSave;
	private JButton buttonDelete;
	private JButton buttonClose;
	private JButton buttonCancel;
	private JButton buttonLeft;
	private JLabel labelLeftPhoto;
	private JLabel labelRightPhoto;
	private JButton buttonRight;
	private JButton buttonAddPhoto;
	private JButton buttonDeletePhoto;
	private JLabel labelBodyType;
	private JComboBox<String> comboBoxBodyType;
	private JPanel panelCarData;
	private JTextField textFieldBrand;
	private JComboBox<String> comboBoxBrand;
	private JTextField textFieldModel;
	private JTextField textFieldYear;
	private JTextField textFieldColor;
	private JTextField textFieldEngine;
	private JTextField textFieldGearbox;
	private JComboBox<String> comboBoxGearbox;
	private JTextField textFieldMileage;
	private JTextField textFieldFuel;
	private JComboBox<String> comboBoxFuel;
	private JTextArea textArea;
	private JTextField textFieldDateBought;
	private JComboBox<String> comboBoxYearBought;
	private JComboBox<String> comboBoxMonthBought;
	private JComboBox<String> comboBoxDayBought;
	private JTextField textFieldPriceBought;
	private JLabel labelSold;
	private JTextField textFieldSold;
	private JCheckBox checkBoxSold;
	private JTextField textFieldPriceSold;
	private JTextField textFieldDateSold;
	private JComboBox<String> comboBoxYearSold;
	private JComboBox<String> comboBoxMonthSold;
	private JComboBox<String> comboBoxDaySold;

	private final Font fontArrows = new Font("Arial", Font.BOLD, 25);
	private final Font smallFont = new Font("Arial", Font.PLAIN, 15);
	private final Font font = new Font("Arial", Font.PLAIN, 20);
	private final Font fontBold = new Font("Arial", Font.BOLD, 20);

	public CarPageJPanel(MainJFrame mainJFrame) {
		this.car = null;
		this.photos = new ArrayList<>();
		setSize(1297, 747);
		setLayout(null);

		labelCenterPhoto = new JLabel();
		labelCenterPhoto.setBounds(348, 5, 600, 450);
		labelCenterPhoto.setBorder(Const.BLACK_BORDER);
		add(labelCenterPhoto);

		JPanel panelTopLine = new JPanel();
		panelTopLine.setBounds(0, 0, 1297, 60);
		panelTopLine.setBackground(Const.RED);
		panelTopLine.setLayout(null);
		add(panelTopLine);

		buttonEdit = new JButton("Edit");
		buttonEdit.setFont(fontBold);
		buttonEdit.setBounds(10, 10, 70, 40);
		buttonEdit.setBorder(Const.WHITE_BORDER);
		buttonEdit.setBackground(Const.RED);
		buttonEdit.setForeground(Color.WHITE);
		buttonEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonEdit.setFocusable(false);
		buttonEdit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				setEditMode(true);
			}  
		});
		panelTopLine.add(buttonEdit);

		buttonSave = new JButton("Save");
		buttonSave.setFont(fontBold);
		buttonSave.setBounds(10, 10, 70, 40);
		buttonSave.setBorder(Const.WHITE_BORDER);
		buttonSave.setBackground(Const.RED);
		buttonSave.setForeground(Color.WHITE);
		buttonSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonSave.setFocusable(false);
		buttonSave.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				String brand = (String) comboBoxBrand.getSelectedItem();
				String model = textFieldModel.getText();
				String year = textFieldYear.getText();
				String color = textFieldColor.getText();
				String engine = textFieldEngine.getText();
				String gearbox = (String) comboBoxGearbox.getSelectedItem();
				String mileage =  textFieldMileage.getText();
				String fuel = (String) comboBoxFuel.getSelectedItem();
				String bodyType = (String) comboBoxBodyType.getSelectedItem();
				String about = textArea.getText();
				String dateBought = comboBoxYearBought.getSelectedItem() + "-" + comboBoxMonthBought.getSelectedItem() + "-" + comboBoxDayBought.getSelectedItem();
				String priceBought = textFieldPriceBought.getText();
				boolean sold = checkBoxSold.isSelected();
				String dateSold = "";
				String priceSold = "0.0";
				if(sold) {
					dateSold = comboBoxYearSold.getSelectedItem() + "-" + comboBoxMonthSold.getSelectedItem() + "-" + comboBoxDaySold.getSelectedItem();
					priceSold = textFieldPriceSold.getText();
				}
				try {
					boolean updated = CarController.update(car.getId(), car.getDealershipId(), brand, model, year, color, engine, gearbox, mileage, fuel, bodyType, about, dateBought, priceBought, sold, dateSold, priceSold);
					if(updated) {
						setEditMode(false);
						car.setBrand(brand);
						car.setModel(model);
						car.setYear(Integer.parseInt(year));
						car.setColor(color);
						car.setEngine(engine);
						car.setGearbox(gearbox);
						car.setMileage(Integer.parseInt(mileage));
						car.setFuel(fuel);
						car.setBodyType(bodyType);
						car.setAbout(about);
						car.setDateBought(dateBought);
						car.setPriceBought(Double.parseDouble(priceBought));
						car.setSold(sold);
						if(sold) {
							car.setDateSold(dateSold);
							car.setPriceSold(Double.parseDouble(priceSold));
						}
						else {
							car.setDateSold("");
							car.setPriceSold(0.0);
						}
						updateData();
					}
				}
				catch (Exception exception) {
					new MessageJDialog(exception.getMessage());
				}
			}  
		});
		panelTopLine.add(buttonSave);

		buttonDelete = new JButton("Delete");
		buttonDelete.setFont(fontBold);
		buttonDelete.setBounds(90, 10, 100, 40);
		buttonDelete.setBorder(Const.WHITE_BORDER);
		buttonDelete.setBackground(Const.RED);
		buttonDelete.setForeground(Color.WHITE);
		buttonDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonDelete.setFocusable(false);
		buttonDelete.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				ConfirmJDialog confirmDialog = new ConfirmJDialog("Are you sure you want to delete this car from the database?");
				if(confirmDialog.getConfirm()) {
					CarController.delete(car.getId());
					File[] files = new File("img/cars/" + car.getId()).listFiles();
					if(files != null) {
						for(File file : files) {
							file.delete();
						}
					}
					try {
						Files.delete(Paths.get("img/cars/" + car.getId()));
					}
					catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}
					mainJFrame.fillCardPanel();
					mainJFrame.closeCarPage();
				}
				confirmDialog.dispose();
			}  
		});
		panelTopLine.add(buttonDelete);

		buttonClose = new JButton("Close");
		buttonClose.setFont(fontBold);
		buttonClose.setBounds(1197, 10, 90, 40);
		buttonClose.setBorder(Const.WHITE_BORDER);
		buttonClose.setBackground(Const.RED);
		buttonClose.setForeground(Color.WHITE);
		buttonClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonClose.setFocusable(false);
		buttonClose.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				mainJFrame.fillCardPanel();
				mainJFrame.closeCarPage();
			}  
		});
		panelTopLine.add(buttonClose);

		buttonCancel = new JButton("Cancel");
		buttonCancel.setFont(fontBold);
		buttonCancel.setBounds(1197, 10, 90, 40);
		buttonCancel.setBorder(Const.WHITE_BORDER);
		buttonCancel.setBackground(Const.RED);
		buttonCancel.setForeground(Color.WHITE);
		buttonCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonCancel.setFocusable(false);
		buttonCancel.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				setEditMode(false);
				updateData();
			}  
		});
		panelTopLine.add(buttonCancel);

		buttonLeft = new JButton("<");
		buttonLeft.setFont(fontArrows);
		buttonLeft.setBounds(283, 210, 60, 40);
		buttonLeft.setBorder(Const.WHITE_BORDER);
		buttonLeft.setBackground(Const.RED);
		buttonLeft.setForeground(Color.WHITE);
		buttonLeft.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonLeft.setFocusable(false);
		buttonLeft.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				selectedPhoto--;
				loadSelectedPhoto();
			}  
		});
		buttonLeft.setVisible(false);
		add(buttonLeft);

		labelLeftPhoto = new JLabel();
		labelLeftPhoto.setBounds(5, 80, 400, 300);
		labelLeftPhoto.setBorder(Const.BLACK_BORDER);
		labelLeftPhoto.setVisible(false);
		add(labelLeftPhoto);

		buttonRight = new JButton(">");
		buttonRight.setFont(fontArrows);
		buttonRight.setBounds(953, 210, 60, 40);
		buttonRight.setBorder(Const.WHITE_BORDER);
		buttonRight.setBackground(Const.RED);
		buttonRight.setForeground(Color.WHITE);
		buttonRight.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonRight.setFocusable(false);
		buttonRight.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				selectedPhoto++;
				loadSelectedPhoto();
			}  
		});
		buttonRight.setVisible(false);
		add(buttonRight);

		labelRightPhoto = new JLabel();
		labelRightPhoto.setBounds(892, 80, 400, 300);
		labelRightPhoto.setBorder(Const.BLACK_BORDER);
		labelRightPhoto.setVisible(false);
		add(labelRightPhoto);

		JPanel panelBottomLine = new JPanel();
		panelBottomLine.setBounds(0, 400, 1297, 60);
		panelBottomLine.setBackground(Const.RED);
		panelBottomLine.setLayout(null);
		add(panelBottomLine);

		buttonAddPhoto = new JButton("Add Photo");
		buttonAddPhoto.setFont(fontBold);
		buttonAddPhoto.setBounds(9, 10, 160, 40);
		buttonAddPhoto.setBorder(Const.WHITE_BORDER);
		buttonAddPhoto.setBackground(Const.RED);
		buttonAddPhoto.setForeground(Color.WHITE);
		buttonAddPhoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonAddPhoto.setFocusable(false);
		buttonAddPhoto.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				if(photos.size() < 10) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setAcceptAllFileFilterUsed(false);
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "png", "jpg", "jpeg");
					fileChooser.setFileFilter(filter);
					int response = fileChooser.showOpenDialog(null);
					if(response == JFileChooser.APPROVE_OPTION) {
						File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
						Path sourcePath = file.toPath();
						Path destinationPath = new File("img/cars/" + car.getId(), car.getId() + "_" + photos.size() + ".jpg").toPath();
						try {
							Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
						}
						catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.toString());
						}
					}
					loadPhotos();
				}
				else {
					new MessageJDialog("Maximum of 10 photos");
				}
			}  
		});
		panelBottomLine.add(buttonAddPhoto);

		buttonDeletePhoto = new JButton("Delete Photo");
		buttonDeletePhoto.setFont(fontBold);
		buttonDeletePhoto.setBounds(179, 10, 160, 40);
		buttonDeletePhoto.setBorder(Const.WHITE_BORDER);
		buttonDeletePhoto.setBackground(Const.RED);
		buttonDeletePhoto.setForeground(Color.WHITE);
		buttonDeletePhoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonDeletePhoto.setFocusable(false);
		buttonDeletePhoto.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				ConfirmJDialog confirmDialog = new ConfirmJDialog("Are you sure you want to delete this photo?");
				if(confirmDialog.getConfirm()) {
					try {
						Files.delete(Paths.get(photos.get(selectedPhoto).getImagePath()));
						for (int i = selectedPhoto + 1; i < photos.size(); i++) {
							String path = photos.get(i).getImagePath();
							File file = new File(path);
							file.renameTo(new File(path.substring(0, path.length() - 5) + (i - 1) + ".jpg"));

						}
						loadPhotos();
					}
					catch (IOException exception) {
						JOptionPane.showMessageDialog(null, exception.toString());
					}
				}
				confirmDialog.dispose();
			}  
		});
		panelBottomLine.add(buttonDeletePhoto);

		labelBodyType = new JLabel("Body Type:");
		labelBodyType.setFont(fontBold);
		labelBodyType.setBounds(1010, 10, 110, 40);
		labelBodyType.setForeground(Color.WHITE);
		panelBottomLine.add(labelBodyType);

		comboBoxBodyType = new JComboBox<>();
		comboBoxBodyType.setFont(font);
		comboBoxBodyType.setBounds(1127, 10, 160, 40);
		comboBoxBodyType.setBackground(Color.WHITE);
		comboBoxBodyType.setFocusable(false);
		for(CarBodyType bodyType : CarBodyType.values()) {
			comboBoxBodyType.addItem(bodyType.getDisplayName());
		}
		panelBottomLine.add(comboBoxBodyType);

		JScrollPane scrollPaneCarData = new JScrollPane();
		scrollPaneCarData.setBounds(0, 460, 1298, 288);
		scrollPaneCarData.getVerticalScrollBar().setUnitIncrement(8);
		add(scrollPaneCarData);

		panelCarData = new JPanel();
		panelCarData.setPreferredSize(new Dimension(0, 479));
		panelCarData.setLayout(null);
		scrollPaneCarData.setViewportView(panelCarData);

		JLabel labelBrand = new JLabel("Brand");
		labelBrand.setFont(smallFont);
		labelBrand.setBounds(178, 18, 220, 15);
		panelCarData.add(labelBrand);

		textFieldBrand = new JTextField();
		textFieldBrand.setFont(font);
		textFieldBrand.setHorizontalAlignment(JTextField.CENTER);
		textFieldBrand.setBounds(178, 35, 220, 40);
		textFieldBrand.setBorder(Const.GRAY_BORDER);
		textFieldBrand.setEditable(false);
		panelCarData.add(textFieldBrand);

		comboBoxBrand = new JComboBox<>();
		comboBoxBrand.setFont(font);
		comboBoxBrand.setBounds(178, 35, 220, 40);
		comboBoxBrand.setBackground(Color.WHITE);
		comboBoxBrand.setFocusable(false);
		for(CarBrand carBrand : CarBrand.values()) {
			comboBoxBrand.addItem(carBrand.getDisplayName());
		}
		panelCarData.add(comboBoxBrand);

		JLabel labelModel = new JLabel("Model");
		labelModel.setFont(smallFont);
		labelModel.setBounds(418, 18, 220, 15);
		panelCarData.add(labelModel);

		textFieldModel = new JTextField();
		textFieldModel.setFont(font);
		textFieldModel.setBounds(418, 35, 220, 40);
		textFieldModel.setBorder(Const.GRAY_BORDER);
		textFieldModel.setDocument(new StringOnly(30));
		textFieldModel.setHorizontalAlignment(JTextField.CENTER);
		panelCarData.add(textFieldModel);

		JLabel labelYear = new JLabel("Year");
		labelYear.setFont(smallFont);
		labelYear.setBounds(658, 18, 220, 15);
		panelCarData.add(labelYear);

		textFieldYear = new JTextField();
		textFieldYear.setFont(font);
		textFieldYear.setBounds(658, 35, 220, 40);
		textFieldYear.setBorder(Const.GRAY_BORDER);
		textFieldYear.setDocument(new IntegerOnly(4));
		textFieldYear.setHorizontalAlignment(JTextField.CENTER);
		panelCarData.add(textFieldYear);

		JLabel labelColor = new JLabel("Color");
		labelColor.setFont(smallFont);
		labelColor.setBounds(898, 18, 220, 15);
		panelCarData.add(labelColor);

		textFieldColor = new JTextField();
		textFieldColor.setFont(font);
		textFieldColor.setBounds(898, 35, 220, 40);
		textFieldColor.setBorder(Const.GRAY_BORDER);
		textFieldColor.setDocument(new StringOnly(30));
		textFieldColor.setHorizontalAlignment(JTextField.CENTER);
		panelCarData.add(textFieldColor);

		JLabel labelEngine = new JLabel("Engine");
		labelEngine.setFont(smallFont);
		labelEngine.setBounds(178, 88, 220, 15);
		panelCarData.add(labelEngine);

		textFieldEngine = new JTextField();
		textFieldEngine.setFont(font);
		textFieldEngine.setBounds(178, 105, 220, 40);
		textFieldEngine.setBorder(Const.GRAY_BORDER);
		textFieldEngine.setDocument(new StringOnly(30));
		textFieldEngine.setHorizontalAlignment(JTextField.CENTER);
		panelCarData.add(textFieldEngine);

		JLabel labelGearbox = new JLabel("Gearbox");
		labelGearbox.setFont(smallFont);
		labelGearbox.setBounds(418, 88, 220, 15);
		panelCarData.add(labelGearbox);

		textFieldGearbox = new JTextField();
		textFieldGearbox.setFont(font);
		textFieldGearbox.setHorizontalAlignment(JTextField.CENTER);
		textFieldGearbox.setBounds(418, 105, 220, 40);
		textFieldGearbox.setBorder(Const.GRAY_BORDER);
		textFieldGearbox.setEditable(false);
		panelCarData.add(textFieldGearbox);

		comboBoxGearbox = new JComboBox<>();
		comboBoxGearbox.setFont(font);
		comboBoxGearbox.setBounds(418, 105, 220, 40);
		comboBoxGearbox.setBackground(Color.WHITE);
		comboBoxGearbox.setFocusable(false);
		comboBoxGearbox.addItem("Automatic");
		comboBoxGearbox.addItem("Manual");
		panelCarData.add(comboBoxGearbox);

		JLabel labelMileage = new JLabel("Mileage");
		labelMileage.setFont(smallFont);
		labelMileage.setBounds(658, 88, 200, 15);
		panelCarData.add(labelMileage);

		textFieldMileage = new JTextField();
		textFieldMileage.setFont(font);
		textFieldMileage.setBounds(658, 105, 220, 40);
		textFieldMileage.setBorder(Const.GRAY_BORDER);
		textFieldMileage.setDocument(new IntegerOnly(7));
		textFieldMileage.setHorizontalAlignment(JTextField.CENTER);
		panelCarData.add(textFieldMileage);

		JLabel labelFuel = new JLabel("Fuel");
		labelFuel.setFont(smallFont);
		labelFuel.setBounds(898, 88, 220, 15);
		panelCarData.add(labelFuel);

		textFieldFuel = new JTextField();
		textFieldFuel.setFont(font);
		textFieldFuel.setHorizontalAlignment(JTextField.CENTER);
		textFieldFuel.setBounds(898, 105, 220, 40);
		textFieldFuel.setBorder(Const.GRAY_BORDER);
		textFieldFuel.setEditable(false);
		panelCarData.add(textFieldFuel);

		comboBoxFuel = new JComboBox<>();
		comboBoxFuel.setFont(font);
		comboBoxFuel.setBounds(898, 105, 220, 40);
		comboBoxFuel.setBackground(Color.WHITE);
		comboBoxFuel.setFocusable(false);
		comboBoxFuel.addItem("Diesel");
		comboBoxFuel.addItem("Eletric");
		comboBoxFuel.addItem("Gasoline");
		comboBoxFuel.addItem("Hybrid");
		panelCarData.add(comboBoxFuel);

		JLabel labelAbout = new JLabel("About");
		labelAbout.setFont(smallFont);
		labelAbout.setBounds(178, 158, 200, 15);
		panelCarData.add(labelAbout);

		JScrollPane scrollPaneTextArea = new JScrollPane();
		scrollPaneTextArea.setBounds(178, 175, 941, 200);
		scrollPaneTextArea.getVerticalScrollBar().setUnitIncrement(8);
		panelCarData.add(scrollPaneTextArea);

		textArea = new JTextArea();
		textArea.setFont(font);
		textArea.setDocument(new StringOnly(1000));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPaneTextArea.setViewportView(textArea);

		JLabel labelDateBought = new JLabel("Date Bought");
		labelDateBought.setFont(smallFont);
		labelDateBought.setBounds(178, 387, 200, 15);
		panelCarData.add(labelDateBought);

		textFieldDateBought = new JTextField();
		textFieldDateBought.setFont(font);
		textFieldDateBought.setHorizontalAlignment(JTextField.CENTER);
		textFieldDateBought.setBounds(178, 404, 150, 40);
		textFieldDateBought.setBorder(Const.GRAY_BORDER);
		textFieldDateBought.setEditable(false);
		panelCarData.add(textFieldDateBought);

		comboBoxYearBought = new JComboBox<>();
		comboBoxYearBought.setFont(font);
		comboBoxYearBought.setBounds(178, 404, 71, 40);
		comboBoxYearBought.setBackground(Color.WHITE);
		comboBoxYearBought.setFocusable(false);
		fillComboBoxYear(comboBoxYearBought);
		comboBoxYearBought.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				int year = Integer.parseInt((String) comboBoxYearBought.getSelectedItem());
				int month = Integer.parseInt((String) comboBoxMonthBought.getSelectedItem());
				fillComboBoxDay(year, month, comboBoxDayBought);
			}  
		});
		panelCarData.add(comboBoxYearBought);

		comboBoxMonthBought = new JComboBox<>();
		comboBoxMonthBought.setFont(font);
		comboBoxMonthBought.setBounds(249, 404, 49, 40);
		comboBoxMonthBought.setBackground(Color.WHITE);
		comboBoxMonthBought.setFocusable(false);
		fillBomboBoxMonth(comboBoxMonthBought);
		comboBoxMonthBought.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				int year = Integer.parseInt((String) comboBoxYearBought.getSelectedItem());
				int month = Integer.parseInt((String) comboBoxMonthBought.getSelectedItem());
				fillComboBoxDay(year, month, comboBoxDayBought);
			}  
		});
		panelCarData.add(comboBoxMonthBought);

		comboBoxDayBought = new JComboBox<>();
		comboBoxDayBought.setFont(font);
		comboBoxDayBought.setBounds(298, 404, 49, 40);
		comboBoxDayBought.setBackground(Color.WHITE);
		comboBoxDayBought.setFocusable(false);
		fillComboBoxDay(2020, 1, comboBoxDayBought);
		panelCarData.add(comboBoxDayBought);

		JLabel labelPriceBought = new JLabel("Price Bought");
		labelPriceBought.setFont(smallFont);
		labelPriceBought.setBounds(348, 387, 230, 15);
		panelCarData.add(labelPriceBought);

		textFieldPriceBought = new JTextField();
		textFieldPriceBought.setFont(font);
		textFieldPriceBought.setBounds(348, 404, 230, 40);
		textFieldPriceBought.setBorder(Const.GRAY_BORDER);
		textFieldPriceBought.setDocument(new DoubleOnly());
		textFieldPriceBought.setHorizontalAlignment(JTextField.CENTER);
		panelCarData.add(textFieldPriceBought);

		labelSold = new JLabel("Sold");
		labelSold.setFont(smallFont);
		labelSold.setBounds(598, 387, 100, 15);
		panelCarData.add(labelSold);

		textFieldSold = new JTextField();
		textFieldSold.setFont(font);
		textFieldSold.setHorizontalAlignment(JTextField.CENTER);
		textFieldSold.setBounds(598, 404, 100, 40);
		textFieldSold.setBorder(Const.GRAY_BORDER);
		textFieldSold.setEditable(false);
		panelCarData.add(textFieldSold);

		checkBoxSold = new JCheckBox("Sold");
		checkBoxSold.setFont(font);
		checkBoxSold.setBounds(616, 404, 100, 40);
		checkBoxSold.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				boolean selected = checkBoxSold.isSelected();
				textFieldPriceSold.setEditable(selected);
				comboBoxYearSold.setEnabled(selected);
				comboBoxMonthSold.setEnabled(selected);
				comboBoxDaySold.setEnabled(selected); 
			}  
		});
		panelCarData.add(checkBoxSold);

		JLabel labelPriceSold = new JLabel("Price Sold");
		labelPriceSold.setFont(smallFont);
		labelPriceSold.setBounds(718, 387, 230, 15);
		panelCarData.add(labelPriceSold);

		textFieldPriceSold = new JTextField();
		textFieldPriceSold.setFont(font);
		textFieldPriceSold.setBounds(718, 404, 230, 40);
		textFieldPriceSold.setBorder(Const.GRAY_BORDER);
		textFieldPriceSold.setDocument(new DoubleOnly());
		textFieldPriceSold.setHorizontalAlignment(JTextField.CENTER);
		textFieldPriceSold.setEditable(false);
		panelCarData.add(textFieldPriceSold);

		JLabel labelDateSold = new JLabel("Date Sold");
		labelDateSold.setFont(smallFont);
		labelDateSold.setBounds(968, 387, 200, 15);
		panelCarData.add(labelDateSold);

		textFieldDateSold = new JTextField();
		textFieldDateSold.setFont(font);
		textFieldDateSold.setHorizontalAlignment(JTextField.CENTER);
		textFieldDateSold.setBounds(968, 404, 150, 40);
		textFieldDateSold.setBorder(Const.GRAY_BORDER);
		textFieldDateSold.setEditable(false);
		panelCarData.add(textFieldDateSold);

		comboBoxYearSold = new JComboBox<>();
		comboBoxYearSold.setFont(font);
		comboBoxYearSold.setBounds(949, 404, 71, 40);
		comboBoxYearSold.setBackground(Color.WHITE);
		comboBoxYearSold.setFocusable(false);
		fillComboBoxYear(comboBoxYearSold);
		comboBoxYearSold.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				int year = Integer.parseInt((String) comboBoxYearSold.getSelectedItem());
				int month = Integer.parseInt((String) comboBoxMonthSold.getSelectedItem());
				fillComboBoxDay(year, month, comboBoxDaySold);
			}  
		});
		comboBoxYearSold.setEnabled(false);
		panelCarData.add(comboBoxYearSold);

		comboBoxMonthSold = new JComboBox<>();
		comboBoxMonthSold.setFont(font);
		comboBoxMonthSold.setBounds(1020, 404, 49, 40);
		comboBoxMonthSold.setBackground(Color.WHITE);
		comboBoxMonthSold.setFocusable(false);
		fillBomboBoxMonth(comboBoxMonthSold);
		comboBoxMonthSold.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				int year = Integer.parseInt((String) comboBoxYearSold.getSelectedItem());
				int month = Integer.parseInt((String) comboBoxMonthSold.getSelectedItem());
				fillComboBoxDay(year, month, comboBoxDaySold);
			}  
		});
		comboBoxMonthSold.setEnabled(false);
		panelCarData.add(comboBoxMonthSold);

		comboBoxDaySold = new JComboBox<>();
		comboBoxDaySold.setFont(font);
		comboBoxDaySold.setBounds(1069, 404, 49, 40);
		comboBoxDaySold.setBackground(Color.WHITE);
		comboBoxDaySold.setFocusable(false);
		fillComboBoxDay(2020, 1, comboBoxDaySold);
		comboBoxDaySold.setEnabled(false);
		panelCarData.add(comboBoxDaySold);

		setEditMode(false);
		setVisible(false);
	}

	public void setCar(Car car) {
		this.car = car;
		updateData();
		loadPhotos();
	}

	private void updateData() {
		textFieldBrand.setText(car.getBrand());
		textFieldModel.setText(car.getModel());
		textFieldYear.setText(car.getYear() + "");
		textFieldColor.setText(car.getColor());
		textFieldEngine.setText(car.getEngine());
		textFieldGearbox.setText(car.getGearbox());
		textFieldMileage.setText(car.getMileage() + "");
		textFieldFuel.setText(car.getFuel());
		textArea.setText(car.getAbout());
		comboBoxBodyType.setSelectedItem(car.getBodyType());
		comboBoxBrand.setSelectedItem(car.getBrand());
		comboBoxGearbox.setSelectedItem(car.getGearbox());
		comboBoxFuel.setSelectedItem(car.getFuel());
		String dateBought = car.getDateBought();
		textFieldDateBought.setText(dateBought);
		comboBoxYearBought.setSelectedItem(dateBought.substring(0, 4));
		comboBoxMonthBought.setSelectedItem(dateBought.substring(5, 7));
		comboBoxDayBought.setSelectedItem(dateBought.substring(8));
		textFieldPriceBought.setText(car.getPriceBought() + "");
		if(car.isSold()) {
			textFieldSold.setText("True");
			checkBoxSold.setSelected(true);
			textFieldPriceSold.setText(car.getPriceSold() + "");
			String dateSold = car.getDateSold();
			textFieldDateSold.setText(dateSold);
			comboBoxYearSold.setSelectedItem(dateSold.substring(0, 4));
			comboBoxMonthSold.setSelectedItem(dateSold.substring(5, 7));
			comboBoxDaySold.setSelectedItem(dateSold.substring(8));
		}
		else {
			textFieldSold.setText("False");
			checkBoxSold.setSelected(false);
			textFieldPriceSold.setText("");
			textFieldDateSold.setText("");
			comboBoxYearSold.setSelectedIndex(0);
			comboBoxMonthSold.setSelectedIndex(0);
			comboBoxDaySold.setSelectedIndex(0);
		}
	}

	private void loadPhotos() {
		photos.clear();
		boolean gotLastPhoto = false;
		int i = 0;
		while(!gotLastPhoto) {
			String imagePath = "img/cars/" + car.getId() + "/" + car.getId() + "_" + i + ".jpg";
			if(new File(imagePath).exists()) {
				photos.add(new Photo(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(600, 450, Image.SCALE_DEFAULT)), imagePath));
				i++;
			}
			else {
				gotLastPhoto = true;
			}
		}
		selectedPhoto = 0;
		loadSelectedPhoto();
	}

	public void loadSelectedPhoto() {
		if(selectedPhoto > 0) {
			labelLeftPhoto.setIcon(photos.get(selectedPhoto - 1).getPhoto());
			labelLeftPhoto.setVisible(true);
			buttonLeft.setVisible(true);
		}
		else {
			labelLeftPhoto.setIcon(null);
			labelLeftPhoto.setVisible(false);
			buttonLeft.setVisible(false);
		}
		if(photos.size() > 0) {
			labelCenterPhoto.setIcon(photos.get(selectedPhoto).getPhoto());
			buttonDeletePhoto.setEnabled(true);
		}
		else {
			labelCenterPhoto.setIcon(new ImageIcon("img/imageNotFound.jpg"));
			buttonDeletePhoto.setEnabled(false);
		}
		if(selectedPhoto < photos.size() - 1) {
			labelRightPhoto.setIcon(photos.get(selectedPhoto + 1).getPhoto());
			labelRightPhoto.setVisible(true);
			buttonRight.setVisible(true);
		}
		else {
			labelRightPhoto.setIcon(null);
			labelRightPhoto.setVisible(false);
			buttonRight.setVisible(false);
		}
	}

	private void setEditMode(boolean editMode) {
		textFieldModel.setEditable(editMode);
		textFieldYear.setEditable(editMode);
		textFieldColor.setEditable(editMode);
		textFieldEngine.setEditable(editMode);
		textFieldMileage.setEditable(editMode);
		textArea.setEditable(editMode);
		if(editMode) {
			textArea.setBackground(Color.WHITE);
		}
		else {
			textArea.setBackground(new Color(238, 238, 238));
		}
		textFieldPriceBought.setEditable(editMode);
		boolean isSelectedAndEditable = checkBoxSold.isSelected() && editMode;
		textFieldPriceSold.setEditable(isSelectedAndEditable);
		comboBoxYearSold.setEnabled(isSelectedAndEditable);
		comboBoxMonthSold.setEnabled(isSelectedAndEditable);
		comboBoxDaySold.setEnabled(isSelectedAndEditable);
		buttonSave.setVisible(editMode);
		buttonEdit.setVisible(!editMode);
		buttonDelete.setVisible(!editMode);
		buttonClose.setVisible(!editMode);
		buttonCancel.setVisible(editMode);
		buttonAddPhoto.setVisible(editMode);
		buttonDeletePhoto.setVisible(editMode);
		labelBodyType.setVisible(editMode);
		comboBoxBodyType.setVisible(editMode);
		textFieldBrand.setVisible(!editMode);
		comboBoxBrand.setVisible(editMode);
		textFieldGearbox.setVisible(!editMode);
		comboBoxGearbox.setVisible(editMode);
		textFieldFuel.setVisible(!editMode);
		comboBoxFuel.setVisible(editMode);
		textFieldDateBought.setVisible(!editMode);
		comboBoxYearBought.setVisible(editMode);
		comboBoxMonthBought.setVisible(editMode);
		comboBoxDayBought.setVisible(editMode);
		textFieldSold.setVisible(!editMode);
		labelSold.setVisible(!editMode);
		checkBoxSold.setVisible(editMode);
		textFieldDateSold.setVisible(!editMode);
		comboBoxYearSold.setVisible(editMode);
		comboBoxMonthSold.setVisible(editMode);
		comboBoxDaySold.setVisible(editMode);
		SwingUtilities.updateComponentTreeUI(this);
	}

	private void fillComboBoxYear(JComboBox<String> comboBox) {
		for(int i = 2020; i <= 2030; i++) {
			comboBox.addItem(i + "");
		}
	}

	private void fillBomboBoxMonth(JComboBox<String> comboBox) {
		for(int i = 1; i <= 12; i++) {
			if(i < 10) {
				comboBox.addItem("0" + i);
			}
			else {
				comboBox.addItem(i + "");
			}
		}
	}

	private void fillComboBoxDay(int year, int month, JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		int days;
		if(month == 2) {
			if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				days = 29;
			}
			else {
				days = 28;
			}
		}
		else if(month == 4 || month == 6 || month == 9 || month == 11) {
			days = 30;
		}
		else {
			days = 31;
		}
		for(int i = 1; i <= days; i++) {
			if(i < 10) {
				comboBox.addItem("0" + i);
			}
			else {
				comboBox.addItem(i + "");
			}
		}
	}
}
