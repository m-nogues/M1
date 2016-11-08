/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Maël Nogues mael.nogues@outlook.com
 */
package engine;

/**
 * The Interface Engine.
 */
public interface Engine {

	/**
	 * Change selection.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
	void changeSelection(int i, int j);

	/**
	 * Copy.
	 */
	void copy();

	/**
	 * Cut.
	 */
	void cut();

	/**
	 * Gets the clipboard.
	 *
	 * @return the clipboard
	 */
	String getClipboard();

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	String getContent();

	/**
	 * Insert text.
	 *
	 * @param toInsert
	 *            the to insert
	 */
	void insertText(String toInsert);

	/**
	 * Paste.
	 */
	void paste();

	/**
	 * Gets the selection start.
	 *
	 * @return the selection start
	 */
	int getSelectionStart();

	/**
	 * Gets the selection end.
	 *
	 * @return the selection end
	 */
	int getSelectionEnd();

}