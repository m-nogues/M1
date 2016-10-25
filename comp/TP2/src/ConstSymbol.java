/**
 * Symbol for an integer constant. It could be a string since we never use the
 * value in integer operations.
 *
 * @author MLB
 */
public class ConstSymbol extends Operand3a {

	/** The value. */
	public int value;

	/**
	 * Instantiates a new const symbol.
	 *
	 * @param v
	 *            the v
	 */
	public ConstSymbol(int v) {
		super(Type.I_CONST);
		value = v;
	}

	/**
	 * For code 3a printing.
	 *
	 * @return the name 3 a
	 */
	@Override
	public String getName3a() {
		return String.valueOf(value);
	}
}
