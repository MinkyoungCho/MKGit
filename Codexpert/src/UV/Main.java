package UV;

import java.util.Scanner;

class Node {
	int r, c;
	Node next = null;
	
	Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Queue {
Node front, rear;
	
	public void enqueue(int r, int c) {
		Node node = new Node(r, c);
		
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

	static int[] x = {-1, 1, 0, 0};
	static int[] y = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[] inputs = new String[N];
		int[][] uv = new int[N][N];
		int[][] min = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			inputs[i] = sc.next();
			for (int j = 0; j < N; j++) {
				uv[i][j] = inputs[i].charAt(j) - '0';
			}
		}
		
		Queue queue = new Queue();
		queue.enqueue(0, 0);
		min[0][0] = uv[0][0];
		
		while (!queue.isEmpty()) {
			Node temp = queue.dequeue();
			
			int tempX = temp.r;
			int tempY = temp.c;
			
			for (int i = 0; i < 4; i++) {
				if (tempX + x[i] < 0 || tempX + x[i] >= N|| tempY + y[i] < 0 || tempY + y[i] >= N) {
					continue;
				}
				
				if (min[tempX + x[i]][tempY + y[i]] == 0) {
					min[tempX + x[i]][tempY + y[i]] = min[tempX][tempY] + uv[tempX + x[i]][tempY + y[i]];
					queue.enqueue(tempX + x[i], tempY + y[i]);
				} else if (min[tempX][tempY] + uv[tempX + x[i]][tempY + y[i]] < min[tempX + x[i]][tempY + y[i]]) {
					min[tempX + x[i]][tempY + y[i]] = min[tempX][tempY] + uv[tempX + x[i]][tempY + y[i]];
					queue.enqueue(tempX + x[i], tempY + y[i]);
				}
			}
			
		}

		System.out.println(min[N - 1][N - 1]);
	}

}
