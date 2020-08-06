package exceptions;

public class Executer {
	public static void exec() throws MyCheckedException {
		try {
			Divider.divide();
		} catch (ArithmeticException ex) {
			throw new MyCheckedException(ex);
//			throw new MyUncheckedException();
		}
	}
}
