/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Maël Nogues mael.nogues@outlook.com
 */
package ui;

/**
 * The Interface UserInterface.
 */
public interface UserInterface {

	/**
	 * Prompt selection.
	 *
	 * @return the int[]
	 */
	int[] promptSelection();

	/**
	 * Prompt text to insert.
	 *
	 * @return the string
	 */
	String promptTextToInsert();

}
