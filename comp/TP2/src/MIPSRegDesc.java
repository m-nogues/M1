
/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Maël Nogues mael.nogues@outlook.com
 */
import java.io.PrintStream;

/**
 * Register descriptor for assembler code generation.
 *
 * @author Bennet, MLB and others
 */
public class MIPSRegDesc implements MIPSCGConstants {

	/** The memory. */
	private Operand3a memory;

	/** The modified. */
	// memory associated with register
	private boolean modified;

	/** The reg number. */
	// true if the content of memory is different from those of the register
	private int regNumber;

	/**
	 * Instantiates a new MIPS reg desc.
	 *
	 * @param regNumber
	 *            the reg number
	 */
	public MIPSRegDesc(int regNumber) {
		memory = null;
		modified = false;
		this.regNumber = regNumber;
	}

	/**
	 * if cleared, the register is no more associated with a variable.
	 */
	public void clear() {
		memory = null;
		modified = false;
	}

	/**
	 * Gets the memory.
	 *
	 * @return the memory
	 */
	public Operand3a getMemory() {
		return memory;
	}

	/**
	 * Gets the modif.
	 *
	 * @return the modif
	 */
	public boolean getModif() {
		return modified;
	}

	/**
	 * Gets the reg number.
	 *
	 * @return the reg number
	 */
	public int getRegNumber() {
		return regNumber;
	}

	/**
	 * set the attributes.
	 *
	 * @param mem
	 *            the mem
	 * @param mod
	 *            the mod
	 */
	public void set(Operand3a mem, boolean mod) {
		memory = mem;
		modified = mod;
	}

	/**
	 * dump the register content in the associated memory, if different.
	 *
	 * @param n
	 *            the n
	 * @param out
	 *            the out
	 */
	public void spill(int n, PrintStream out) {
		if (modified) {
			out.println(R_INDENT + "sw  $" + n + "," + memory.getOffset() + "($" + R_STACK + ")");
			modified = false;
		}
	}

}
