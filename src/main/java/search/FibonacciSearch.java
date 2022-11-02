package search;

public class FibonacciSearch implements ISearch{

	public static void main(String[] args) {
		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(new FibonacciSearch().search(arr, 7));
	}

	@Override
	public int search(int[] arr, int findVal) {
		
		return 0;
	}
	
	private int[] fib(int maxSize) {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for(int i=2; i<maxSize; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		return f;
	}
}
