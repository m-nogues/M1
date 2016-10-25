
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
 * Manages register allocation during assembler code generation.
 *
 * @author Bennet, MLB and others
 */

public class MIPSRegAlloc implements MIPSCGConstants {

	/** The Constant S_eq_S_op_S. */
	private static final int R_eq_R_op_R = 0, S_eq_R_op_R = 1, R_eq_S_op_R = 2, S_eq_S_op_R = 3, R_eq_R_op_S = 4,
			S_eq_R_op_S = 5, R_eq_S_op_S = 6, S_eq_S_op_S = 7;

	/** The registers. */
	private MIPSRegDesc[] registers;

	/** The out. */
	private PrintStream out;

	/**
	 * Instantiates a new MIPS reg alloc.
	 *
	 * @param out
	 *            the out
	 */
	public MIPSRegAlloc(PrintStream out) {
		this.out = out;
		registers = new MIPSRegDesc[R_MAX];
		for (int i = 0; i < R_MAX; i++)
			registers[i] = new MIPSRegDesc(i);
	}

	/**
	 * Allocate one register.
	 *
	 * @return the MIPS reg desc
	 */
	public MIPSRegDesc allocateOneRegister() {
		MIPSRegDesc reg;

		reg = this.getEmptyRegister();
		if (reg != null)
			return reg;
		reg = this.getNotModifiedRegister();
		if (reg != null)
			return reg;
		reg = registers[R_GEN];
		spillOne(R_GEN);
		return reg;
	}

	/**
	 * Allocate one register.
	 *
	 * @param r1
	 *            the r 1
	 * @return the MIPS reg desc
	 */
	// allocate a register different from r1
	public MIPSRegDesc allocateOneRegister(int r1) {
		MIPSRegDesc reg;

		reg = this.getEmptyRegister(r1);
		if (reg != null)
			return reg;

		reg = this.getNotModifiedRegister(r1);
		if (reg != null)
			return reg;

		for (int r = R_GEN; r < R_MAX; r++)
			if (r != r1) {
				reg = registers[r];
				spillOne(r);
				return reg;
			}
		return null;
	}

	/**
	 * Allocate one register.
	 *
	 * @param r1
	 *            the r 1
	 * @param r2
	 *            the r 2
	 * @return the MIPS reg desc
	 */
	// allocate a register different from r1 and r2
	public MIPSRegDesc allocateOneRegister(int r1, int r2) {
		MIPSRegDesc reg;

		reg = this.getEmptyRegister(r1, r2);
		if (reg != null)
			return reg;

		reg = this.getNotModifiedRegister(r1, r2);
		if (reg != null)
			return reg;

		for (int r = R_GEN; r < R_MAX; r++)
			if (r != r1 && r != r2) {
				reg = registers[r];
				spillOne(r);
				return reg;
			}
		return null;
	}

	/**
	 * Allocate three registers for a 3-addresses opcode.
	 *
	 * @param dest
	 *            the dest
	 * @param op1
	 *            the op 1
	 * @param op2
	 *            the op 2
	 * @return the MIPSCG triplet
	 */
	public MIPSCGTriplet<MIPSRegDesc> allocateRegisters(Operand3a dest, Operand3a op1, Operand3a op2) {
		// Each operand can be in two states:
		// - Already bound to a register (nothing to be done)
		// - Not bound to a register (we need to find one).
		// 2^3 cases have to be considered.

		MIPSRegDesc destReg = isInRegister(dest);
		MIPSRegDesc op1Reg = isInRegister(op1);
		MIPSRegDesc op2Reg = isInRegister(op2);
		int destInRegister = destReg != null ? 0 : 1;
		int op1InRegister = op1Reg != null ? 0 : 2;
		int op2InRegister = op2Reg != null ? 0 : 4;
		int situation = destInRegister + op1InRegister + op2InRegister;

		switch (situation) {
			case R_eq_R_op_R:
				// All three operands are already associated to registers
				break;
			case S_eq_R_op_R:
				if (!op1Reg.getModif()) {
					destReg = op1Reg;
					break;
				}
				if (!op2Reg.getModif()) {
					destReg = op2Reg;
					break;
				}
				destReg = this.allocateOneRegister(op1Reg.getRegNumber(), op2Reg.getRegNumber());
				break;
			case R_eq_R_op_S:
				op2Reg = destReg;
				this.loadRegisterMIPS(op2Reg, op2);
				break;
			case R_eq_S_op_R:
				op1Reg = destReg;
				this.loadRegisterMIPS(op1Reg, op1);
				break;
			case S_eq_R_op_S:
				op2Reg = this.allocateOneRegister(op1Reg.getRegNumber());
				this.loadRegisterMIPS(op2Reg, op2);
				destReg = op2Reg;
				break;
			case S_eq_S_op_R:
				op1Reg = this.allocateOneRegister(op2Reg.getRegNumber());
				this.loadRegisterMIPS(op1Reg, op1);
				destReg = op1Reg;
				break;
			case R_eq_S_op_S:
				op1Reg = destReg;
				this.loadRegisterMIPS(op1Reg, op1);
				op2Reg = this.allocateOneRegister(op1Reg.getRegNumber());
				this.loadRegisterMIPS(op2Reg, op2);
				break;
			case S_eq_S_op_S:
				destReg = this.allocateOneRegister();
				op2Reg = destReg;
				op1Reg = this.allocateOneRegister(destReg.getRegNumber());
				this.loadRegisterMIPS(op1Reg, op1);
				this.loadRegisterMIPS(destReg, op2);

				break;
		}

		// In all cases the destination register now contains a value associated
		// with dest operand, and it has been modified.
		destReg.set(dest, true);

		return new MIPSCGTriplet<>(destReg, op1Reg, op2Reg);
	}

	/**
	 * clear all descriptors.
	 */
	public void clearAll() {
		for (int i = R_RES; i < R_MAX; i++)
			registers[i].clear();
	}

	/**
	 * clear a descriptor.
	 *
	 * @param r
	 *            the r
	 */

	public void clearDescriptor(int r) {
		registers[r].clear();
	}

	/**
	 * flushAll: spill all registers and clear their descriptors.
	 */
	public void flushAll() {
		for (int i = R_RES; i < R_MAX; i++) {
			registers[i].spill(i, out);
			registers[i].clear();
		}
	}

	/**
	 * getAnother get a register to hold the second operand of a computation.
	 *
	 * @param op
	 *            the op
	 * @param cr
	 *            the cr
	 * @return the another
	 */
	public int getAnother(Operand3a op, int cr) {
		int r;
		// already in a register?
		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getMemory() == op && r != cr)
				return r;
		// find an empty register?
		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getMemory() == null && r != cr) {
				loadRegisterMIPS(r, op);
				return r;
			}
		// find an unmodified register?
		for (r = R_GEN; r < R_MAX; r++)
			if (!registers[r].getModif() && r != cr) {
				registers[r].clear();
				loadRegisterMIPS(r, op);
				return r;
			}
		// take a modified register different from cr
		for (r = R_GEN; r < R_MAX; r++)
			if (r != cr) {
				registers[r].spill(r, out);
				registers[r].clear();
				loadRegisterMIPS(r, op);
				return r;
			}
		return -1; // this should never happend
	}

	/**
	 * Gets the empty register.
	 *
	 * @return the empty register
	 */
	public MIPSRegDesc getEmptyRegister() {
		int r;

		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getMemory() == null)
				return registers[r];
		return null;
	}

	/**
	 * Gets the empty register.
	 *
	 * @param r1
	 *            the r 1
	 * @return the empty register
	 */
	// get an empty register different from r1 if it exists
	public MIPSRegDesc getEmptyRegister(int r1) {
		int r;

		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getMemory() == null && r != r1)
				return registers[r];
		return null;
	}

	/**
	 * Gets the empty register.
	 *
	 * @param r1
	 *            the r 1
	 * @param r2
	 *            the r 2
	 * @return the empty register
	 */
	// get an empty register different from r1 and r2 if it exists
	public MIPSRegDesc getEmptyRegister(int r1, int r2) {
		int r;

		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getMemory() == null && r != r1 && r != r2)
				return registers[r];
		return null;
	}

	/**
	 * getErasable gets a register that will hold an operand and be overwritten
	 * by the result.
	 *
	 * @param op
	 *            the op
	 * @return the erasable
	 */
	public int getErasable(Operand3a op) {
		/*
		 * get a register to hold the result of a computation a := b op c This
		 * must initially hold c and will be overwritten with a. If c is already
		 * in a register we use that, spilling it if necessary. Otherwise we use
		 * in order of preference from: 1) an empty register; 2) an unmodified
		 * register; 3) a modified register. In the last case we spill the
		 * content of the register before it is used. If c is not in the given
		 * result register, we load it. Clearly, we must use only general
		 * purpose registers. Note that since c may be the same as b, we must
		 * update the address and register descriptors
		 */
		int r;
		// already in a register?
		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getMemory() == op) {
				registers[r].spill(r, out);
				return r;
			}
		// find an empty register?
		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getMemory() == null) {
				loadRegisterMIPS(r, op);
				return r;
			}
		// find an unmodified register?
		for (r = R_GEN; r < R_MAX; r++)
			if (!registers[r].getModif()) {
				registers[r].clear();
				loadRegisterMIPS(r, op);
				return r;
			}
		// take a modified register
		registers[R_GEN].spill(R_GEN, out);
		registers[R_GEN].clear();
		loadRegisterMIPS(R_GEN, op);
		return R_GEN;
	}

	/**
	 * Gets the not modified register.
	 *
	 * @return the not modified register
	 */
	public MIPSRegDesc getNotModifiedRegister() {
		int r;

		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getModif() == false)
				return registers[r];
		return null;
	}

	/**
	 * Gets the not modified register.
	 *
	 * @param r1
	 *            the r 1
	 * @return the not modified register
	 */
	// get a not modified register different from r1 if it exists
	public MIPSRegDesc getNotModifiedRegister(int r1) {
		int r;

		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getModif() == false && r != r1)
				return registers[r];
		return null;
	}

	/**
	 * Gets the not modified register.
	 *
	 * @param r1
	 *            the r 1
	 * @param r2
	 *            the r 2
	 * @return the not modified register
	 */
	// get a not modified register different from r1 and r2 if it exists
	public MIPSRegDesc getNotModifiedRegister(int r1, int r2) {
		int r;

		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getModif() == false && r != r1 && r != r2)
				return registers[r];
		return null;
	}

	/**
	 * get some register: get a register without associating it with some place
	 * in memory. We use the same policy as above to select it
	 *
	 * @param cr
	 *            the cr
	 * @return the some
	 */

	public int getSome(int cr) {
		int r;
		// find an empty register?
		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getMemory() == null)
				return r;
		// find an unmodified register?
		for (r = R_GEN; r < R_MAX; r++)
			if (!registers[r].getModif() && r != cr) {
				registers[r].clear();
				return r;
			}
		// take a modified register (necessarely not cr as this one is newly
		// loaded)
		registers[R_GEN].spill(R_GEN, out);
		registers[R_GEN].clear();
		return R_GEN;
	}

	/**
	 * Checks if is in register.
	 *
	 * @param op
	 *            the op
	 * @return the MIPS reg desc
	 */
	public MIPSRegDesc isInRegister(Operand3a op) {
		int r = search(op);
		if (r == -1)
			return null;
		else
			return registers[r];
	}

	/**
	 * load a value in a register. The register descriptor is updated
	 * accordingly
	 *
	 * @param r
	 *            the r
	 * @param op
	 *            the op
	 */

	public void loadRegisterMIPS(int r, Operand3a op) {
		// look if it is in a register
		for (int s = 0; s < R_MAX; s++)
			if (registers[s].getMemory() == op) {
				out.println(R_INDENT + "move  $" + r + ",$" + s);
				setDescriptor(r, op, registers[s].getModif());
				return;
			}

		// not in a register. Load appropriately with type
		if (op.isConstInteger())
			out.println(R_INDENT + "li   $" + r + ", " + op.getName3a());
		else if (op.isVarInteger())
			out.println(R_INDENT + "lw  $" + r + "," + op.getOffset() + "($" + R_STACK + ")");
		else if (op.isPointer())
			out.println(R_INDENT + "lw  $" + r + "," + op.getOffset() + "($" + R_STACK + ")");
		else if (op.isLabel()) {
			// special case of print string
			out.println(R_INDENT + "lui   $" + r + ",%hi(" + op.getName3a() + ")");
			out.println(R_INDENT + "addiu   $" + r + ",$" + r + ",%lo(" + op.getName3a() + ")");
		} else
			System.out.println("\n loadRegister: unknown case");
		registers[r].set(op, R_UNMODIFIED);
	}

	/**
	 * Load register MIPS.
	 *
	 * @param r
	 *            the r
	 * @param op
	 *            the op
	 */
	public void loadRegisterMIPS(MIPSRegDesc r, Operand3a op) {
		this.loadRegisterMIPS(r.getRegNumber(), op);
	}

	/**
	 * search for a symbol in a register, return -1 if not found.
	 *
	 * @param mem
	 *            the mem
	 * @return the int
	 */
	public int search(Operand3a mem) {
		int r;
		for (r = R_GEN; r < R_MAX; r++)
			if (registers[r].getMemory() == mem)
				break;
		if (r < R_MAX)
			return r;
		else
			return -1;
	}

	/**
	 * insert a descriptor entry for the given name.
	 *
	 * @param r
	 *            the r
	 * @param mem
	 *            the mem
	 * @param mod
	 *            the mod
	 */
	public void setDescriptor(int r, Operand3a mem, boolean mod) {
		// check if it is already in a register
		for (int re = R_GEN; re < R_MAX; re++)
			if (registers[re].getMemory() == mem)
				registers[re].clear();
		registers[r].set(mem, mod);
	}

	/**
	 * spilling of all registers. Values are dumped in memory if necessary
	 */
	public void spillAll() {
		for (int i = R_RES; i < R_MAX; i++)
			registers[i].spill(i, out);
	}

	/**
	 * spill a register.
	 *
	 * @param r
	 *            the r
	 */
	public void spillOne(int r) {
		registers[r].spill(r, out);
	}

}
