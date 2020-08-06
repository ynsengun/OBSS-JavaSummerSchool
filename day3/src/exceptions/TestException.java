package exceptions;

public class TestException {
	public static void main(String[] args) {
		try {
			Executer.exec();
		} catch (MyCheckedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
