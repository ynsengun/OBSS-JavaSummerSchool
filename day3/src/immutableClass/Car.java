package immutableClass;

public class Car {
	private String name;
	
	public Car( String name) {
		this.name = name;
	}
	
	public Car( Car other) {
		this(other.getName());
	}

	public String getName() {
		return name;
	}
	
	public void setName( String name) {
		this.name = name;
	}
}
