package sort;

public class Test {
	public static void main(String[] args) {
		ISort algorithm = new HeapSort();
		Sort sort = new Sort(algorithm);
		sort.doSort(ArrUtil.getArr(80000));
	}
}
