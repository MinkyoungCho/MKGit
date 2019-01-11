package µî»ê·Î;

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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int peakR = sc.nextInt();
		int peakC = sc.nextInt();
		int[][] mountain = new int[N + 2][N + 2];
		int[][] table = new int[N + 2][N + 2];
		
		int[] dR = {-1, 1, 0, 0};
		int[] dC = {0, 0, -1, 1};
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				mountain[i][j] = sc.nextInt();
				table[i][j] = 0x7fffffff;
			}
		}
		
		Queue queue = new Queue();
		
		int front = 0, rear = 0;
		for (int i = 0; i < N + 2; i++) {
			queue.enqueue(0, i);
			queue.enqueue(N + 1, i);
		}
		
		for (int i = 1; i <= N; i++) {
			queue.enqueue(i, 0);
			queue.enqueue(i, N + 1);
		}
		
		while(!queue.isEmpty()) {
			
			Node current = queue.dequeue();
			
			for (int i = 0; i < 4; i++) {
				int tempR = current.r + dR[i];
				int tempC = current.c + dC[i];
				
				if (tempR >= 1 && tempC >= 1 && tempR <= N && tempC <= N) {
					int move = mountain[current.r][current.c] - mountain[tempR][tempC];
					if (move < 0) {
						move *= move;
					}
					
					move += table[current.r][current.c];
					if (table[tempR][tempC] > move) {
						table[tempR][tempC] = move;
						queue.enqueue(tempR, tempC);
					}
				}
			}
		}
		
		System.out.println(table[peakR][peakC]);
	}

}
