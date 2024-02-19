package view.jdialog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import view.consts.Const;

@SuppressWarnings("serial")
public class ConfirmJDialog extends JDialog {

	private boolean confirm;

	private JButton buttonYes;
	private JButton buttonNo;

	private final Font font = new Font("Arial", Font.BOLD, 20);

	public ConfirmJDialog(String question) {
		this.confirm = false;
		setIconImage(Const.ICON);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(614, 150);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setModal(true);

		JLabel labelConfirm = new JLabel(question, SwingConstants.CENTER);
		labelConfirm.setFont(font);
		labelConfirm.setBounds(15, 14, 570, 20);
		add(labelConfirm);

		buttonYes = new JButton("Yes");
		buttonYes.setFont(font);
		buttonYes.setBounds(214, 51, 70, 40);
		buttonYes.setBorder(Const.WHITE_BORDER);
		buttonYes.setBackground(Const.RED);
		buttonYes.setForeground(Color.WHITE);
		buttonYes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonYes.setFocusable(false);
		buttonYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm = true;
				setVisible(false);
			}
		});
		add(buttonYes);

		buttonNo = new JButton("No");
		buttonNo.setFont(font);
		buttonNo.setBounds(314, 51, 70, 40);
		buttonNo.setBorder(Const.WHITE_BORDER);
		buttonNo.setBackground(Const.RED);
		buttonNo.setForeground(Color.WHITE);
		buttonNo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonNo.setFocusable(false);
		buttonNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		add(buttonNo);

		setVisible(true);
	}

	public boolean getConfirm() {
		return confirm;
	}
}
