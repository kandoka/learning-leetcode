package practice;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {
	private int[][] chessBoard;
	private boolean[] isVisited;
	private boolean isFinished;
	private int X;
	private int Y;
	
	public static void main(String[] args) {
		HorseChessboard hc = new HorseChessboard();
		String s1 = "awake";
		StringBuffer sb1 = new StringBuffer(s1);
		StringBuffer sb2 = sb1.append("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(sb1==sb2);
		hc.init(8, 8);
		hc.travel(0, 0, 1);
		hc.show();
	}
	
	private void change(String str) {
		str += "bbb";
	}
	
	/**
	 * @param X 行数
	 * @param Y	列数
	 */
	private void init(int X, int Y) {
		this.X = X;
		this.Y = Y;
		chessBoard = new int[this.X][this.Y];
		isVisited = new boolean[this.X * this.Y];
		for(boolean b: isVisited) {
			b = false;
		}
		isFinished = false;
	}
	
	/**
	 * @param x 当前位置的行数
	 * @param y	当前位置的列数
	 * @param stepCount	当前走过的步数
	 */
	private void travel(int x, int y, int stepCount) {
		//标记该位置是第几步
		chessBoard[x][y] = stepCount;
		//标记该位置已经走过
		isVisited[x * Y + y] = true;
		//得到下一个能走的位置的数列
		ArrayList<Point> nexts = next(new Point(x, y));
		sort(nexts);
		//递归
		while(!nexts.isEmpty()) {
			Point nextPoint = nexts.remove(0);
			if(!isVisited[nextPoint.x * Y + nextPoint.y]) {
				travel(nextPoint.x, nextPoint.y, stepCount + 1);
			}//找到未访问的位置，继续往下走
		}//遍历下一个能走的位置的数列
		
		if(stepCount < X * Y && !isFinished) {
			chessBoard[x][y] = 0;
			isVisited[x * Y + y] = false;
		}//如果未走完棋盘，或正在处于回溯过程
		else {
			isFinished = true;
		}//
	}
	
	private void show() {
		//输出棋盘的最后情况
		for(int[] rows : chessBoard) {
			for(int step: rows) {
				System.out.print(step + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * @param curPoint	棋子当前位置
	 * @return	返回下一个能走的位置的数列
	 */
	private ArrayList<Point> next(Point curPoint){
		//存放下一个能走的位置的数列
		ArrayList<Point> ps = new ArrayList<Point>();
		Point nextPoint = new Point();
		//表示马儿可以走5这个位置
		if((nextPoint.x = curPoint.x - 2) >= 0 && (nextPoint.y = curPoint.y -1) >= 0) {
			ps.add(new Point(nextPoint));
		}
		//判断马儿可以走6这个位置
		if((nextPoint.x = curPoint.x - 1) >=0 && (nextPoint.y=curPoint.y-2)>=0) {
			ps.add(new Point(nextPoint));
		}
		//判断马儿可以走7这个位置
		if ((nextPoint.x = curPoint.x + 1) < X && (nextPoint.y = curPoint.y - 2) >= 0) {
			ps.add(new Point(nextPoint));
		}
		//判断马儿可以走0这个位置
		if ((nextPoint.x = curPoint.x + 2) < X && (nextPoint.y = curPoint.y - 1) >= 0) {
			ps.add(new Point(nextPoint));
		}
		//判断马儿可以走1这个位置
		if ((nextPoint.x = curPoint.x + 2) < X && (nextPoint.y = curPoint.y + 1) < Y) {
			ps.add(new Point(nextPoint));
		}
		//判断马儿可以走2这个位置
		if ((nextPoint.x = curPoint.x + 1) < X && (nextPoint.y = curPoint.y + 2) < Y) {
			ps.add(new Point(nextPoint));
		}
		//判断马儿可以走3这个位置
		if ((nextPoint.x = curPoint.x - 1) >= 0 && (nextPoint.y = curPoint.y + 2) < Y) {
			ps.add(new Point(nextPoint));
		}
		//判断马儿可以走4这个位置
		if ((nextPoint.x = curPoint.x - 2) >= 0 && (nextPoint.y = curPoint.y + 1) < Y) {
			ps.add(new Point(nextPoint));
		}
		return ps;
	}
	
	/**
	 * @param ps 要排序的数列
	 */
	public void sort(ArrayList<Point> ps) {
		ps.sort(new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return next(p1).size() - next(p2).size();
			}
		});
	}
}
