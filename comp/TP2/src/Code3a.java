import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * A list of TAC statements (Inst3a). Basically, 3 address code is a list of
 * Inst3a objects. However, in any compiler, it is necessary to allocate some
 * memory for constants of variable size. The most obvious example are string
 * constants. In VSL it is the only constants which need memory allocation. The
 * other constants are integers which are operands in arithmetic expression and
 * have immediate addresses. Consequently, Code3a has two attributes: a list of
 * 3a instructions and a list of Data3a for storing the string constants.
 *
 * @author MLB
 **/

public class Code3a {

	/** The the code. */
	// private static boolean no_error = true;
	protected List<Inst3a> the_code;

	/** The the data. */
	protected List<Data3a> the_data;

	/**
	 * Creates an empty list of TAC.
	 */
	public Code3a() {
		the_code = new LinkedList<>();
		the_data = new LinkedList<>();
	}

	/**
	 * Creates a list of TAC with only one statement.
	 *
	 * @param i
	 *            the i
	 */
	public Code3a(Inst3a i) {
		the_code = new LinkedList<>();
		the_data = new LinkedList<>();
		the_code.add(i);
	}

	/**
	 * Appends two codes. Lists of instructions are concatenated. The two lists
	 * of string constants used in each code are also concatenated.
	 *
	 * @param c
	 *            the c
	 */
	public void append(Code3a c) {
		if (c != null) {
			the_code.addAll(c.the_code);
			the_data.addAll(c.the_data);
		}
	}

	/**
	 * Add a statement at the end of the list of TAC.
	 *
	 * @param inst
	 *            the inst
	 */
	public void append(Inst3a inst) {
		the_code.add(inst);
	}

	/**
	 * Add a Data3a for storing a string constant.
	 *
	 * @param dat
	 *            the dat
	 */
	public void appendData(Data3a dat) {
		the_data.add(dat);
	}

	/**
	 * To be used by the (machine) code generator.
	 *
	 * @return List<Inst3a> the 3a code
	 */
	public List<Inst3a> getCode() {
		return the_code;
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return the_code.isEmpty();
	}

	/**
	 * Print the 3A code.
	 */
	public void print() {
		int i;
		for (i = 0; i < the_code.size(); i++)
			the_code.get(i).print();
		// print the data
		for (i = 0; i < the_data.size(); i++)
			the_data.get(i).print();
	}

	/**
	 * Print the 3A code in a file.
	 *
	 * @param fo
	 *            the fo
	 */
	public void print_in_file(FileWriter fo) {
		int i;
		for (i = 0; i < the_code.size(); i++)
			the_code.get(i).print_in_file(fo);

	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i;
		for (i = 0; i < the_code.size(); i++)
			sb.append(the_code.get(i) + "\n");
		// print the data
		for (i = 0; i < the_data.size(); i++)
			sb.append(the_data.get(i) + "\n");
		return sb.toString();
	}

}
