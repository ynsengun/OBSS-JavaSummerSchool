import java.util.Scanner;

public class Age {

	public static void main(String[] args) {
//		System.out.println("Hello world");
		
		int age;
		Scanner scan = new Scanner(System.in);
		
		age = scan.nextInt();
		
		if(age > 0 && age <= 18) {
			System.out.println("Young");
		} else if(age > 18) {
			System.out.println("Old");
		} else {
			System.out.println("Invalid input");
		}
	}

}
