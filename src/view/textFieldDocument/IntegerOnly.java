package view.textFieldDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class IntegerOnly extends PlainDocument {

	private final int maxLength;

	public IntegerOnly(int maxLength) {
		this.maxLength = maxLength;
	}

	public void insertString(int offs, String s, AttributeSet a) throws BadLocationException {
		if(s == null) {
			return;
		}
		if(s.matches("[0-9]+") && (getLength() + s.length()) <= maxLength) {
			super.insertString(offs, s, a);
		}
	}
}
