import java.util.Scanner;

public class NumberGuess {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		try {
			int myNumber = (int) (Math.random() * 32 + 1);
			while (true) {
				int input = 0;
				boolean continueFlag = false;
				try {
					System.out.print("Insert an input (input should an integer between 1 and 32): ");
					input = scanner.nextInt();
				} catch (Exception e) {
					scanner = new Scanner(System.in);
					continueFlag = true;
				}

				if (input < 1 || input > 32 || continueFlag) {
					System.out.println("Invalid input!");
					continue;
				}

				if (input > myNumber) {
					System.out.println("Down");
				} else if (input < myNumber) {
					System.out.println("Up");
				} else {
					System.out.println("Congratulations!");
					break;
				}
			}
		} finally {
			scanner.close();
		}
	}

}
