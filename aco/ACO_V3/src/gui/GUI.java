/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;

import commands.Redo;
import commands.Replay;
import commands.Start;
import commands.Stop;
import commands.Undo;
import editor.HistoryManager;
import editor.Observer;
import editor.Recorder;
import engine.Buffer;
import engine.EditionEngine;
import recordables.CopyRecordable;
import recordables.CutRecordable;
import recordables.DelTextRecordable;
import recordables.PasteRecordable;

/**
 * GUI base on Swing JtextArea.
 */
public final class GUI extends JFrame implements Observer, ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The paste. */
	private final JButton paste;

	/** The copy. */
	private final JButton copy;

	/** The cut. */
	private final JButton cut;

	/** The delete. */
	private final JButton delete;

	/** The start. */
	private final JButton start;

	/** The replay. */
	private final JButton replay;

	/** The stop. */
	private final JButton stop;

	/** The text area. */
	private final JTextArea textArea;

	/** The engine. */
	private final EditionEngine engine;

	/** The modification filter. */
	private final ModificationFilter modifFilter;

	/** The selection listener. */
	private final SelectionListener selectionListener;

	/** The recorder. */
	private final Recorder recorder;

	/** The undo. */
	private UndoButton undo;

	/** The redo. */
	private RedoButton redo;

	/**
	 * Instantiates a new graphical user interface.
	 *
	 * @param engine
	 *            the engine
	 * @param recorder
	 *            the recorder
	 * @param historyManager
	 *            the history manager
	 */
	public GUI(final EditionEngine engine, final Recorder recorder, HistoryManager historyManager) {
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("Engine is null");
		if (recorder == null)
			throw new IllegalArgumentException("Recorder is null");
		if (historyManager == null)
			throw new IllegalArgumentException("History manager is null");

		/* Treatment */
		this.engine = engine;
		this.recorder = recorder;

		modifFilter = new ModificationFilter(engine, recorder);
		selectionListener = new SelectionListener(engine, recorder);

		// set textArea and listener on the gui
		textArea = new TextAreaCustom(15, 80, engine);
		textArea.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		textArea.setFont(new Font("monospaced", Font.PLAIN, 14));
		textArea.addCaretListener(selectionListener);
		((AbstractDocument) textArea.getDocument()).setDocumentFilter(modifFilter);
		JScrollPane scrollingText = new JScrollPane(textArea);

		// the Jpanel
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(scrollingText, BorderLayout.CENTER);

		// the menuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 0));

		paste = new JButton();
		copy = new JButton();
		cut = new JButton();
		delete = new JButton();

		start = new StartButton();
		replay = new ReplayButton();
		stop = new StopButton();

		recorder.addObserver((Observer) start);
		recorder.addObserver((Observer) replay);
		recorder.addObserver((Observer) stop);

		undo = new UndoButton();
		redo = new RedoButton();

		historyManager.addObserver(undo);
		historyManager.addObserver(redo);

		// Sets the icons
		paste.setIcon(new ImageIcon(getClass().getResource("/icons/paste.png")));
		copy.setIcon(new ImageIcon(getClass().getResource("/icons/copy.png")));
		cut.setIcon(new ImageIcon(getClass().getResource("/icons/cut.png")));
		delete.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));
		start.setIcon(new ImageIcon(getClass().getResource("/icons/rec.png")));
		replay.setIcon(new ImageIcon(getClass().getResource("/icons/play.png")));
		stop.setIcon(new ImageIcon(getClass().getResource("/icons/stop.png")));
		undo.setIcon(new ImageIcon(getClass().getResource("/icons/undo.png")));
		redo.setIcon(new ImageIcon(getClass().getResource("/icons/redo.png")));

		// Sets the tips bubble
		paste.setToolTipText("Paste");
		copy.setToolTipText("Copy");
		cut.setToolTipText("Cut");
		delete.setToolTipText("Delete");
		start.setToolTipText("Rec");
		replay.setToolTipText("Replay");
		stop.setToolTipText("Stop");
		undo.setToolTipText("Undo");
		redo.setToolTipText("Redo");

		// Sets the listeners on the buttons
		paste.addActionListener(this);
		copy.addActionListener(this);
		cut.addActionListener(this);
		delete.addActionListener(this);
		start.addActionListener(this);
		replay.addActionListener(this);
		stop.addActionListener(this);
		undo.addActionListener(this);
		redo.addActionListener(this);

		paste.setFocusable(false);
		copy.setFocusable(false);
		cut.setFocusable(false);
		delete.setFocusable(false);
		start.setFocusable(false);
		replay.setFocusable(false);
		stop.setFocusable(false);
		undo.setFocusable(false);
		redo.setFocusable(false);

		// Adds all the buttons on the menuBar
		menuBar.add(copy);
		menuBar.add(cut);
		menuBar.add(paste);
		menuBar.add(delete);
		menuBar.add(start);
		menuBar.add(replay);
		menuBar.add(stop);
		menuBar.add(undo);
		menuBar.add(redo);

		// Disables the button on launch
		stop.setEnabled(false);
		start.setEnabled(false);

		// Sets the main window
		setContentPane(content);
		setJMenuBar(menuBar);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		pack();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public final void actionPerformed(ActionEvent e) {
		if (e.getSource() == paste)
			new PasteRecordable(engine, recorder).execute();
		else if (e.getSource() == copy)
			new CopyRecordable(engine, recorder).execute();
		else if (e.getSource() == cut)
			new CutRecordable(engine, recorder).execute();
		else if (e.getSource() == delete)
			new DelTextRecordable(engine, recorder).execute();
		else if (e.getSource() == start)
			new Start(recorder).execute();
		else if (e.getSource() == replay)
			new Replay(recorder).execute();
		else if (e.getSource() == stop)
			new Stop(recorder).execute();
		else if (e.getSource() == undo)
			new Undo(engine).execute();
		else if (e.getSource() == redo)
			new Redo(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observer#update(editor.Observable)
	 */
	@Override
	public void update(editor.Observable o) {
		/* Precondition */
		if (o == null)
			throw new IllegalArgumentException("o is null");
		if (!(o instanceof Buffer))
			throw new IllegalArgumentException("o not of type Buffer");

		/* Treatment */
		Buffer buffer = (Buffer) o;
		modifFilter.setActive(false);
		selectionListener.setActive(false);
		textArea.setText(buffer.getContent());
		textArea.setCaretPosition(buffer.getNewOffset());
		selectionListener.setActive(true);
		modifFilter.setActive(true);

	}
}