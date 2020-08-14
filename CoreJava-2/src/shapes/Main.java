package shapes;

public class Main {

	public static void main(String[] args) {
		try {
			Pen pen = new Pen();
			Shape circle = new Circle(3, "red");
			Shape rectangle = new Rectangle(3, 4, "blue");

			pen.draw(circle);
			pen.draw(rectangle);

			pen.changeColor("black", circle);
			pen.changeColor("white", rectangle);

			pen.draw(circle);
			pen.draw(rectangle);
		} catch (IllegalArgumentException e) {
			System.err.println("Error Occured");
		}
	}

}
