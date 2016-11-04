/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Maël Nogues mael.nogues@outlook.com
 */
package command;

import engine.Engine;

/**
 * The Class Copy.
 */
public class Copy implements Command {

	/** The receiver. */
	private Engine receiver;

	/**
	 * Instantiates a new copy.
	 *
	 * @param receiver
	 *            the receiver
	 */
	public Copy(Engine receiver) {
		this.receiver = receiver;
	}

	/*
	 * (non-Javadoc)
	 * @see command.Command#execute()
	 */
	@Override
	public void execute() {
		receiver.copy();
	}

}
