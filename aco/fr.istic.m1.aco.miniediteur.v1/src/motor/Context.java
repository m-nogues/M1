package motor;

/**
 * The Context.
 */
public class Context {

	private static Context INSTANCE = new Context();

	public static Context getInstance() {
		return INSTANCE;
	}

	/** The clip board. */
	private StringBuffer clipBoard;

	private StringBuffer selected;

	/** The text. */
	private StringBuffer text;

	private Context() {
		clipBoard = new StringBuffer();
		selected = new StringBuffer();
		text = new StringBuffer();
	}

	public StringBuffer getClipBoard() {
		return clipBoard;
	}

	public StringBuffer getSelected() {
		return selected;
	}

	public StringBuffer getText() {
		return text;
	}

	public void setClipBoard(String clipBoard) {
		this.clipBoard.delete(0, this.clipBoard.length());
		this.clipBoard.append(clipBoard);
	}

	public void setSelected(String selected) {
		this.selected.delete(0, this.selected.length());
		this.selected.append(selected);
	}

	public void setText(String text) {
		this.text.delete(0, this.text.length());
		this.text.append(text);
	}
}