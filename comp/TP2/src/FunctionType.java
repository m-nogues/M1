
/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Maël Nogues mael.nogues@outlook.com
 */
import java.util.LinkedList;
import java.util.List;

/**
 * The type of a function is composed of a return type plus a list of types for
 * its arguments (empty if none). It also has a field "prototype" indicating
 * whether this represents a prototype declaration (without body) or a function
 * definition (with body).
 *
 * @author MLB
 */
public class FunctionType extends Type {
	/**
	 * True if this is a prototype declaration, false if this is a function
	 * definition.
	 */
	public boolean prototype;

	/** The return type. */
	private Type returnType;

	/** The arguments. */
	private List<Type> arguments = new LinkedList<>();

	/**
	 * Creates a new function type indifferent of prototype/function status
	 * (used to check function calls).
	 *
	 * @param returnType
	 *            Return type of the function
	 */
	public FunctionType(Type returnType) {
		super("FUNC", -1);
		this.returnType = returnType;
		prototype = true; // meaningless, arbitrary choice
	}

	/**
	 * Creates a new function type with the given return type and whether it is
	 * a prototype declaration or a function definition.
	 *
	 * @param returnType
	 *            Return type of the function
	 * @param prototype
	 *            True iff this is a prototype declaration (instead of a
	 *            function definition)
	 */
	public FunctionType(Type returnType, boolean prototype) {
		super("FUNC", -1);
		this.returnType = returnType;
		this.prototype = prototype;
	}

	/**
	 * Extend the arity of this function type, from f(t1, ..., tn) to f(t1, ...,
	 * tn, t). Use this when iteratively constructing the entire type of a
	 * function call.
	 *
	 * @param t
	 *            the new argument type to be added.
	 */
	public void extend(Type t) {
		arguments.add(t);
	}

	/**
	 * Returns the list of argument types of this function (without the return
	 * type).
	 *
	 * @return the list of argument types of this function.
	 */
	public List<Type> getArguments() {
		// Defensive copy to avoid user from mutating the list
		return new LinkedList<>(arguments);
	}

	/**
	 * Returns the return type of this function (it can be VOID).
	 *
	 * @return the return type of this function (it can be VOID).
	 */
	public Type getReturnType() {
		return returnType;
	}

	/*
	 * (non-Javadoc)
	 * @see Type#isCompatible(Type)
	 */
	@Override
	public boolean isCompatible(Type t) {
		if (!(t instanceof FunctionType))
			return false;
		FunctionType ft = (FunctionType) t;
		return returnType.equals(ft.returnType) && arguments.equals(ft.arguments);
	}

	/*
	 * (non-Javadoc)
	 * @see Type#toString()
	 */
	@Override
	public String toString() {
		return name + arguments + " : " + returnType;
	}

}
