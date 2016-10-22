package motor;

import event.EventManager;
import event.EventTypeEnum;
import event.Subject;

public class MotorV1 implements EventManager {

	private void copy() {
		Context con = Context.getInstance();
		con.setClipBoard(con.getSelected().toString());
	}

	private void cut(Subject object) {
		Context con = Context.getInstance();
		con.setClipBoard(con.getSelected().toString());
		int start = con.getText().indexOf(con.getSelected().toString());
		con.setText(con.getText().replace(start, start + con.getSelected().length(), "").toString());
		con.setSelected("");
	}

	@Override
	public void executeEvent(EventTypeEnum event, Subject object) {
		if (object == null)
			return;
		switch (event) {
			case SELECT:
				select(object);
				break;
			case COPY:
				copy();
				break;
			case CUT:
				cut(object);
				break;
			case PASTE:
				paste(object);
				break;
			case INSERT:
				insert(object);
				break;
			case SAVE:
				save();
				break;
			default:
				System.out.println("Invalid event");
				return;
		}
	}

	private void insert(Subject object) {
		// TODO Auto-generated method stub

	}

	private void paste(Subject object) {
		// TODO Auto-generated method stub

	}

	private void save() {
		// TODO Auto-generated method stub

	}

	private void select(Subject object) {
		Context con = Context.getInstance();
		con.setSelected(con.getText().substring(Subject.selection[0], Subject.selection[1]));
	}

}
