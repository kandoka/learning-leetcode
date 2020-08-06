package sort;

import java.util.Arrays;

public class RadixSort implements ISort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		new RadixSort().sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void sort(int[] arr) {
		//int max = findMax(arr);
		int[][] bucket = new int[10][arr.length];
		int count[] = new int[10];
		int power = 1;
		boolean flag = true;
		while(flag) {
			flag = false;
			//将元素放入对应的桶
			for(int i=0; i<arr.length; i++) {
				int which = arr[i] / power % 10;//取余
				bucket[which][count[which]++] = arr[i];
				if(which > 0 || 1 == power) {
					flag = true;
				}//当未达到最大位数或正在比较个位时（屏蔽个位都是0的情况）					
			}//循环将所有元素按当前位数上的数字放入对应桶中
			power*=10;
			
			//将元素放回到序列中
			int index = 0;
			for(int i=0; i<bucket.length; i++) {
				if(count[i] > 0) {
					for(int j=0; j<count[i]; j++) {
						arr[index++] = bucket[i][j];
					}//从桶底到桶顶
				}//桶里有东西时
				count[i] = 0;//重置计数
			}//遍历一排0-9的桶
		}//循环直到到达最大位数
	}

}
