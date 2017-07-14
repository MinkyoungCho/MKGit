//package BFSBasic;
//
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
//
//class Element {
//	int x;
//	int y;
//	int level;
//	
//	public Element(int x, int y, int l) {
//		this.x = x;
//		this.y = y;
//		level = l;
//	}
//}
//
//class BFS {
//	char[][] matrix;
//	boolean[][] mark;
//	int level = 0;
//	
//	//return the deepest level for the starting point(i, j)
//	public int bfs(int i, int j) { 
//		Queue<Element> queue = new LinkedList<>();
//		queue.add(new Element(i, j, 0));
//		mark[i][j] = true;
//		
//		int maxLevel = 0;
//		while(!queue.isEmpty()) {
//			Element e = queue.remove();
////			System.out.println("remove @ " + e.x + e.y);
//			
//			if ((e.x - 1 >= 0) && !mark[e.x - 1][e.y] && (matrix[e.x - 1][e.y] == 'L')) {
//				mark[e.x - 1][e.y] = true;
//				maxLevel = e.level + 1;
//				queue.add(new Element(e.x - 1, e.y, e.level + 1));
//			}
//			if ((e.x + 1 < matrix.length) && !mark[e.x + 1][e.y] && (matrix[e.x + 1][e.y] == 'L')) {
//				mark[e.x + 1][e.y] = true;
//				maxLevel = e.level + 1;
//				queue.add(new Element(e.x + 1, e.y, e.level + 1));
//			}
//			if ((e.y - 1 >= 0) && !mark[e.x][e.y - 1] && (matrix[e.x][e.y - 1] == 'L')) {
//				mark[e.x][e.y - 1] = true;
//				maxLevel = e.level + 1;
//				queue.add(new Element(e.x, e.y - 1, e.level + 1));
//			}
//			if ((e.y + 1 < matrix[0].length) && !mark[e.x][e.y + 1] && (matrix[e.x][e.y + 1] == 'L')) {
//				mark[e.x][e.y + 1] = true;
//				maxLevel = e.level + 1;
//				queue.add(new Element(e.x, e.y + 1, e.level + 1));
//			}
//			
//			
//		}
//		
//		return maxLevel;
//	}
//			
//}
//
//public class BFSTreasure {
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		int row = scanner.nextInt();
//		int col = scanner.nextInt();
//		
//		BFS bfs = new BFS();
//		bfs.matrix = new char[row][col];
//		bfs.mark = new boolean[row][col];
//		
//		String gar = scanner.nextLine();
//		
//		for (int i = 0; i < row; i++) {
//			String input = scanner.nextLine();
//			for (int j = 0; j < col; j++) {
//				bfs.matrix[i][j] = input.charAt(j);
//			}
//		}
//		
//		int max = -1;
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < col; j++) {
//				if (bfs.matrix[i][j] == 'L') {
//					int temp = bfs.bfs(i, j);
//					if (temp > max) {
//						max = temp;
//					}
//				}
//				
//				for (int m = 0; m < row; m++) {
//					for (int k = 0; k < col; k++) {
//						bfs.mark[m][k] = false;
//					}
//				}
//			}
//		}
//		
//		System.out.println(max);
//	}
//}
