package BFSBasic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Element {
	int x;
	int y;
	int l;
	
	public Element(int x, int y, int l) {
		this.x = x;
		this.y = y;
		this.l = l;
	}
}

class BFS {
	int[][] matrix;
	boolean[][] mark;
	int count = -1;
	
	public void bfs() {
		int max = -1;
		Queue<Element> queue = new LinkedList<>();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					mark[i][j] = true;
					queue.add(new Element(i, j, 0));
					max = 0;
				}
			}
		}
	
		while (!queue.isEmpty()) {
			Element e = queue.remove();
//			System.out.println("!!!");
			
			if (e.l > max) {
				max = e.l;
			}
			
			if((e.x - 1 >= 0) && (!mark[e.x - 1][e.y]) && (matrix[e.x - 1][e.y] == 0)) {
				mark[e.x - 1][e.y] = true;
				queue.add(new Element(e.x - 1, e.y, e.l + 1));
			}
			
			if((e.x + 1 < matrix.length) && (!mark[e.x + 1][e.y]) && (matrix[e.x + 1][e.y] == 0)) {
				mark[e.x + 1][e.y] = true;
				queue.add(new Element(e.x + 1, e.y, e.l + 1));
			}			
			if((e.y - 1 >= 0) && (!mark[e.x][e.y - 1]) && (matrix[e.x][e.y - 1] == 0)) {
				mark[e.x][e.y - 1] = true;
				queue.add(new Element(e.x, e.y - 1, e.l + 1));
			}
			
			if((e.y + 1 < matrix[0].length) && (!mark[e.x][e.y + 1]) && (matrix[e.x][e.y + 1] == 0)) {
				mark[e.x][e.y + 1] = true;
				queue.add(new Element(e.x, e.y + 1, e.l + 1));
			}		
		}
		
		count = max;
	}
}

public class Tomato {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int row = scanner.nextInt();
		int col = scanner.nextInt();
		
		BFS bfs = new BFS();
		bfs.matrix = new int[row][col];
		bfs.mark = new boolean[row][col];
				
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				bfs.matrix[i][j] = scanner.nextInt();
			}
		}
		
		bfs.bfs();
		System.out.println(bfs.count);

	}

}
