/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Ma�l Nogues mael.nogues@etudiant.univ-rennes.fr
 * @author Mathieu Grandmontagne mathieu.grandmontagne@etudiant.univ-rennes.fr
 */
package fr.istic.m1.aco.miniediteur.v1.textEditor;

import fr.istic.m1.aco.miniediteur.v1.event.EventDispatcher;
import fr.istic.m1.aco.miniediteur.v1.view.HMI;

/**
 * The text editor.
 *
 * It launches the user interface and the creates the event dispatcher.
 */
public class TextEditor {

	/** The event dispatcher. */
	private static final EventDispatcher ed = EventDispatcher.getInstance();

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		HMI hmi = new HMI();
		hmi.launch();
	}

}