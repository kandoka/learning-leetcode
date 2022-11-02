package search;

import java.util.Arrays;

public class BinarySearch implements ISearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(new BinarySearch().search(arr, 7));
		
	}

	public int search(int[] arr, int findVal) {
		int left = 0;
		int right = arr.length-1;
		return searchByRecurion(arr, findVal, left, right);
	}
	
	private int searchByRecurion(int[] arr, int findVal, int left, int right) {
		//没找到
		if(left > right)
			return -1;
		//正在找
		int mid = (left + right) / 2;
		if(findVal < arr[mid])
			return searchByRecurion(arr, findVal, left, mid);
		else if(findVal > arr[mid])
			return searchByRecurion(arr, findVal, mid+1, right);
		else//找到
			return mid;
	}
}
