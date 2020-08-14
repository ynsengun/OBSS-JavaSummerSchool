package events_and_innerclass;

import java.util.ArrayList;

public class Button implements Renderable{
	private ArrayList<MouseListener> mouseListeners;
	private ButtonRenderer buttonRenderer;
	
	public Button() {
		mouseListeners = new ArrayList<>();
		buttonRenderer = new ButtonRenderer();
	}
	
	public void click() {
		ClickEvent clickEvent = new ClickEvent(this);
		for( MouseListener mouseListener : mouseListeners) {
			mouseListener.mouseClicked(clickEvent);
		}
	}
	
	public void addMouseListener( MouseListener mouseListener) {
		mouseListeners.add(mouseListener);
	}
	
	public void removeMouseListener( MouseListener mouseListener) {
		mouseListeners.remove(mouseListener);
	}
	
	@Override
	public Renderer getRenderer() {
		return buttonRenderer;
	}
	
	private class ButtonRenderer implements Renderer{
		@Override
		public void render() {
			System.out.println("Button rendered");
		}
	}
}
