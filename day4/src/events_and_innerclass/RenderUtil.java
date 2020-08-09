package events_and_innerclass;

public class RenderUtil {
	
	private RenderUtil() {
	}
	
	public static void doRender(Renderable renderable) {
		renderable.getRenderer().render();
	}

}
