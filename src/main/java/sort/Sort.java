package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sort {
	
	private ISort sort;
	
	public Sort(ISort sort) {
		this.sort = sort;
	}
	
	public void doSort(int[] arr) {
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间是=" + date1Str);
		sort.sort(arr);
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序后的时间是=" + date2Str);
		System.out.println("花费时间=" + (data2.getTime() - data1.getTime()));
	}
}
