package shapes;

public class Pen {
	public void draw(Drawable s) {
		String drawInfo = s.getDrawInfo();
		System.out.println(drawInfo);
	}

	public void changeColor(String color, Shape s) {
		s.setColor(color);
	}
}
