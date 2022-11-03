package sort;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

import java.util.Arrays;

@Slf4j
public class BubbleSort implements ISort {
	public static void main(String[] args) {
		BasicConfigurator.configure();
//		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		int[] arr = ArrUtil.getArr(10);
		new BubbleSort().sort(arr);
		log.info(Arrays.toString(arr));
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
