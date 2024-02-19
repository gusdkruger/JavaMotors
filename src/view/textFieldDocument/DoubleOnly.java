package view.textFieldDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class DoubleOnly extends PlainDocument {

	public void insertString(int offs, String s, AttributeSet a) throws BadLocationException {
		if(s == null) {
			return;
		}
		if(s.matches("[0-9.]+")) {
			String before = getText(0, getLength());
			String after = before.substring(0, offs) + s + before.substring(offs);
			if(validateData(after)) {
				super.insertString(offs, s, a);
			}
		}
	}

	private boolean validateData(String string) {
		if(string.contains(".")) {
			int amountOfDots = 0;
			int dotPosition = 0;
			for(int i = 0; i < string.length(); i++) {
				if(string.charAt(i) == '.') {
					amountOfDots++;
					dotPosition = i;
				}
			}
			if(amountOfDots == 1) {
				String beforeDot = string.substring(0, dotPosition);
				String afterDot = string.substring(dotPosition + 1, string.length());
				if(beforeDot.length() > 9 || afterDot.length() > 2) {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			if(string.length() > 9) {
				return false;
			}
		}
		return true;
	}
}
