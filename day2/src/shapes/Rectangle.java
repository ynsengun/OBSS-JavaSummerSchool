package shapes;

public class Rectangle extends Shape {
	private int width;
	private int height;

	public Rectangle(int width, int height, String color) {
		super(color);
		this.setWidth(width);
		this.setHeight(height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		if (width < 0) {
			this.width = 0;
			throw new IllegalArgumentException("Width cannot be smaller than zero");
		}
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		if (height < 0) {
			this.height = 0;
			throw new IllegalArgumentException("Height cannot be smaller than zero");
		}
		this.height = height;
	}

	@Override
	public String getDrawInfo() {
		return "Area of the " + getColor() + " rectangle: " + getArea();
	}

	@Override
	public double getArea() {
		return this.height * this.width;
	}
}
