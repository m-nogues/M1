/**
 * For MIPS code generator. Don't care.
 *
 * @param <T>
 *            the generic type
 */
public class MIPSCGTriplet<T> {

	/** The t 3. */
	T t1, t2, t3;

	/**
	 * Instantiates a new MIPSCG triplet.
	 *
	 * @param t1
	 *            the t 1
	 * @param t2
	 *            the t 2
	 * @param t3
	 *            the t 3
	 */
	public MIPSCGTriplet(T t1, T t2, T t3) {
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}

	/**
	 * Gets the 1.
	 *
	 * @return the 1
	 */
	public T get1() {
		return t1;
	}

	/**
	 * Gets the 2.
	 *
	 * @return the 2
	 */
	public T get2() {
		return t2;
	}

	/**
	 * Gets the 3.
	 *
	 * @return the 3
	 */
	public T get3() {
		return t3;
	}

}
