package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MST {
	public static void main(String[] args) {
		String[] cityname = {"北京","武汉","南京","上海","杭州","广州","深圳"};
		int max = Integer.MAX_VALUE;
		int[][] city= {
                { max, 8, 7, max, max, max, max }, //北京——武汉、南京
                { 8, max,6, max,9, 8,max }, //武汉——北京、南京、杭州、广州
                { 7, 6, max, 3,4, max,max }, //南京——北京、武汉、上海、杭州
                { max, max,3, max,2, max,max }, //上海——南京、杭州
                { max, 9,4, 2,max, max,10 }, //杭州——武汉、南京、上海、深圳
                { max, 8,max, max,max, max,2 }, //广州——武汉、深圳
                { max, max,max, max,10,2,max }//深圳——杭州、广州
        };// 地图
		usePrim(cityname, city, max);
		useKruskal(cityname, city, max);
	}
	
	public static void usePrim(String[] cityname, int[][] city, int max) {
		Prim prim = new Prim(cityname, city, max);
		prim.generateMST(0);		
	}
	public static void useKruskal(String[] cityname, int[][] city, int max) {
		Kruskal kruskal = new Kruskal(cityname, city, max);
		kruskal.generateMST(0);		
	}
}

class Prim {
	private List<Edge> edges;
	private boolean[] isVisited;
	private String[] names;
	
	public Prim(String[] cityname, int[][] city, int max) {
		this.edges = new ArrayList<Edge>();
		this.isVisited = new boolean[city.length];
		this.names = cityname;
		//构建图：边集合、已访问集合初始化
		for(int i = 0; i < city.length; i++) {
			this.isVisited[i] = false;
			for(int j = i + 1; j < city[i].length; j++) {
				if(city[i][j] != max) {
					this.edges.add(new Edge(i, j, city[i][j]));				
				}				
			}//遍历城市j，如果与城市i连通，则加入边集合
		}//以城市i为起点
	}
	
	public void generateMST(int start) {
		int minLength = Integer.MAX_VALUE;	
		isVisited[start] = true;
		int v1 = -1, v2 = -1;//记录两个顶点下标
		List<Edge> mst = new ArrayList<Edge>();
		for(int k = 0; k < isVisited.length; k++) {
			boolean isFound = false;//是否找到最短边
			for(int i = 0; i < isVisited.length; i++) {
				if(isVisited[i]) {
					for(Edge e: edges) {
						if(i == e.point1 || i == e.point2) {
							if(!(isVisited[e.point1] && isVisited[e.point2]) && e.length < minLength) {
								isFound = true;
								minLength = e.length;
								//加入结果集中
								//记录两个顶点
								v1 = e.point1;
								v2 = e.point2;
							}//当这条边的两个顶点之一未被访问且权值最小
						}//当在边集合中找到访问过的顶点i时						
					}//从边集合中寻找i的最短边					
				}//看哪个顶点i访问过
				
			}//遍历所有顶点
			if(isFound) {
				isVisited[v1] = true;
				isVisited[v2] = true;
				mst.add(new Edge(v1, v2, minLength));
			}//如果这一轮找到最短边				
			minLength = Integer.MAX_VALUE;
			isFound = false;
		}//循环直到找到n-1条最短边
		String str = "prim最短路径：";
		int minPathLength = 0;
		for(Edge e: mst) {
			str += "\n" + names[e.point1] + "->" + names[e.point2] + ": " + e.length;
			minPathLength += e.length;
		}
		System.out.println(str);
		System.out.println("prim最短路径长度为：" + minPathLength);
	}
	
	@Override
	public String toString() {
		String str = "";
		for(Edge e: edges) {
			str += e;
		}
		return str + " Length: " + edges.size();
	}
}

class Kruskal {
	private List<Edge> edges;
	private boolean[] isVisited;
	private String[] names;
	
	public Kruskal(String[] cityname, int[][] city, int max) {
		this.edges = new ArrayList<Edge>();
		this.isVisited = new boolean[city.length];
		this.names = cityname;
		//构建图：边集合、已访问集合初始化
		for(int i = 0; i < city.length; i++) {
			this.isVisited[i] = false;
			for(int j = i + 1; j < city[i].length; j++) {
				if(city[i][j] != max) {
					this.edges.add(new Edge(i, j, city[i][j]));				
				}				
			}//遍历城市j，如果与城市i连通，则加入边集合
		}//以城市i为起点
	}
	
	//给边集合排序
	public void sort() {
		edges.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				return e1.length - e2.length;
			}
		});
	}
	
	public void generateMST(int start) {
		int[] ends = new int[isVisited.length];//终点数组
		List<Edge> mst = new ArrayList<Edge>();
		//给边集合按长度排个序
		sort();
		//初始化并查集
		for(int i = 0; i < ends.length; i++) {
			ends[i] = i;
		}
		//构建最小生成树
		for(int i = 0; i < edges.size(); i++) {
			Edge e = edges.get(i);
			int p1 = findEnd(ends, e.point1);
			int p2 = findEnd(ends, e.point2);
			if(p1 != p2) {
				mst.add(e);
				ends[p1] = p2;
				System.out.println("ends: " + Arrays.toString(ends));
			}//如果两个顶点的终点不一致
		}//从边集合里最短的那条边开始遍历
		
		String str = "kruskal最短路径：";
		int minPathLength = 0;
		for(Edge e: mst) {
			str += "\n" + names[e.point1] + "->" + names[e.point2] + ": " + e.length;
			minPathLength += e.length;
		}
		System.out.println(str);
		System.out.println("kruskal最短路径长度为：" + minPathLength);
	}
	
	/**
	 * @param ends 终点数组，下标对应顶点，值对应其终点
	 * @param i 想要查找的顶点
	 * @return
	 */
	public int findEnd(int[] ends, int i) {
		if(ends[i] == i) {
			return i;
		}//如果终点是它自己，则找到终点
		else {
			ends[i] = findEnd(ends, ends[i]);
			return ends[i];
		}//否则递归查找，路径压缩
			
	}
	
	@Override
	public String toString() {
		String str = "";
		for(Edge e: edges) {
			str += e;
		}
		return str + " Length: " + edges.size();
	}
}

class Edge {
	public int point1;
	public int point2;
	public int length;
	
	public Edge(int point1, int point2, int length) {
		super();
		this.point1 = point1;
		this.point2 = point2;
		this.length = length;
	}

	@Override
	public String toString() {
		return "Edge [point1=" + point1 + ", point2=" + point2 + ", length=" + length + "]";
	}
	
}
