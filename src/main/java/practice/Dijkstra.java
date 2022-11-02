package practice;

import java.util.Arrays;

public class Dijkstra {
	private int[] dis;
	private boolean[] visited;
	private int[] pre;
	
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
        Dijkstra d = new Dijkstra();
        d.init(matrix, N);
        d.findShortestPath(matrix, 2, N);
	}
	
	
	/**
	 * 	初始化各个数组
	 * @param matrix 图
	 * @param N 最大值
	 */
	public void init(int[][] matrix, int N) {
		dis = new int[matrix.length];
		visited = new boolean[matrix.length];
		pre = new int[matrix.length];
		
		for(int i = 0; i < matrix.length; i++) {
			dis[i] = N;
			visited[i] = false;
			pre[i] = -1;
		}
	}

	/**
	 * @param matrix 图
	 * @param start	出发顶点
	 * @param N	代表不连通
	 */
	public void findShortestPath(int[][] matrix, int start, int N) {
		int minLength = N;
		//标记为已访问
		visited[start] = true;
		dis[start] = 0;
		//遍历顶点start周围的顶点，更新距离
		pre[start] = start;
		for(int i = 0; i < matrix.length; i++) {
			if(matrix[i][start] < N) {
				dis[i] = matrix[i][start];
				pre[i] = start;
			}//如果当前顶点i与start连通
		}
		
		//广度优先遍历
		while(true) {
			int curMinVertex = -1;
			minLength = N;
			
			//找到当前未被访问过的，最短路径的顶点curMinVertex
			for(int i = 0; i < matrix.length; i++) {
				if(dis[i] < minLength && !visited[i]) {
					minLength = dis[i];
					curMinVertex = i;
				}
			}
			if(-1 == curMinVertex) {
				System.out.println("curMinVertex: "+curMinVertex);
				break;
			}//如果没有找到最短路径的顶点
			visited[curMinVertex] = true;
			
			//从curMinVertex开始搜索并更新路径距离
			for(int i = 0; i < matrix.length; i++) {
				if(!visited[i]) {
					if(matrix[i][curMinVertex] < N && dis[curMinVertex] + matrix[i][curMinVertex] < dis[i]) {
						dis[i] = dis[curMinVertex] + matrix[i][curMinVertex];
						pre[i] = curMinVertex;
					}//如果新的i点到start的距离比旧的短
				}//如果i没有被访问
			}//从当前最短顶点minVertex开始遍历周围的顶点
		}
		show();
	}
	
	private void show() {
		System.out.println("dis: " + Arrays.toString(dis));
		System.out.println("pre: " + Arrays.toString(pre));
		System.out.println("visited: " + Arrays.toString(visited));
	}
}

