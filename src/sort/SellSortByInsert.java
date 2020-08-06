package sort;

import java.util.Arrays;

public class SellSortByInsert implements ISort {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		new SellSortByInsert().sort(arr);
		System.out.println(Arrays.toString(arr));

	}

	public void sort(int[] arr) {
		int temp = 0;
		int gap = arr.length/2;
		while(gap > 0) {
			for(int i=gap; i<arr.length; i++) {
				int j;
				temp = arr[i];
				for(j=i; j-gap>=0&&arr[j-gap]>temp; j-=gap) {
					arr[j] = arr[j-gap];
				}//遍历一组中的所有元素
				arr[j] = temp;
			}//遍历每个组			
			gap /= 2;
		}
	}
}
