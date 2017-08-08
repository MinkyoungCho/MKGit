package PriorityQueue;

import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 2));
		pq.add(new Node(0, 0));
		pq.add(new Node(2, 3));
		pq.add(new Node(2, -1));
		pq.add(new Node(2, -2));

		// ����� y���ҿ� ���� ������ ���!
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			System.out.println(temp.x + " " + temp.y); 
		}
	}

}
