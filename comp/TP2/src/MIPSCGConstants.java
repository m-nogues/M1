/**
 * Constants used for MIPS code generation only.
 */
public interface MIPSCGConstants {

	/** The word size. */
	public int WORD_SIZE = 4; /* 32 bits machine */

	/**
	 * Constants for assembly code generation. Essentially register IDs This is
	 * architecture specific.
	 */

	static String R_INDENT = "       ";

	/** The r zero. */
	static int R_ZERO = 0; /* Constant zero */

	/** The r res. */
	static int R_RES = 2; /* Result reg and last reserved */

	/** The r arg1. */
	static int R_ARG1 = 4;

	/** The r arg2. */
	static int R_ARG2 = 5;

	/** The r arg3. */
	static int R_ARG3 = 6;

	/** The r arg4. */
	static int R_ARG4 = 7;

	/** The r gen. */
	static int R_GEN = 8; /* First general purpose register */

	/** The r max. */
	static int R_MAX = 24; /* 24 regs */

	// $25 is reserved for dynamic link

	/** The r gp. */
	static int R_GP = 28;

	/** The r stack. */
	static int R_STACK = 29; /* Stack pointer */

	/** The r ret. */
	static int R_RET = 31; /* Return address */

	/** The r modified. */
	static boolean R_MODIFIED = true; /* Entries for descriptors */

	/** The r unmodified. */
	static boolean R_UNMODIFIED = false;
}
