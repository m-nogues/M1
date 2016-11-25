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
import engine.ImplementedEngine;

/**
 * Interface graphique de notre éditeur Utilise une JTextArea pour l'affichage
 * du buffer
 */
public final class GUI extends JFrame implements Observer, ActionListener {
	private static final long serialVersionUID = 1L;
	// Boutons
	private final JButton	coller;
	private final JButton	copier;
	private final JButton	couper;
	private final JButton	supprimer;
	// Zone de texte
	private final JTextArea zoneTexte;
	// Moteur d'édition
	private final EditionEngine moteur;
	// Listener d'insertions
	private final FiltreModifications	filtreModifs;
	private final ListenerSelection		listenerSelection;

	public GUI(final EditionEngine moteur) {
		/* Préconditions */
		if (moteur == null)
			throw new IllegalArgumentException("engine est à null");
		/* Traitement */
		this.moteur = moteur;
		filtreModifs = new FiltreModifications(moteur);
		listenerSelection = new ListenerSelection(moteur);
		zoneTexte = new ZoneTexte(15, 80, moteur);
		zoneTexte.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		zoneTexte.setFont(new Font("monospaced", Font.PLAIN, 14));
		zoneTexte.addCaretListener(listenerSelection);
		((AbstractDocument) zoneTexte.getDocument()).setDocumentFilter(filtreModifs);
		JScrollPane scrollingText = new JScrollPane(zoneTexte);
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		// Ajout de la barre de défilement
		content.add(scrollingText, BorderLayout.CENTER);
		// Création de la barre d'outils
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 0));
		// Instanciation des boutons
		coller = new JButton();
		copier = new JButton();
		couper = new JButton();
		supprimer = new JButton();
		// Association des icones aux boutons
		coller.setIcon(new ImageIcon(getClass().getResource("/icones/coller.png")));
		copier.setIcon(new ImageIcon(getClass().getResource("/icones/copier.png")));
		couper.setIcon(new ImageIcon(getClass().getResource("/icones/couper.png")));
		supprimer.setIcon(new ImageIcon(getClass().getResource("/icones/supprimer.png")));
		// Association des bulles d'aide
		coller.setToolTipText("Paste");
		copier.setToolTipText("Copy");
		couper.setToolTipText("Cut");
		supprimer.setToolTipText("Supprimer");
		// Spécification des listeners
		coller.addActionListener(this);
		copier.addActionListener(this);
		couper.addActionListener(this);
		supprimer.addActionListener(this);
		coller.setFocusable(false);
		copier.setFocusable(false);
		couper.setFocusable(false);
		supprimer.setFocusable(false);
		// Ajout des boutons à la barre d'outils
		menuBar.add(copier);
		menuBar.add(couper);
		menuBar.add(coller);
		menuBar.add(supprimer);
		// Mise en place des éléments dans la fenêtre
		setContentPane(content);
		setJMenuBar(menuBar);
		// Ajout des comportements par défaut et des propriété propres à notre
		// éditeur + Affichage
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Editeur de texte v3 [BERTIER - FOUCAULT]");
		setLocationRelativeTo(null);
		setVisible(true);
		pack();
	}

	public GUI(ImplementedEngine engine, Recorder recorder) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public final void actionPerformed(ActionEvent e) {
		if (e.getSource() == coller)
			new Paste(moteur).execute();
		else if (e.getSource() == copier)
			new Copy(moteur).execute();
		else if (e.getSource() == couper)
			new Cut(moteur).execute();
		else if (e.getSource() == supprimer)
			new DeleteText(moteur).execute();
	}

	@Override
	public void update(editor.Observable o) {
		/* Preconditions */
		if (o == null)
			throw new IllegalArgumentException("o est à null");
		/* Treatment */
		if (o instanceof Buffer) {
			Buffer buffer = (Buffer) o;
			filtreModifs.setReagir(false);
			listenerSelection.setReagir(false);
			zoneTexte.setText(buffer.getContent());
			zoneTexte.setCaretPosition(buffer.getNewOffset());
			listenerSelection.setReagir(true);
			filtreModifs.setReagir(true);
		}
	}
}