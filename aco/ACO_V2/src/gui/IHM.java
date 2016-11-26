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

import commands.Replay;
import commands.Start;
import commands.Stop;
import editor.Observable;
import editor.Observateur;
import editor.Recorder;
import engine.Buffer;
import engine.MoteurEdition;
import recordables.CopyRecordable;
import recordables.CutRecordable;
import recordables.DelTextRecordable;
import recordables.PasteRecordable;

/**
 * Interface graphique de notre éditeur
 * Utilise une JTextArea pour l'affichage du buffer
 */
public final class IHM extends JFrame implements Observateur, ActionListener {

	private static final long serialVersionUID = 1L;

	// Boutons
	private final JButton	coller;
	private final JButton	copier;
	private final JButton	couper;
	private final JButton	supprimer;
	private final JButton	enregistrer;
	private final JButton	jouer;
	private final JButton	stop;

	// Zone de texte
	private final JTextArea zoneTexte;

	// Moteur d'édition
	private final MoteurEdition moteur;

	// Listener d'insertions
	private final FiltreModifications	filtreModifs;
	private final ListenerSelection		listenerSelection;

	// Recorder des mementos des commandes enregistrables
	private final Recorder recorder;

	public IHM(final MoteurEdition moteur, final Recorder recorder) {

		/* Préconditions */
		if (moteur == null)
			throw new IllegalArgumentException("engine est à null");

		if (recorder == null)
			throw new IllegalArgumentException("recorder est à null");

		/* Traitement */
		this.moteur = moteur;
		this.recorder = recorder;

		filtreModifs = new FiltreModifications(moteur, recorder);
		listenerSelection = new ListenerSelection(moteur, recorder);

		zoneTexte = new ZoneTexte(15, 80, moteur, recorder);
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

		enregistrer = new BoutonEnregistrer();
		jouer = new BoutonJouer();
		stop = new BoutonStop();

		recorder.ajouterObservateur((Observateur) enregistrer);
		recorder.ajouterObservateur((Observateur) jouer);
		recorder.ajouterObservateur((Observateur) stop);

		// Association des icones aux boutons
		coller.setIcon(new ImageIcon(getClass().getResource("/icones/coller.png")));
		copier.setIcon(new ImageIcon(getClass().getResource("/icones/copier.png")));
		couper.setIcon(new ImageIcon(getClass().getResource("/icones/couper.png")));
		supprimer.setIcon(new ImageIcon(getClass().getResource("/icones/supprimer.png")));
		enregistrer.setIcon(new ImageIcon(getClass().getResource("/icones/rec.png")));
		jouer.setIcon(new ImageIcon(getClass().getResource("/icones/play.png")));
		stop.setIcon(new ImageIcon(getClass().getResource("/icones/stop.png")));

		// Association des bulles d'aide
		coller.setToolTipText("Coller");
		copier.setToolTipText("Copier");
		couper.setToolTipText("Couper");
		supprimer.setToolTipText("Supprimer");
		enregistrer.setToolTipText("Enregistrer");
		jouer.setToolTipText("Jouer");
		stop.setToolTipText("Stop");

		// Spécification des listeners
		coller.addActionListener(this);
		copier.addActionListener(this);
		couper.addActionListener(this);
		supprimer.addActionListener(this);
		enregistrer.addActionListener(this);
		jouer.addActionListener(this);
		stop.addActionListener(this);

		coller.setFocusable(false);
		copier.setFocusable(false);
		couper.setFocusable(false);
		supprimer.setFocusable(false);
		enregistrer.setFocusable(false);
		jouer.setFocusable(false);
		stop.setFocusable(false);

		// Ajout des boutons à la barre d'outils
		menuBar.add(copier);
		menuBar.add(couper);
		menuBar.add(coller);
		menuBar.add(supprimer);
		menuBar.add(enregistrer);
		menuBar.add(jouer);
		menuBar.add(stop);

		// On ne peut pas jouer une action dès le début
		stop.setEnabled(false);
		jouer.setEnabled(false);

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

	@Override
	public final void actionPerformed(ActionEvent e) {

		if (e.getSource() == coller)
			new PasteRecordable(moteur, recorder).execute();
		else if (e.getSource() == copier)
			new CopyRecordable(moteur, recorder).executer();
		else if (e.getSource() == couper)
			new CutRecordable(moteur, recorder).executer();
		else if (e.getSource() == supprimer)
			new DelTextRecordable(moteur, recorder).executer();
		else if (e.getSource() == enregistrer)
			new Start(recorder).execute();
		else if (e.getSource() == jouer)
			new Replay(recorder).execute();
		else if (e.getSource() == stop)
			new Stop(recorder).execute();
	}

	/**
	 * @see Observateur
	 */
	@Override
	public final void miseAJour(Observable o) {

		/* Préconditions */
		if (o == null)
			throw new IllegalArgumentException("o est à null");

		/* Traitement */
		if (o instanceof Buffer) {

			Buffer buffer = (Buffer) o;

			// On désactive le filtre pour éviter de renvoyer une commande
			filtreModifs.setReagir(false);
			listenerSelection.setReagir(false);

			zoneTexte.setText(buffer.getContenu());
			zoneTexte.setCaretPosition(buffer.getOffsetModif());

			listenerSelection.setReagir(true);
			filtreModifs.setReagir(true);
		}
	}
}