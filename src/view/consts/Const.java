package view.consts;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Const {

	public static final Image ICON = new ImageIcon("img/icon.png").getImage();

	public static final Color RED = new Color(243, 18, 60);
	public static final Color WHITE = new Color(200, 200, 200);

	public static final Border BLACK_BORDER = new LineBorder(Color.BLACK, 1, false);
	public static final Border GRAY_BORDER = new LineBorder(Color.GRAY, 1, false);
	public static final Border WHITE_BORDER = new LineBorder(WHITE, 2, false);
}
