package serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student implements Serializable {
	private String name;
	// private Course course;
	private transient Course course;

	public Student() {

	}

	public Student(String name, Course course) {
		this.setName(name);
		this.setCourse(course);
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();

		oos.writeUTF(course.getTemp());
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();

		this.course = new Course(ois.readUTF());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
