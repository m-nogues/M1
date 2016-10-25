/**
 * Implementation of a generic type object. The type is represented by the type
 * expression: T(t1, ...,tn). An attribute size was added for compiler needs.
 */
public class Type {

	/** The Constant INT. */
	static final public Type INT = new Type("INT", 4);

	/** The Constant I_CONST. */
	static final public Type I_CONST = new Type("I_CONST", 4);

	/** The Constant LABEL. */
	static final public Type LABEL = new Type("LABEL", -1);

	/** The Constant POINTER. */
	static final public Type POINTER = new Type("POINTER", 4);

	/** The Constant ERROR. */
	static final public Type ERROR = new Type("ERROR", -1);

	/** The Constant VOID. */
	static final public Type VOID = new Type("VOID", 0);

	/** The size. */
	protected int size;

	/** The name. */
	protected String name;

	/**
	 * Constructor for a simple type name().
	 *
	 * @param name
	 *            the name
	 * @param size
	 *            the size
	 */
	public Type(String name, int size) {
		this.name = name;
		this.size = size;
	}

	/**
	 * Returns true iff the given parameter is an object with the same type as
	 * this one.
	 *
	 * @param o
	 *            object to be compared with.
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Type))
			return false;
		Type t = (Type) o;
		return name.equals(t.name) && size == t.size;
	}

	/**
	 * Returns the size in bytes.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return name.hashCode() * 7 + size * 13;
	}

	/**
	 * Tests if two types are compatible. For simple types, compatibility is
	 * equality of types. Sub-types (e.g. function types) must override this
	 * function accordingly.
	 *
	 * @param t
	 *            the t
	 * @return true, if is compatible
	 */
	public boolean isCompatible(Type t) {
		return equals(t);
	}

	/**
	 * Returns a printable representation of the type expression. Must be
	 * overridden by subclasses.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return name;
	}

}
