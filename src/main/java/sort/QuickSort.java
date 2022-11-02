
package sort;

import java.util.Arrays;

public class QuickSort implements ISort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		new QuickSort().sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void sort(int[] arr) {
		// TODO Auto-generated method stub
		int left = 0;
		int right = arr.length-1;
		recursinSort(arr, left, right);
	}
	
	private void recursinSort(int[] arr, int left, int right) {
		if(left >= right)
			return;
		int temp = arr[left];//取基准值
		int l = left;
		int r = right;
		//比基准值小的放左边，比基准值大的放右边
		while(l < r) {
			while(l < r && arr[r] > temp)//由右向左直到遇到比基准值小的
				r--;
			arr[l] = arr[r];
			while(l < r && arr[l] <= temp)//由左向右直到遇到比基准值大的
				l++;
			arr[r] = arr[l];
		}
		arr[l] = temp;
		recursinSort(arr, left, l-1);//对新基准值左边做同样的操作
		recursinSort(arr, r+1, right);//对新基准值右边边做同样的操作
	}

}
