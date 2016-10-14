/*
 *
 */
package event;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class EventDispatcher {

	public ConcurrentMap<EventTypeEnum, CopyOnWriteArraySet<EventManager>> EventManagers = new ConcurrentHashMap<EventTypeEnum, CopyOnWriteArraySet<EventManager>>();

	public EventDispatcher() {
		for (EventTypeEnum e : EventTypeEnum.values())
			EventManagers.put(e, new CopyOnWriteArraySet<>());
	}

	public void dispatch(EventTypeEnum event, Subject object) {
		if (event == null)
			return;
		for (EventManager manager : EventManagers.get(event))
			manager.executeEvent(event, object);
	}

	public void enlist(EventTypeEnum event, EventManager manager) {
		if (event == null)
			return;
		EventManagers.get(event).add(manager);
	}

	public void unlist(EventTypeEnum event, EventManager manager) {
		if (event == null)
			return;
		EventManagers.get(event).remove(manager);
	}
}
