package BusReservation;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Bus bus = new Bus(Destination.ANKARA, 2);

		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				Destination destination;
				String name;

				System.out.print("Name of the passanger (-1 to exit): ");
				name = scanner.nextLine();
				if ("-1".equals(name)) {
					break;
				}

				System.out.print("Destination of the passanger: ");
				String destinationStr = scanner.nextLine();
				try {
					destination = Destination.valueOf(destinationStr);
				} catch (IllegalArgumentException e) {
					destination = null;
				}

				bus.insertPassanger(new Passanger(name, destination));
			}
		}
	}

}
