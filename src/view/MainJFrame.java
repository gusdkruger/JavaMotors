package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.CarController;
import dao.DealershipDAO;
import enums.CarBodyType;
import enums.CarBrand;
import model.Car;
import view.consts.Const;
import view.textFieldDocument.StringOnly;

@SuppressWarnings("serial")
public class MainJFrame extends JFrame {

	private int dealershipId;
	private ArrayList<Car> cars;
	private boolean freezeActionEvent;

	private JPanel panelFilters;
	private JButton buttonNew;
	private NewCarPageJPanel newCarPage;
	private JTextField textFieldSearch;
	private JButton buttonSearch;
	private JComboBox<String> comboBoxBrand;
	private JComboBox<String> comboBoxBodyType;
	private JComboBox<String> comboBoxSold;
	private JButton buttonClear;
	private JScrollPane scrollPane;
	private JPanel cardPanel;
	private CarPageJPanel carPage;

	private final Font font = new Font("Arial", Font.PLAIN, 20);
	private final Font fontBold = new Font("Arial", Font.BOLD, 20);

	public MainJFrame(int dealershipId) {
		super(DealershipDAO.read(dealershipId));
		this.dealershipId = dealershipId;
		this.cars = CarController.read(dealershipId);
		this.freezeActionEvent = false;
		setIconImage(Const.ICON);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1313, 786);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		panelFilters = new JPanel();
		panelFilters.setBounds(0, 0, 1297, 60);
		panelFilters.setBackground(Const.RED);
		panelFilters.setLayout(null);
		add(panelFilters);

		buttonNew = new JButton("New");
		buttonNew.setFont(fontBold);
		buttonNew.setBounds(10, 10, 70, 40);
		buttonNew.setBorder(Const.WHITE_BORDER);
		buttonNew.setBackground(Const.RED);
		buttonNew.setForeground(Color.WHITE);
		buttonNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonNew.setFocusable(false);
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelFilters.setVisible(false);
				scrollPane.setVisible(false);
				newCarPage = new NewCarPageJPanel(dealershipId, MainJFrame.this);
				add(newCarPage);
			}
		});
		panelFilters.add(buttonNew);

		textFieldSearch = new JTextField();
		textFieldSearch.setFont(font);
		textFieldSearch.setBounds(90, 10, 287, 40);
		textFieldSearch.setBorder(Const.GRAY_BORDER);
		textFieldSearch.setDocument(new StringOnly(30));
		panelFilters.add(textFieldSearch);

		buttonSearch = new JButton("Search");
		buttonSearch.setFont(fontBold);
		buttonSearch.setBounds(387, 10, 100, 40);
		buttonSearch.setBorder(Const.WHITE_BORDER);
		buttonSearch.setBackground(Const.RED);
		buttonSearch.setForeground(Color.WHITE);
		buttonSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonSearch.setFocusable(false);
		buttonSearch.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				fillCardPanel();
			}  
		});
		panelFilters.add(buttonSearch);

		JLabel labelBrand = new JLabel("Brand");
		labelBrand.setFont(fontBold);
		labelBrand.setBounds(501, 10, 70, 40);
		labelBrand.setForeground(Color.WHITE);
		panelFilters.add(labelBrand);

		comboBoxBrand = new JComboBox<>();
		comboBoxBrand.setFont(fontBold);
		comboBoxBrand.setBounds(573, 10, 173, 40);
		comboBoxBrand.setBackground(Color.WHITE);
		comboBoxBrand.setFocusable(false);
		comboBoxBrand.addItem("Any");
		for(CarBrand carBrand : CarBrand.values()) {
			comboBoxBrand.addItem(carBrand.getDisplayName());
		}
		comboBoxBrand.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				if(!freezeActionEvent) {
					fillCardPanel();
				}
			}  
		});
		panelFilters.add(comboBoxBrand);

		JLabel labelBodyType = new JLabel("Body Type:");
		labelBodyType.setFont(fontBold);
		labelBodyType.setBounds(760, 10, 110, 40);
		labelBodyType.setForeground(Color.WHITE);
		panelFilters.add(labelBodyType);

		comboBoxBodyType = new JComboBox<>();
		comboBoxBodyType.setFont(fontBold);
		comboBoxBodyType.setBounds(877, 10, 161, 40);
		comboBoxBodyType.setBackground(Color.WHITE);
		comboBoxBodyType.setFocusable(false);
		comboBoxBodyType.addItem("Any");
		for(CarBodyType carBodyType : CarBodyType.values()) {
			comboBoxBodyType.addItem(carBodyType.getDisplayName());
		}
		comboBoxBodyType.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				if(!freezeActionEvent) {
					fillCardPanel();
				}
			}  
		});
		panelFilters.add(comboBoxBodyType);

		JLabel labelSold = new JLabel("Sold:");
		labelSold.setFont(fontBold);
		labelSold.setBounds(1053, 10, 50, 40);
		labelSold.setForeground(Color.WHITE);
		panelFilters.add(labelSold);

		comboBoxSold = new JComboBox<>();
		comboBoxSold.setFont(fontBold);
		comboBoxSold.setBounds(1110, 10, 77, 40);
		comboBoxSold.setBackground(Color.WHITE);
		comboBoxSold.setFocusable(false);
		comboBoxSold.addItem("Any");
		comboBoxSold.addItem("True");
		comboBoxSold.addItem("False");
		comboBoxSold.setSelectedIndex(2);
		comboBoxSold.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				if(!freezeActionEvent) {
					fillCardPanel();
				}
			}  
		});
		panelFilters.add(comboBoxSold);

		buttonClear = new JButton("Clear");
		buttonClear.setFont(fontBold);
		buttonClear.setBounds(1197, 10, 90, 40);
		buttonClear.setBorder(Const.WHITE_BORDER);
		buttonClear.setBackground(Const.RED);
		buttonClear.setForeground(Color.WHITE);
		buttonClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonClear.setFocusable(false);
		buttonClear.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				freezeActionEvent = true;
				textFieldSearch.setText("");
				comboBoxBrand.setSelectedIndex(0);
				comboBoxBodyType.setSelectedIndex(0);
				comboBoxSold.setSelectedIndex(2);
				freezeActionEvent = false;
				fillCardPanel();
			}  
		});
		panelFilters.add(buttonClear);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 60, 1298, 688);
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		add(scrollPane);

		cardPanel = new JPanel();
		cardPanel.setLayout(null);
		scrollPane.setViewportView(cardPanel);

		carPage = new CarPageJPanel(this);
		add(carPage);

		fillCardPanel();
		setVisible(true);
	}

	private ArrayList<Car> filterCars() {
		String selectedModel = textFieldSearch.getText().toLowerCase();
		String selectedBrand = (String) comboBoxBrand.getSelectedItem();
		String selectedBodyType = (String) comboBoxBodyType.getSelectedItem();
		String selectedSold = (String) comboBoxSold.getSelectedItem();
		ArrayList<Car> list = new ArrayList<>();
		for(int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			if(car.getModel().toLowerCase().contains(selectedModel)) {
				if(comboBoxBrand.getSelectedIndex() == 0 || selectedBrand.equals(car.getBrand())) {
					if(comboBoxBodyType.getSelectedIndex() == 0 || selectedBodyType.equals(car.getBodyType())) {
						if(comboBoxSold.getSelectedIndex() == 0 || selectedSold.equalsIgnoreCase(car.isSold() + "")) {
							list.add(car);
						}
					}
				}
			}
		}
		return list;
	}

	public void fillCardPanel() {
		cars = CarController.read(dealershipId);
		ArrayList<Car> filteredCars = filterCars();
		cardPanel.removeAll();
		int filteredCarsSize = filteredCars.size();
		if(filteredCarsSize > 0) {
			int i;
			int iterations = filteredCarsSize - (filteredCarsSize % 3);
			int x = 15;
			int y = 15;
			for(i = 0; i < iterations; i++) {
				CarCardJPanel card = new CarCardJPanel(filteredCars.get(i), this);
				card.setBounds(x, y, 410, 397);
				cardPanel.add(card);
				if(x == 855) {
					x = 15;
					y += 407;
				}
				else {
					x += 420;
				}
			}
			if(filteredCarsSize - iterations == 2) {
				x = 225;
				CarCardJPanel cardLeft = new CarCardJPanel(filteredCars.get(i), this);
				cardLeft.setBounds(x, y, 410, 397);
				cardPanel.add(cardLeft);
				x += 420;
				CarCardJPanel cardRight = new CarCardJPanel(filteredCars.get(i + 1), this);
				cardRight.setBounds(x, y, 410, 397);
				cardPanel.add(cardRight);
				y += 407;
			}
			else if(filteredCarsSize - iterations == 1) {
				x = 435;
				CarCardJPanel cardCenter = new CarCardJPanel(filteredCars.get(i), this);
				cardCenter.setBounds(x, y, 410, 397);
				cardPanel.add(cardCenter);
				y += 407;
			}
			cardPanel.setPreferredSize(new Dimension(0, y + 5));
		}
		else {
			JLabel label = new JLabel("There are no cars with given filters");
			label.setFont(new Font("Arial", Font.BOLD, 40));
			label.setBounds(323, 97, 650, 50);
			label.setForeground(Const.RED);
			cardPanel.add(label);
			cardPanel.setPreferredSize(new Dimension(0, 0));
		}
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void closeNewCarPage() {
		remove(newCarPage);
		panelFilters.setVisible(true);
		scrollPane.setVisible(true);
	}

	public void openCarPage(Car car) {
		panelFilters.setVisible(false);
		scrollPane.setVisible(false);
		carPage.setCar(car);
		carPage.setVisible(true);
	}

	public void closeCarPage() {
		carPage.setVisible(false);
		panelFilters.setVisible(true);
		scrollPane.setVisible(true);
	}
}
