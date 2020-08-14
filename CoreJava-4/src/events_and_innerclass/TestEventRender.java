package events_and_innerclass;

public class TestEventRender {

	public static void main(String[] args) {
		// Event test
		Form form = new Form();
		Button button = new Button();
		
		form.setButton(button);
		button.addMouseListener(form);
		
		button.click();
		
		// Render test
		RenderUtil.doRender(button);
		RenderUtil.doRender(form);
	}
}
