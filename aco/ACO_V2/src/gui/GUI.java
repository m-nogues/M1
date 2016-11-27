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

import commands.Copy;
import commands.Cut;
import commands.DeleteText;
import commands.Paste;
import editor.Observer;
import editor.Recorder;
import engine.Buffer;
import engine.EditionEngine;

/**
 * GUI base on Swing JtextArea
 */
public final class GUI extends JFrame implements Observer, ActionListener {
	private static final long serialVersionUID = 1L;

	// buttons declaration
	private final JButton	paste;
	private final JButton	copy;
	private final JButton	cut;
	private final JButton	delete;
	private final JButton	start;
	private final JButton	replay;
	private final JButton	stop;

	private final JTextArea textArea;
	// Edition Engine declaration
	private final EditionEngine engine;

	// Listener on textArea
	private final ModificationFilter	modifFilter;
	private final SelectionListener		selectionListener;

	private final Recorder recorder;

	/**
	 * @param engine
	 *            the edition engine
	 * @param recorder
	 *            the recorder
	 */
	public GUI(final EditionEngine engine, final Recorder recorder) {

		if (engine == null)
			throw new IllegalArgumentException("Null engine");
		if (recorder == null)
			throw new IllegalArgumentException("Null recorder");

		this.engine = engine;
		this.recorder = recorder;

		modifFilter = new ModificationFilter(engine);
		selectionListener = new SelectionListener(engine);

		// set textArea and listener on it
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

		// Icon set
		paste.setIcon(new ImageIcon(getClass().getResource("/icones/paste.png")));
		copy.setIcon(new ImageIcon(getClass().getResource("/icones/copy.png")));
		cut.setIcon(new ImageIcon(getClass().getResource("/icones/cut.png")));
		delete.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
		start.setIcon(new ImageIcon(getClass().getResource("/icones/rec.png")));
		replay.setIcon(new ImageIcon(getClass().getResource("/icones/play.png")));
		stop.setIcon(new ImageIcon(getClass().getResource("/icones/stop.png")));

		// Set bubble tips
		paste.setToolTipText("Paste");
		copy.setToolTipText("Copy");
		cut.setToolTipText("Cut");
		delete.setToolTipText("delete");
		start.setToolTipText("Rec");
		replay.setToolTipText("Replay");
		stop.setToolTipText("Stop");

		// Set listener on button
		paste.addActionListener(this);
		copy.addActionListener(this);
		cut.addActionListener(this);
		delete.addActionListener(this);
		start.addActionListener(this);
		replay.addActionListener(this);
		stop.addActionListener(this);

		paste.setFocusable(false);
		copy.setFocusable(false);
		cut.setFocusable(false);
		delete.setFocusable(false);
		start.setFocusable(false);
		replay.setFocusable(false);
		stop.setFocusable(false);

		// Add all buttons on menuBar
		menuBar.add(copy);
		menuBar.add(cut);
		menuBar.add(paste);
		menuBar.add(delete);
		menuBar.add(start);
		menuBar.add(replay);
		menuBar.add(stop);

		// Set Main window
		setContentPane(content);
		setJMenuBar(menuBar);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Text Editor v2");
		setLocationRelativeTo(null);
		setVisible(true);
		pack();
	}

	@Override
	public final void actionPerformed(ActionEvent e) {
		if (e.getSource() == paste)
			new Paste(engine).execute();
		else if (e.getSource() == copy)
			new Copy(engine).execute();
		else if (e.getSource() == cut)
			new Cut(engine).execute();
		else if (e.getSource() == delete)
			new DeleteText(engine).execute();
	}

	@Override
	public void update(editor.Observable o) {

		if (o == null)
			throw new IllegalArgumentException("Null o");

		if (o instanceof Buffer) {
			Buffer buffer = (Buffer) o;
			modifFilter.setActive(false);
			selectionListener.setActive(false);
			textArea.setText(buffer.getContent());
			textArea.setCaretPosition(buffer.getNewOffset());
			selectionListener.setActive(true);
			modifFilter.setActive(true);
		}
	}
}