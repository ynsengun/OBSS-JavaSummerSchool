package shapes;

public class Circle extends Shape{
	private int radius;

	public Circle(int radius, String color) {
		super(color);
		this.setRadius(radius);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		if (radius < 0) {
			this.radius = 0;
			throw new IllegalArgumentException("Radius cannot be smaller than zero");
		}
		this.radius = radius;
	}

	@Override
	public String getDrawInfo() {
		return "Area of the " + getColor() + " circle: " + getArea();
	}

	@Override
	public double getArea() {
		return this.radius * this.radius * Math.PI;
	}

}
