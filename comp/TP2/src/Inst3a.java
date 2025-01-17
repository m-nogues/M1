import java.io.FileWriter;
import java.io.IOException;

/**
 * A three address code (TAC) instruction is composed of an operation code and
 * three operands a,b,c; the operands are objects from the Operand3a class. The
 * operation codes form an enumeration (TAC). The meaning of the codes is as
 * follows:
 * <ul>
 * <li>ADD a = b + c
 * <li>SUB a = b - c
 * <li>MUL a = b * c
 * <li>DIV a = b / c
 * <li>NEG a = -b
 * <li>COPY a = b
 * <li>GOTO goto a
 * <li>IFZ ifz a goto b
 * <li>IFNZ ifnz a goto b
 * <li>ARG arg a
 * <li>CALL a = call b ou call b
 * <li>RETURN return a
 * <li>TABVAR a = b[c]
 * <li>VARTAB a[b] = c
 * <li>LABEL label a
 * <li>VAR var a
 * <li>BEGINFUNC
 * <li>ENDFUNC
 * </ul>
 * <p>
 * When an operand is missing, it must be replaced by the null object in the
 * constructor. For example: i = Inst3a(LABEL, a, null, null)
 *
 * @author MLB
 */
public class Inst3a {

	/**
	 * Enumeration of all three-address code operators; most of them have
	 * self-explaining names.
	 */
	public enum TAC {

		/** The add. */
		ADD,
		/** The sub. */
		SUB,
		/** The mul. */
		MUL,
		/** The div. */
		DIV,
		// a = b op c
		/** The neg. */
		NEG,
		// a = -b
		/** The copy. */
		COPY,
		// a = b
		/** The goto. */
		GOTO,
		// goto a
		/** The ifz. */
		IFZ,
		// ifz a goto b
		/** The ifnz. */
		IFNZ,
		// ifnz a goto b
		/** The arg. */
		ARG,
		// arg a
		/** The call. */
		CALL,
		// a = call b ou call b
		/** The return. */
		RETURN,
		// return a
		/** The tabvar. */
		TABVAR,
		// a = b[c]
		/** The vartab. */
		VARTAB,
		// a[b] = c
		/** The label. */
		LABEL,
		// label a
		/** The var. */
		VAR,
		// var a
		/** The beginfunc. */
		BEGINFUNC,
		/** The endfunc. */
		ENDFUNC
	}

	/** The code op. */
	private TAC code_op;

	/** The op a. */
	private Operand3a op_a;

	/** The op b. */
	private Operand3a op_b;

	/** The op c. */
	private Operand3a op_c;

	/**
	 * Constructor.
	 *
	 * @param op
	 *            : TAC operator
	 * @param a
	 *            : Operand3a
	 * @param b
	 *            : Operand3a or null
	 * @param c
	 *            : Operand3a or null
	 */
	public Inst3a(TAC op, Operand3a a, Operand3a b, Operand3a c) {
		code_op = op;
		op_a = a;
		op_b = b;
		op_c = c;
	}

	/**
	 * Self explained.
	 *
	 * @return Operand3a
	 */
	public Operand3a getA() {
		return op_a;
	}

	/**
	 * Self explained.
	 *
	 * @return Operand3a
	 */
	public Operand3a getB() {
		return op_b;
	}

	/**
	 * Self explained.
	 *
	 * @return Operand3a
	 */
	public Operand3a getC() {
		return op_c;
	}

	/**
	 * Returns the operation code.
	 *
	 * @return int
	 */
	public TAC getOp() {
		return code_op;
	}

	/**
	 * Prints the.
	 */
	public void print() {
		System.out.println(toString());
	}

	/**
	 * Prints the in file.
	 *
	 * @param fo
	 *            the fo
	 */
	public void print_in_file(FileWriter fo) {
		try {
			fo.write(toString() + "\n");
		} catch (IOException exc) {}
	}

	/**
	 * method for robustifying the toString method. If an operand is missing
	 * (null) a "####" is returned in place of the name. Otherwise, the name, as
	 * defined by the method getName3a is returned.
	 *
	 * @param x
	 *            : the operand
	 * @return : a printable string
	 */
	private String the_Name(Operand3a x) {
		if (x == null)
			return "####";
		else
			return x.getName3a();
	}

	/**
	 * Prints a TAC instruction. This method is put here for debug purpose only.
	 * TAC with type information is just an intermediate representation of the
	 * program. The printed form lack some information to be completely
	 * equivalent to the source program. <br>
	 * The toString method is robustified for debug purposes. If an operand is
	 * missing (null) a "####" is returned in place of the name. Otherwise, the
	 * name, as defined by the method <b>getName3a</b>, is returned.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		switch (code_op) {
			case ADD:
				return " " + the_Name(op_a) + " = " + the_Name(op_b) + " + " + the_Name(op_c);
			case SUB:
				return " " + the_Name(op_a) + " = " + the_Name(op_b) + " - " + the_Name(op_c);
			case MUL:
				return " " + the_Name(op_a) + " = " + the_Name(op_b) + " * " + the_Name(op_c);
			case DIV:
				return " " + the_Name(op_a) + " = " + the_Name(op_b) + " / " + the_Name(op_c);
			case NEG:
				return " " + the_Name(op_a) + " = " + " - " + the_Name(op_b);
			case COPY:
				return " " + the_Name(op_a) + " = " + the_Name(op_b);
			case GOTO:
				return " goto " + the_Name(op_a);

			case IFZ:
				return " ifz " + the_Name(op_a) + " goto " + the_Name(op_b);
			case IFNZ:
				return " ifnz " + the_Name(op_a) + " goto " + the_Name(op_b);
			case ARG:
				return " arg " + the_Name(op_a);
			case CALL:
				if (op_a != null)
					return " " + the_Name(op_a) + " =  call " + the_Name(op_b);
				else
					return " call " + the_Name(op_b);
			case RETURN:
				return " return " + the_Name(op_a);
			case LABEL:
				return " label " + the_Name(op_a);
			case VAR:
				return " var " + the_Name(op_a);
			case BEGINFUNC:
				return " beginfunc ";
			case ENDFUNC:
				return " endfunc ";
			case TABVAR:
				return " " + the_Name(op_a) + " = " + the_Name(op_b) + " [ " + the_Name(op_c) + " ]";

			case VARTAB:
				return " " + the_Name(op_a) + " [ " + the_Name(op_b) + " ] = " + the_Name(op_c);
			default:
				return " unknown TAC opcode ";
		}
	}
} // en class Inst3a
