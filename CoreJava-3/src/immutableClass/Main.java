package immutableClass;

public class Main {

	public static void main(String[] args) {
		Car car1 = new Car("car1");
		
		Person person1 = new Person("p1", "p1", 1, car1);
		
		System.out.println(person1.getName());
		System.out.println(person1.getSurname());
		System.out.println(person1.getBirthDay());
		System.out.println(person1.getCar().getName());
	}

}
