
public class ArrayReverse {
	public static void main(String[] args) {
		int array[] = new int[10];
		
		for( int i = 0 ; i < array.length ; i++ ) {
			array[i] = i * 4;
		}
		
		for( int i = 0 ; i < array.length / 2 ; i++ ) {
			int otherLocation = array.length - i - 1;
			
			// swap two array positions
			int temp = array[i];
			array[i] = array[otherLocation];
			array[otherLocation] = temp;
		}
		
		for( int i = 0 ; i < array.length ; i++ ) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
