package serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

	public static void main(String[] args) {
		Course course = new Course("1");
		Student student = new Student("s1", course);
		Student newStudent;

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("/Users/yusuf/OBSS/day3/src/serialization/out.txt"))) {

			oos.writeObject(student);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (ObjectInputStream oos = new ObjectInputStream(
				new FileInputStream("/Users/yusuf/OBSS/day3/src/serialization/out.txt"))) {

			newStudent = (Student) oos.readObject();
			// System.out.println(newStudent.getName());
			System.out.println(newStudent.getName() + " " + newStudent.getCourse().getTemp());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
