package generic;

public class Executer {

	public static void main(String[] args) {
		Student student = new Student("name1");
		Student student2;

		Utility.<Student>serializeObject(student);
		student2 = Utility.<Student>deserialize();

		if (student2 != null)
			System.out.println(student2.getName());
	}

}
