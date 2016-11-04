/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Ma�l Nogues mael.nogues@outlook.com
 */
package engine;

/**
 * The Class EngineImpl.
 */
public class EngineImpl implements Engine {

	/** The buffer. */
	private StringBuffer buffer = new StringBuffer();

	/** The clipboard. */
	private String clipboard = new String();

	/** The selection. */
	private int[] selection = new int[] { 0, 0 };

	/*
	 * (non-Javadoc)
	 * @see engine.Engine#changeSelection(int, int)
	 */
	@Override
	public void changeSelection(int start, int end) {
		if (start > end) {
			int tmp = start;
			start = end;
			end = tmp;
		}

		if (start < 0)
			start = 0;

		if (start > buffer.length())
			start = buffer.length();

		if (end < 0)
			end = 0;

		if (end > buffer.length())
			end = buffer.length();

		selection = new int[] { start, end };
	}

	/*
	 * (non-Javadoc)
	 * @see engine.Engine#copy()
	 */
	@Override
	public void copy() {
		clipboard = buffer.substring(selection[0], selection[1]);
	}

	/*
	 * (non-Javadoc)
	 * @see engine.Engine#cut()
	 */
	@Override
	public void cut() {
		copy();
		delete();
		setCursor(selection[0]);
	}

	/**
	 * Delete.
	 */
	private void delete() {
		buffer.delete(selection[0], selection[1]);
	}

	/*
	 * (non-Javadoc)
	 * @see engine.Engine#getClipboard()
	 */
	@Override
	public String getClipboard() {
		return new String(clipboard);
	}

	/*
	 * (non-Javadoc)
	 * @see engine.Engine#getContent()
	 */
	@Override
	public String getContent() {
		return buffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see engine.Engine#getSelection()
	 */
	@Override
	public int[] getSelection() {
		return new int[] { selection[0], selection[1] };
	}

	/*
	 * (non-Javadoc)
	 * @see engine.Engine#insertText(java.lang.String)
	 */
	@Override
	public void insertText(String text) {
		delete();
		buffer.insert(selection[0], text);
		setCursor(selection[0] + text.length());
	}

	/*
	 * (non-Javadoc)
	 * @see engine.Engine#paste()
	 */
	@Override
	public void paste() {
		insertText(clipboard);
	}

	/**
	 * Sets the cursor.
	 *
	 * @param i
	 *            the new cursor
	 */
	private void setCursor(int i) {
		changeSelection(i, i);
	}

}
