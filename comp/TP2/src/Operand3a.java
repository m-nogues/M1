/**
 * Abstract class for implementing TAC operands (which are also the "symbols"
 * stored in the SymbolTable). This class has the minimal set of attributes for
 * (assembler) code generation. The abstract methods defined in the interface
 * are also necessary for assembler code generation. They might be useful for
 * type checking and TAC generation.
 * <p>
 * The offset attribute is set and used by the machine code generator. It
 * represents the address of the operand relative to the top of the function
 * frame in the execution stack. You don't need it in the frontend.
 *
 * @author MLB
 */
abstract public class Operand3a implements CGInterface {

	/** The type. */
	public final Type type;

	/** The offset. */
	private int offset;

	/**
	 * Instantiates a new operand 3 a.
	 *
	 * @param t
	 *            the t
	 */
	public Operand3a(Type t) {
		type = t;
		offset = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see CGInterface#getName3a()
	 */
	// Must be overridden by subclasses
	@Override
	abstract public String getName3a();

	/**
	 * returns the address of the operand.
	 *
	 * @return the address
	 */
	public int getOffset() {
		return offset;
	}

	/*
	 * (non-Javadoc)
	 * @see CGInterface#getScope()
	 */
	@Override
	public int getScope() {
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * @see CGInterface#getSize()
	 */
	@Override
	public int getSize() {
		return type.getSize();
	}

	/*
	 * (non-Javadoc)
	 * @see CGInterface#isArray()
	 */
	@Override
	public boolean isArray() {
		return type instanceof ArrayType;
	}

	/*
	 * (non-Javadoc)
	 * @see CGInterface#isConstInteger()
	 */
	@Override
	public boolean isConstInteger() {
		return type == Type.I_CONST;
	}

	/*
	 * (non-Javadoc)
	 * @see CGInterface#isLabel()
	 */
	@Override
	public boolean isLabel() {
		return type == Type.LABEL;
	}

	/*
	 * (non-Javadoc)
	 * @see CGInterface#isParam()
	 */
	@Override
	public boolean isParam() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see CGInterface#isPointer()
	 */
	@Override
	public boolean isPointer() {
		return type == Type.POINTER;
	}

	/*
	 * (non-Javadoc)
	 * @see CGInterface#isVarInteger()
	 */
	@Override
	public boolean isVarInteger() {
		return type == Type.INT;
	}

	/**
	 * set the address of the operand.
	 *
	 * @param off
	 *            int
	 */
	public void setOffset(int off) {
		offset = off;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (type != null)
			return type.toString();
		else
			return "";
	}

}
