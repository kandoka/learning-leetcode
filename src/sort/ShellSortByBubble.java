package sort;

import java.util.Arrays;

public class ShellSortByBubble implements ISort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		new ShellSortByBubble().sort(arr);
		System.out.println(Arrays.toString(arr));

	}

	public void sort(int[] arr) {
		int temp = 0;
		int gap = arr.length/2;
		while(gap > 0) {
			//遍历每个组
			for(int i=gap;i<arr.length;i++) {
				//遍历一组中的每个元素
				for(int j=i-gap;j>=0;j-=gap) {
					if(arr[j] > arr[j+gap]) {
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					}
				}
			}			
			gap /= 2;
		}
	}
	
//	public void sort(int[] arr) {
//		int temp = 0;
//		int gap = arr.length/2;
//		while(gap > 0) {
//			//遍历每个组
//			for(int i=0;i<gap;i++) {
//				//遍历一组中的每个元素
//				for(int j=i;j<arr.length-gap;j+=gap) {
//					if(arr[j] > arr[j+gap]) {
//						temp = arr[j];
//						arr[j] = arr[j + gap];
//						arr[j + gap] = temp;
//					}
//				}
//			}			
//			gap /= 2;
//		}
//	}
}
