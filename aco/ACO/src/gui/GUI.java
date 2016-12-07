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
import engine.Buffer;
import engine.EditionEngine;

/**
 * GUI based on the Swings' JtextArea.
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

	/** The text area. */
	//
	private final JTextArea textArea;

	/** The engine. */
	private final EditionEngine engine;

	/** The modification filter. */
	private final ModificationFilter modifFilter;

	/** The selection listener. */
	private final SelectionListener selectionListener;

	/**
	 * Instantiates a new gui.
	 *
	 * @param engine
	 *            the engine
	 */
	public GUI(final EditionEngine engine) {
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("Null engine");

		/* Treatment */
		this.engine = engine;

		// sets textArea and listeners on this
		modifFilter = new ModificationFilter(engine);
		selectionListener = new SelectionListener(engine);
		textArea = new TextAreaCustom(15, 80, engine);
		textArea.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		textArea.setFont(new Font("monospaced", Font.PLAIN, 14));
		textArea.addCaretListener(selectionListener);
		((AbstractDocument) textArea.getDocument()).setDocumentFilter(modifFilter);
		JScrollPane scrollingText = new JScrollPane(textArea);
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());

		content.add(scrollingText, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 0));

		paste = new JButton();
		copy = new JButton();
		cut = new JButton();
		delete = new JButton();

		// Sets the icons
<<<<<<< HEAD
		paste.setIcon(new ImageIcon(getClass().getResource("/icons/paste.png")));
		copy.setIcon(new ImageIcon(getClass().getResource("/icons/copy.png")));
		cut.setIcon(new ImageIcon(getClass().getResource("/icons/cut.png")));
		delete.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));
=======
		paste.setIcon(new ImageIcon(getClass().getResource("/icones/paste.png")));
		copy.setIcon(new ImageIcon(getClass().getResource("/icones/copy.png")));
		cut.setIcon(new ImageIcon(getClass().getResource("/icones/cut.png")));
		delete.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
>>>>>>> 41f245e1c9b5423a3b52be7eda4f1bc6eceeb23f

		// Sets the tips bubble
		paste.setToolTipText("Paste");
		copy.setToolTipText("Copy");
		cut.setToolTipText("Cut");
		delete.setToolTipText("delete");

		// Set listener on button
		paste.addActionListener(this);
		copy.addActionListener(this);
		cut.addActionListener(this);
		delete.addActionListener(this);
		paste.setFocusable(false);
		copy.setFocusable(false);
		cut.setFocusable(false);
		delete.setFocusable(false);

		// Add all buttons on menuBar
		menuBar.add(copy);
		menuBar.add(cut);
		menuBar.add(paste);
		menuBar.add(delete);

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
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
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