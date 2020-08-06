package shapes;

public abstract class Shape implements Drawable {
	private String color;

	public Shape(String color) {
		this.setColor(color);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public abstract double getArea();
}
