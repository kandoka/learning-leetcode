package practice;

import java.util.Arrays;

public class Floyd {

	private int[][] pre;
	private int[][] dis;
	
	public static void main(String[] args) {
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		//邻接矩阵
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = Integer.MAX_VALUE;// 表示不可以连接
		matrix[0]=new int[]{N,5,7,N,N,N,2};  
        matrix[1]=new int[]{5,N,N,9,N,N,3};  
        matrix[2]=new int[]{7,N,N,N,8,N,N};  
        matrix[3]=new int[]{N,9,N,N,N,4,N};  
        matrix[4]=new int[]{N,N,8,N,N,5,4};  
        matrix[5]=new int[]{N,N,N,4,5,N,6};  
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        
        Floyd f = new Floyd();
        f.init(matrix, N);
        f.findShortestPath(matrix, 0, N);
        f.show();
	}

	public void init(int[][] matrix, int N) {
		dis = new int[matrix.length][matrix[0].length];
		pre = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				dis[i][j] = matrix[i][j];
				if(i == j) {
					dis[i][j] = 0;
				}
			}
			Arrays.fill(pre[i], i);
		}
	}
	
	public void findShortestPath(int[][] matrix, int start, int N) {
		int curLength = N;
		for(int k = 0; k < matrix.length; k++) {
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix.length; j++) {
					if(dis[i][k] < N && dis[k][j] < N) {
						curLength = dis[i][k] + dis[k][j];
						if(curLength < dis[i][j]) {
							dis[i][j] = curLength;
							pre[i][j] = pre[k][j];//j的前驱变为k的前驱
						}//如果i->k + k->j小于之间i->j时
					}
				}//取终点顶点j
			}//取出发顶点i
		}//取中间顶点k
	}
	
	public void show() {
		//为了显示便于阅读，我们优化一下输出
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		for (int k = 0; k < dis.length; k++) {
			// 输出dis数组的一行数据
			for (int i = k; i < dis.length; i++) {
				System.out.print("| "+vertex[k]+"->"+vertex[i]+": " + dis[k][i]);
			}
			System.out.println();
			System.out.println("路径关系");
			for (int i = 0; i < pre.length; i++) {
				System.out.print("| "+vertex[i]+"->"+vertex[k]+": "+vertex[pre[k][i]]);
			}
			System.out.println();
			System.out.println("=======================================================================");

		}
	}
}
