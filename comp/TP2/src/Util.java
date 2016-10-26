import java.util.logging.Logger;

import org.antlr.runtime.tree.CommonTree;

/**
 * Utility methods for generating error and warning messages, as well as
 * debugging settings. This class contains a status field which is set in the
 * presence of errors and warnings. It also contains a field indicating the
 * current debugging level. All messages here are emitted using the standard
 * error (stderr) or a logger (if the -debug option is used).
 */
public class Util {

	/** The vsl filename. */
	static public String vslFilename = "<undefined>";

	/** The status. */
	static public int status = 0; // 0 - no errors nor warnings; 1 - errors
									// detected; 2 - warnings emitted; 3 -
									// errors and warnings

	/** The log. */
	static public Logger log = Logger.getLogger("comp");

	/**
	 * Auxiliary method to retrieve the column of a token, without throwing
	 * exceptions. If the token is null, returns -1.
	 *
	 * @param t
	 *            the t
	 * @return the token char position in line
	 */
	static private int getTokenCharPositionInLine(CommonTree t) {
		if (t == null)
			return -1;
		else
			return t.getCharPositionInLine();
	}

	/**
	 * Auxiliary method to retrieve the line number of a token, without throwing
	 * exceptions. If the token is null, returns -1.
	 *
	 * @param t
	 *            the t
	 * @return the token line
	 */
	static private int getTokenLine(CommonTree t) {
		if (t == null)
			return -1;
		else
			return t.getLine();
	}

	/**
	 * Prints the error.
	 *
	 * @param token
	 *            the token
	 * @param msg
	 *            the msg
	 */
	static public void printError(CommonTree token, String msg) {
		printMsg(1, getTokenLine(token), getTokenCharPositionInLine(token), "error", msg);
	}

	/**
	 * Prints the error.
	 *
	 * @param line
	 *            the line
	 * @param col
	 *            the col
	 * @param msg
	 *            the msg
	 */
	static public void printError(int line, int col, String msg) {
		printMsg(1, line, col, "error", msg);
	}

	/**
	 * Prints the error.
	 *
	 * @param msg
	 *            the msg
	 */
	static public void printError(String msg) {
		printMsg(1, "error", msg);
	}

	/**
	 * Prints the msg.
	 *
	 * @param newStatus
	 *            the new status
	 * @param line
	 *            the line
	 * @param col
	 *            the col
	 * @param type
	 *            the type
	 * @param msg
	 *            the msg
	 */
	static private void printMsg(int newStatus, int line, int col, String type, String msg) {
		status |= newStatus;
		System.err.println(vslFilename + ":" + line + ": " + type + ": " + msg);
	}

	/**
	 * Prints the msg.
	 *
	 * @param newStatus
	 *            the new status
	 * @param type
	 *            the type
	 * @param msg
	 *            the msg
	 */
	static private void printMsg(int newStatus, String type, String msg) {
		status |= newStatus;
		System.err.println(vslFilename + ": " + type + ": " + msg);
	}

	/**
	 * Prints the warning.
	 *
	 * @param token
	 *            the token
	 * @param msg
	 *            the msg
	 */
	static public void printWarning(CommonTree token, String msg) {
		printMsg(2, getTokenLine(token), getTokenCharPositionInLine(token), "warning", msg);
	}

	/**
	 * Prints the warning.
	 *
	 * @param line
	 *            the line
	 * @param col
	 *            the col
	 * @param msg
	 *            the msg
	 */
	static public void printWarning(int line, int col, String msg) {
		printMsg(2, line, col, "warning", msg);
	}

	/**
	 * Prints the warning.
	 *
	 * @param msg
	 *            the msg
	 */
	static public void printWarning(String msg) {
		printMsg(2, "warning", msg);
	}

}
