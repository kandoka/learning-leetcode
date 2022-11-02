package sort;

import java.util.Arrays;

public class MergeSort implements ISort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		new MergeSort().sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void sort(int[] arr) {
		int[] temp = new int[arr.length];
		divide(arr, temp, 0, arr.length-1);
	}
	
	private void divide(int[] origin, int[] temp, int left, int right) {
		int mid = left;
		if(left < right) {
			mid = (left + right) / 2;
			divide(origin, temp, left, mid);
			divide(origin, temp, mid + 1, right);
			merge(origin, temp, left, mid, right);
		}
	}
	
	private void merge(int[] origin, int[] temp, int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int t = left;
		//比较i和j指向的值，并把较小的值放置到temp中，直到有一方到达终点。
		while(i <= mid && j <= right) {
			if(origin[i] < origin[j]) {
				temp[t] = origin[i];
				i++;
				t++;
			}
			else {
				temp[t] = origin[j];
				j++;
				t++;
			}
		}
		
		//将剩余的值按顺序放入temp中
		while(i <= mid) {
			temp[t] = origin[i];
			i++;
			t++;
		}
		while(j <= right) {
			temp[t] = origin[j];
			j++;
			t++;
		}
		
		//将temp中所有值拷贝进原始数组中
		t = left;
		while(t <= right) {
			origin[t] = temp[t];
			t++;
		}
	}
}
