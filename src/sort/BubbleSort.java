package sort;

import java.util.Arrays;

public class BubbleSort implements ISort {
	public static void main(String[] args) {
		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		new BubbleSort().sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public void sort(int[] arr) {
		int temp = 0;
		for(int j=0;j<arr.length-1;j++) {
			for(int i=0;i<arr.length-1;i++) {
				if(arr[i] > arr[i+1]) {
					temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
				}
			}//一趟，将最大的数不断置换到末尾
		}//n-1趟
	}

}
