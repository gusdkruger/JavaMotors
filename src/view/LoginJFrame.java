package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import dao.UserDAO;
import view.consts.Const;
import view.textFieldDocument.StringOnly;

@SuppressWarnings("serial")
public class LoginJFrame extends JFrame {

	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JLabel labelInvalid;
	private Timer timer;
	private JButton buttonLogin;

	private final Font smallFont = new Font("Arial", Font.PLAIN, 15);
	private final Font smallFontBold = new Font("Arial", Font.BOLD, 15);
	private final Font bigFont = new Font("Arial", Font.PLAIN, 27);
	private final Font bigFontBold = new Font("Arial", Font.BOLD, 27);

	public LoginJFrame() {
		super("JavaMotors");
		setIconImage(Const.ICON);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(426, 354);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		JLabel labelUsername = new JLabel("Username");
		labelUsername.setFont(smallFont);
		labelUsername.setBounds(80, 23, 250, 15);
		add(labelUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setFont(bigFont);
		textFieldUsername.setBounds(80, 40, 250, 50);
		textFieldUsername.setBorder(Const.GRAY_BORDER);
		textFieldUsername.setDocument(new StringOnly(30));
		add(textFieldUsername);

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setFont(smallFont);
		labelPassword.setBounds(80, 113, 250, 15);
		add(labelPassword);

		passwordField = new JPasswordField();
		passwordField.setFont(bigFont);
		passwordField.setBounds(80, 130, 250, 50);
		passwordField.setBorder(Const.GRAY_BORDER);
		passwordField.setDocument(new StringOnly(30));
		add(passwordField);

		labelInvalid = new JLabel("Invalid username or password");
		labelInvalid.setFont(smallFontBold);
		labelInvalid.setHorizontalAlignment(JTextField.CENTER);
		labelInvalid.setBounds(80, 203, 250, 15);
		labelInvalid.setForeground(Const.RED);
		labelInvalid.setVisible(false);
		add(labelInvalid);

		timer = new Timer(3000, (e) -> {
			labelInvalid.setVisible(false);
			timer.stop();
		});

		buttonLogin = new JButton("Log in");
		buttonLogin.setFont(bigFontBold);
		buttonLogin.setBounds(80, 220, 250, 55);
		buttonLogin.setBorder(Const.WHITE_BORDER);
		buttonLogin.setBackground(Const.RED);
		buttonLogin.setForeground(Color.WHITE);
		buttonLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonLogin.setFocusable(false);
		buttonLogin.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				String username = textFieldUsername.getText();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText();
				if (!username.isBlank() && !password.isBlank()) {
					int dealershipId = UserDAO.authenticateLogin(username, password);
					if (dealershipId != -1) {
						new MainJFrame(dealershipId);
						dispose();
					}
					else {
						passwordField.setText("");
						labelInvalid.setVisible(true);
						timer.start();
					}
				}
			}  
		});
		add(buttonLogin);
		
		textFieldUsername.setText("CarHouse");
		passwordField.setText("admin");
		setVisible(true);
	}
}
