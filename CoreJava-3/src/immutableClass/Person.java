package immutableClass;

public final class Person {
	private final String name;
	private final String surname;
	private final int birthDay;
	private final Car car;
	
	public Person( String name, String surname, int birthDay, Car car) {
		this.name = name;
		this.surname = surname;
		this.birthDay = birthDay;
		this.car = new Car(car);
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public Car getCar() {
		return new Car(car);
	}
}
