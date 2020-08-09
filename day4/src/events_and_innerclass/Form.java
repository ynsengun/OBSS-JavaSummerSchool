package events_and_innerclass;

public class Form implements MouseListener, Renderable{
	private Button button;
	private FormRenderer formRenderer;
	
	public Form() {
		formRenderer = new FormRenderer();
	}

	@Override
	public void mouseClicked(ClickEvent clickEvent) {
		if( clickEvent.getSource() instanceof Button)
			System.out.println("Button clicked, date: " + clickEvent.getDate());
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
	
	@Override
	public Renderer getRenderer() {
		return formRenderer;
	}
	
	private class FormRenderer implements Renderer{
		@Override
		public void render() {
			System.out.println("Form rendered");
		}
	}
}
