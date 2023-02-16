package de.bp.pacman.ui;

import javafx.scene.control.TextField;

/**
 * Modifier Class of the FXML method Textfield to control input length
 */
public class TextFieldLimited extends TextField {
    private int maxlength;

    /**
     * Initiates the Textfield with a maximum input length of 10 characters.
     */
    public TextFieldLimited() {
        this.maxlength = 10;
    }

    /**
     * Controls the input length
     *
     * @param start The starting index in the range, inclusive. This must be &gt;= 0 and &lt; the end.
     * @param end The ending index in the range, exclusive. This is one-past the last character to
     *            delete (consistent with the String manipulation methods). This must be &gt; the start,
     *            and &lt;= the length of the text.
     * @param text The text that is to replace the range. This must not be null.
     */
    @Override
    public void replaceText(int start, int end, String text) {
        if (text.equals("")) {
            super.replaceText(start, end, text);
        } else if (getText().length() < maxlength && !text.contains("-")) {
            super.replaceText(start, end, text.toUpperCase());
        }
    }

    /**
     * Controls the input length upon selection
     *
     * @param text the replacement string
     */
    @Override
    public void replaceSelection(String text) {
        if (text.equals("")) {
            super.replaceSelection(text);
        } else if (getText().length() < maxlength && !text.contains("-")) {
            if (text.length() > maxlength - getText().length()) {
                text = text.substring(0, maxlength- getText().length());
            }
            super.replaceSelection(text);
        }
    }
}