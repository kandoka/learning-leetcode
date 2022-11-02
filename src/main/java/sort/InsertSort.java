package sort;

import java.util.Arrays;

public class InsertSort implements ISort {
	public static void main(String[] args) {
		int[] arr = {101, 34, 119, 1, -1, 89};
		new InsertSort().sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public void sort(int[] arr) {
		for(int i=1;i<arr.length;i++) {
			int indexForInsert = i;
			int insertVal = arr[indexForInsert];
			while(indexForInsert > 0 && insertVal < arr[indexForInsert-1]) {
				arr[indexForInsert] = arr[indexForInsert-1];
				indexForInsert--;
			}//从i开始向前遍历有序数组，没找到合适的插入位置就将有序数组后移
			arr[indexForInsert] = insertVal;
		}//每趟将一个元素插入到有序数组中合适的位置
	}
}
