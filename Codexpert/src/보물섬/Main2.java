package 보물섬;

import java.util.Scanner;

class Node {
	int r, c, dist;
	Node next = null;
	
	Node(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}
}

class Queue {
	Node front, rear;
	
	public void enqueue(int r, int c, int dist) {
		Node node = new Node(r, c, dist);
		
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

public class Main2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int numOfRow = sc.nextInt();
		int numOfCol = sc.nextInt();
		
		char[][] map = new char[numOfRow][numOfCol];
		String[] input = new String[numOfRow];
		int count = 0;
		
		Queue startQ = new Queue();
		
		for (int i = 0; i < numOfRow; i++) {
			input[i] = sc.next();
			
			for (int j = 0; j < numOfCol; j++) {
				map[i][j] = input[i].charAt(j);
				
				if (map[i][j] == 'L') {
					count ++;
					startQ.enqueue(i, j, 0);
				}
			}
		}
		
		boolean[][] flag;
		int maxDist = -1;
		
		int[] dR = {-1, 1, 0, 0};
		int[] dC = {0, 0, -1, 1};
		
		while (!startQ.isEmpty()) {
			Node start = startQ.dequeue();
			
			flag = new boolean[numOfRow][numOfCol]; //완전 초기화
			
			Queue queue = new Queue();
			queue.enqueue(start.r, start.c, 0);
			
			while (!queue.isEmpty()) { //bfs
				Node current = queue.dequeue(); //방문
				
				for (int dir = 0; dir < 4; dir++) {
					int tempR = current.r + dR[dir];
					int tempC = current.c + dC[dir];
					
					if (tempR >= 0 && tempC >= 0 && tempR < numOfRow && tempC < numOfCol) {
						if (map[tempR][tempC] == 'L' && !flag[tempR][tempC]) { //아직 방문하지 않은 육지
							if ((current.dist + 1) > maxDist) {
								maxDist = current.dist + 1; 
							}
							flag[tempR][tempC] = true;
							queue.enqueue(tempR, tempC, current.dist + 1); //방문 후보로 넣음
						}
					}
				}
			}

		}
		
		
		System.out.println(maxDist);
		
	}

}
