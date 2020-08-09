package generic;

import java.io.Serializable;

//public class Student {
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2727756889039909798L;

	private String name;

	public Student() {
		name = "";
	}

	public Student(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
