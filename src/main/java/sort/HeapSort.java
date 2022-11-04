package sort;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import util.tree.BinaryTree;
import util.tree.BinaryTreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

@Slf4j
public class HeapSort implements ISort {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		int[] arr = ArrUtil.getArr(12);
		log.info("开始：{}", arr);
//		BinaryTreeNode root = HeapSort.toTree(arr);
//		BinaryTree.prettyPrint(root);
		new HeapSort().sort(arr);
		log.info("结果：{}", arr);
	}

	@Override
	public void sort(int[] arr) {
		int temp = 0;
		//自底向上遍历，建立大顶堆。
		for(int i=arr.length/2-1; i>=0; i--) {//从最下层的最后一个非叶子节点开始遍历
			buildHeap(arr, i, arr.length);
		}//结果得到数组第一位一定是最大值
		log.info("大顶堆完成：{}", arr);
		
		//开始不断交换和重建大顶堆
		for(int i=arr.length-1; i>0; i--) {
			temp = arr[i];
			arr[i] = arr[0];//arr[0]为每次重建大顶堆而产生的新的最大值
			arr[0] = temp;
			buildHeap(arr, 0, i);//每次从0为出发节点，将当前节点0这个较小值下沉到合适的位置
		}//得到有序序列
	}

	/**
	 * 	自顶向下遍历，将较大的值向上交换
	 * @param arr 待建堆的数组
	 * @param i	表示非叶子节点的在数组中的下标
	 * @param length 表示数组中有多少个元素需要调整，数量逐渐减少
	 */
	private static void buildHeap(int[] arr, int i, int length) {
		log.info("【建堆一次遍历】开始，当前根节点坐标：{} 值：{}，当前数组长度：{}", i, arr[i], length);
		int temp = arr[i];//存储当前开始建堆的节点的元素，即为出发节点的值
		//每次向下遍历一层，到达左子节点
		for(int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			log.info("【下一层】");
			if(k < length - 1 && arr[k] < arr[k + 1]) {//如果存在右子节点k+1且右子节点比左子节点k大
				k++;//到达右子节点，设为k，这样就选出i节点下一层的最大的子节点k
				log.info("先比较左右子节点值，左子节点坐标：{}，值：{}，右子节点坐标：{}，值：{} " +
						"左子节点 比 右子节点小，当前节点移动到右子节点吗，右子节点作为最大的子节点", k, arr[k], k+1, arr[k+1]);
			} else if(k < length - 1){
				log.info("先比较左右子节点值，左子节点坐标：{}，值：{}，右子节点坐标：{}，值：{} " +
						"左子节点 比 右子节点大，当前节点不动，左子节点作为最大的子节点", k, arr[k], k+1, arr[k+1]);
			}
			if(arr[k] > temp) {//如果最大的子节点k比出发节点的值大
				log.info("子节点坐标：{} 值：{} 比 根节点值：{} 大，子节点的值上移交换到父节点", k, arr[k], temp);
				arr[i] = arr[k];//把最大的子节点k的值赋值给其父节点i，意味着较大的子节点k上浮到上一层，较小的父节点i下沉到下一层
				log.info("当前父节点坐标从：{} 移动到：{}", i, k);
				i = k;//从当前节点i的子节点k作为当前节点再向下遍历一层
			}
			else {//如果当前节点的最大的子节点k比出发节点的值小，说明再往下没有更大的值了，退出循环
				log.info("子节点坐标：{} 值：{} 比 根节点值：{} 小，最大的子节点比当前根节点小，终止本次向下层遍历", k, arr[k], temp);
				break;
			}
		}
		arr[i] = temp;//将以上遍历得出的最大值赋值给出发节点，完成交换。
		log.info("【建堆一次遍历】完成，当前数组：{}", arr);
	}

	public static BinaryTreeNode toTree(int[] source) {
		if(source.length == 0) {
			return null;
		}
		BinaryTreeNode root = new BinaryTreeNode().setVal(source[0]).setIndex(0);
		Deque<BinaryTreeNode> queue = new LinkedList<>();
		queue.offer(root);
		BinaryTreeNode cur;
		int lineLength = 2;
		int startIndex = 1;
		int restLength = source.length - 1;

		while (restLength > 0) {
			for(int i = startIndex; i < startIndex + lineLength; i = i + 2) {
				if(i == source.length) {
					return root;
				}
				cur = queue.poll();
				cur.setLeft(new BinaryTreeNode().setVal(source[i]).setIndex(i));
				queue.offer(cur.getLeft());
				if(i + 1 == source.length) {
					return root;
				}
				cur.setRight(new BinaryTreeNode().setVal(source[i + 1]).setIndex(i));
				queue.offer(cur.getRight());
			}
			startIndex += lineLength;
			restLength -= lineLength;
			lineLength = queue.size() * 2;
		}
		return root;
	}
}
