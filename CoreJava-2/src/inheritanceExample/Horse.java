package inheritanceExample;

public class Horse extends Animal {
	String name;
	
	public Horse(String name) {
		super(name);
		this.name = name;
		System.out.println(name + " is an horse");
	}
}
