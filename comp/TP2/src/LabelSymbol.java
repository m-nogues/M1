/**
 * Symbol for program labels (mainly for function calls). Except for functions,
 * the name of a label must be provided by SymbDistrib via newLabel().
 *
 * @author MLB
 */
public class LabelSymbol extends Operand3a {

	/** The name. */
	public String name;

	/**
	 * Instantiates a new label symbol.
	 *
	 * @param n
	 *            the n
	 */
	public LabelSymbol(String n) {
		super(Type.LABEL);
		name = n;
	}

	/*
	 * (non-Javadoc)
	 * @see Operand3a#getName3a()
	 */
	@Override
	public String getName3a() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see Operand3a#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}
