package events_and_innerclass;

import java.util.EventListener;

public interface MouseListener extends EventListener {
	void mouseClicked(ClickEvent clickEvent);
}
