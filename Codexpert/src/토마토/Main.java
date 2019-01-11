package 토마토;

import java.util.Scanner;

class Node {
	int row, col, level;
	Node next = null;
	
	Node(int row, int col, int level) {
		this.row = row;
		this.col = col;
		this.level = level;
	}
}

class Queue {
	Node front, rear;
	
	public void enqueue(int r, int c, int l) {
		Node node = new Node(r, c, l);
		
		if (front == null) {
			front = rear = node;
		} else {
			rear.next = node;
			rear = node;
		}
	}
	
	public Node dequeue() {
		Node res = front;
		
		if (front == rear) {
			front = rear = null;
		} else {
			front = front.next;
		}
		
		return res;
	}
	
	public boolean isEmpty() {
		if (front == null) {
			return true;
		} else {
			return false;
		}
	}
}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt(); //col
		int N = sc.nextInt(); //row
		
		int[][] matrix = new int[N][M];
		boolean[][] mark = new boolean[N][M];
		int totalT = 0;
		int maturedT = 0;
		
		Queue queue = new Queue();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				matrix[i][j] = sc.nextInt();
				
				if (matrix[i][j] != -1) {
					totalT ++;
					if (matrix[i][j] == 1) {
						maturedT ++;
						queue.enqueue(i, j, 0);
					} 
				}
				
			}
		}
		
		int youngT = totalT - maturedT;
		int l = 0;
	
		//모두 익었는가 
		if (totalT == maturedT) {
			System.out.println(0);
			return;
		}
		
		int count = 0;
		
		while (!queue.isEmpty()) {
			Node current = queue.dequeue();
			mark[current.row][current.col] = true;
			
			int i = current.row;
			int j = current.col;
			l = current.level;
			
			//위
			if (i - 1 >= 0 && matrix[i - 1][j] == 0 && !mark[i - 1][j]) {
				matrix[i - 1][j] = 1;
				count ++;
				queue.enqueue(i - 1, j, l + 1);
			}
			
			//아래
			if (i + 1 < N && matrix[i + 1][j] == 0 && !mark[i + 1][j]) {
				matrix[i + 1][j] = 1;
				count ++;
				queue.enqueue(i + 1, j, l + 1);
			}
			
			//왼
			if (j - 1 >= 0 && matrix[i][j - 1] == 0 && !mark[i][j - 1]) {
				matrix[i][j - 1] = 1;
				count ++;
				queue.enqueue(i, j - 1, l + 1);
			}
			
			//오른
			if (j + 1 < M && matrix[i][j + 1] == 0 && !mark[i][j + 1]) {
				matrix[i][j + 1] = 1;
				count ++;
				queue.enqueue(i, j + 1, l + 1);
			}
			
		}

		if (count != youngT) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(l);
	}

}
