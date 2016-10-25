/**
 * Type for arrays (currently, only integer arrays). Includes the underlying
 * array type and the length of the array (necessary to compute its size in
 * memory).
 *
 * @author MLB
 */

class ArrayType extends Type {

	/** The length. */
	private int length;

	/** The type. */
	private Type type;

	/**
	 * Instantiates a new array type.
	 *
	 * @param t
	 *            the t
	 * @param l
	 *            the l
	 */
	public ArrayType(Type t, int l) {
		super("ARRAY", 4 * l);
		type = t;
		length = l;
	}

	/**
	 * Returns the dimension of this array (used only for code generation).
	 *
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/*
	 * (non-Javadoc)
	 * @see Type#isCompatible(Type)
	 */
	@Override
	public boolean isCompatible(Type t) {
		if (this == t)
			return true;
		if (!(t instanceof ArrayType))
			return false;
		ArrayType at = (ArrayType) t;
		return name.equals(at.name) && type.equals(at.type);
	}

	/*
	 * (non-Javadoc)
	 * @see Type#toString()
	 */
	@Override
	public String toString() {
		return name + "(" + type + ")";
	}

}
