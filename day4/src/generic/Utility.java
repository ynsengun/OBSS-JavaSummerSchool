package generic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Utility {
	public static <T extends Serializable> void serializeObject(T t) {

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("/Users/yusuf/OBSS/day4/src/generic/out.txt"))) {

			// System.out.println(((Student)t).getName());
			oos.writeObject(t);
			oos.flush();
		} catch (IOException e) {
			System.out.println("Error on serialization");
			// e.printStackTrace();
		}
	}

	public static <T extends Serializable> T deserialize() {
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("/Users/yusuf/OBSS/day4/src/generic/out.txt"))) {

			return (T) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error on deserialization");
			// e.printStackTrace();
		}

		return null;
	}
}
