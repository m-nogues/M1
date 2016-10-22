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
package event;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The event dispatcher.
 *
 * It receives events from the subjects and dispatches it to the event managers
 * that are concerned by this event.
 */
public class EventDispatcher {

	/** The instance of EventDispatcher. */
	private static EventDispatcher INSTANCE = new EventDispatcher();

	/**
	 * Gets the single instance of EventDispatcher.
	 *
	 * @return single instance of EventDispatcher
	 */
	public static EventDispatcher getInstance() {
		return INSTANCE;
	}

	/**
	 * The multithread enabled map of event type paired with their set of
	 * enlisted managers.
	 */
	private ConcurrentMap<EventTypeEnum, CopyOnWriteArraySet<EventManager>> EventManagers = new ConcurrentHashMap<>();

	/**
	 * Instantiates a new event dispatcher.
	 */
	private EventDispatcher() {
		for (EventTypeEnum e : EventTypeEnum.values())
			EventManagers.put(e, new CopyOnWriteArraySet<>());
	}

	/**
	 * Dispatches the event from the subject to all the concerned event
	 * managers.
	 *
	 * @param event
	 *            the event
	 * @param object
	 *            the object
	 */
	public void dispatch(EventTypeEnum event, Subject object) {
		if (event == null)
			return;
		for (EventManager manager : EventManagers.get(event))
			manager.executeEvent(event, object);
	}

	/**
	 * Enlists the event manager to the event type.
	 *
	 * @param event
	 *            the event
	 * @param manager
	 *            the manager
	 */
	public void enlist(EventTypeEnum event, EventManager manager) {
		if (event == null)
			return;
		EventManagers.get(event).add(manager);
	}

	/**
	 * Resigns the event manager from the event type.
	 *
	 * @param event
	 *            the event
	 * @param manager
	 *            the manager
	 */
	public void resign(EventTypeEnum event, EventManager manager) {
		if (event == null)
			return;
		EventManagers.get(event).remove(manager);
	}
}