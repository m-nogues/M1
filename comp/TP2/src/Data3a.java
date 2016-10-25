/**
 * Class for implementing initialized constants. The only constants in VSL are
 * character strings. A label must be associated to its textual content and is
 * used as the name of the text in three-address code. So the class is simple:
 * it contains the label and its text.
 *
 * @author MLB
 */

public class Data3a {

	/** The label. */
	private LabelSymbol label;

	/** The text. */
	private String text;

	/**
	 * Instantiates a new data 3 a.
	 *
	 * @param t
	 *            the t
	 */
	public Data3a(String t) {
		label = SymbDistrib.newLabel();
		text = t;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public LabelSymbol getLabel() {
		return label;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Prints the.
	 */
	public void print() {
		System.out.println(toString());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return label.toString() + ":" + text;
	}

}
