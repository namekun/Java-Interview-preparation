package Ch8_Java_Basic;

public class Array {
	public static void arrayReference() {
		final int[] myArray = new int[] { 0, 1, 2, 3, 4, 5 };
		int [] arrayReference2 = myArray;
		
		arrayReference2[5] = 99;
		
		for (int i = 0; i < arrayReference2.length; i++) {
			System.out.print(arrayReference2[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		arrayReference();
	}
}
