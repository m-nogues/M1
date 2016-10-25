/**
 * Symbol for functions (declarations/calls). In addition to the name, this
 * class has a label attribute. The label associated to a function is the 3a
 * name in CALL and the address in 3a code function definition.
 *
 * @author MLB
 */
public class FunctionSymbol extends Operand3a {

	/** The label. */
	public final LabelSymbol label;

	/**
	 * Constructor.
	 *
	 * @param lab
	 *            LabelAttribute: the label associated
	 * @param t
	 *            : a function type handled separately in compilation process
	 */
	public FunctionSymbol(LabelSymbol lab, FunctionType t) {
		super(t);
		label = lab;
	}

	/**
	 * Necessary for TAC printing.
	 *
	 * @return the name 3 a
	 */
	@Override
	public String getName3a() {
		return label.name;
	}

	/**
	 * The scope of a function is always zero in VSL+.
	 *
	 * @return the scope
	 */
	@Override
	public int getScope() {
		return 0;
	}
}
