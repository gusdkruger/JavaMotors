package view.textFieldDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class StringOnly extends PlainDocument {

	private final int maxLength;

	public StringOnly(int maxLength) {
		this.maxLength = maxLength;
	}

	public void insertString(int offs, String s, AttributeSet a) throws BadLocationException {
		if(s == null) {
			return;
		}
		if(!s.contains("'") && !s.contains("\"") && (getLength() + s.length()) <= maxLength) {
			super.insertString(offs, s, a);
		}
	}
}
