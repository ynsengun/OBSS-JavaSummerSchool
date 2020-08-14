package events_and_innerclass;

import java.util.EventObject;

public final class ClickEvent extends EventObject {
	private static final long serialVersionUID = -4384234495382037215L;
	private final String date;

	public ClickEvent(Object source) {
		super(source);
		this.date = "now";
	}

	public String getDate() {
		return date;
	}

}
