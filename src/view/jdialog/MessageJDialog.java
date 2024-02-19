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
public class MessageJDialog extends JDialog {

	private JButton buttonOk;

	private final Font font = new Font("Arial", Font.BOLD, 20);

	public MessageJDialog(String feedback) {
		setIconImage(Const.ICON);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(597, 150);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setModal(true);

		JLabel labelFeedback = new JLabel(feedback, SwingConstants.CENTER);
		labelFeedback.setFont(font);
		labelFeedback.setBounds(15, 14, 551, 20);
		add(labelFeedback);

		buttonOk = new JButton("Ok");
		buttonOk.setFont(font);
		buttonOk.setBounds(255, 51, 70, 40);
		buttonOk.setBorder(Const.WHITE_BORDER);
		buttonOk.setBackground(Const.RED);
		buttonOk.setForeground(Color.WHITE);
		buttonOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonOk.setFocusable(false);
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(buttonOk);

		setVisible(true);
	}
}
