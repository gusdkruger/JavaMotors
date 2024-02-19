package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Car;
import view.consts.Const;

@SuppressWarnings("serial")
public class CarCardJPanel extends JPanel implements MouseListener {

	private Car car;
	private MainJFrame mainJFrame;
	private boolean mouseRelesedOnCard;

	private final Font font = new Font("Arial", Font.PLAIN, 23);
	private final Font fontBold = new Font("Arial", Font.PLAIN, 23);

	public CarCardJPanel(Car car, MainJFrame mainJFrame) {
		this.car = car;
		this.mainJFrame = mainJFrame;
		this.mouseRelesedOnCard = false;
		setBorder(Const.WHITE_BORDER);
		setBackground(Color.WHITE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLayout(null);
		addMouseListener(this);

		String imagePath = "img/cars/" + car.getId() + "/" + car.getId() + "_0.jpg";
		ImageIcon image;
		if(new File(imagePath).exists()) {
			image = new ImageIcon(imagePath);
		}
		else {
			image = new ImageIcon("img/imageNotFound.jpg");
		}
		JLabel labelPhoto = new JLabel(new ImageIcon(image.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT)));
		labelPhoto.setBounds(5, 5, 400, 300);
		add(labelPhoto);

		JLabel labelModelBrand = new JLabel(car.getBrand() + " " + car.getModel());
		labelModelBrand.setFont(fontBold);
		labelModelBrand.setBounds(5, 314, 400, 20);
		add(labelModelBrand);

		JLabel labelEngineGearbox = new JLabel(car.getEngine() + " " + car.getGearbox());
		labelEngineGearbox.setFont(font);
		labelEngineGearbox.setBounds(5, 339, 400, 20);
		add(labelEngineGearbox);

		JLabel labelMileage = new JLabel(car.getMileage() + " km");
		labelMileage.setFont(font);
		labelMileage.setBounds(5, 364, 400, 20);
		add(labelMileage);

		JLabel labelYear = new JLabel(car.getYear() + "");
		labelYear.setFont(font);
		labelYear.setBounds(351, 364, 400, 20);
		add(labelYear);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(mouseRelesedOnCard) {
			mainJFrame.openCarPage(car);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseRelesedOnCard = true;

	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseRelesedOnCard = false;
	}
}
