package sort;

public class GenerateArr {
	public static int[] getArr(int maxSize) {
		int[] arr = new int[maxSize];
		for(int i=0; i<maxSize; i++) {
			arr[i] = (int)(Math.random()*maxSize);
		}
		return arr;
	}
}
