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
import ui.UserInterface;

/**
 * The Class ChangeSelection.
 */
public class ChangeSelection implements Command {

	/** The receiver. */
	private Engine receiver;

	/** The invoker. */
	private UserInterface invoker;

	/**
	 * Instantiates a new change selection.
	 *
	 * @param receiver
	 *            the receiver
	 * @param invoker
	 *            the invoker
	 */
	public ChangeSelection(Engine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker = invoker;
	}

	/*
	 * (non-Javadoc)
	 * @see command.Command#execute()
	 */
	@Override
	public void execute() {
		int[] selection = invoker.promptSelection();
		receiver.changeSelection(selection[0], selection[1]);
	}

}
