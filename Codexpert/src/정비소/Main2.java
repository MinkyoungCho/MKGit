package 정비소;

import java.util.Scanner;

//class Node {
//	int shop;
//	Node next = null;
//	
//	Node(int shop) {
//		this.shop = shop;
//	}
//}
//
//class Queue {
//	Node front = null, rear = null;
//	
//	void enqueue(int shop) {
//		Node node = new Node(shop);
//		if (front == null) {
//			front = rear = node;
//		} else {
//			rear.next = node;
//			rear = node;
//		}
//	}
//	
//	Node dequeue() {
//		Node node = front;
//		
//		if (front == rear) {
//			front = rear = null;
//		} else {
//			front = front.next;
//		}
//		
//		return front;
//	}
//}

public class Main2 {
	static int capacity, N, distance;
	static int[] posOfShops, timeOfShop;
	static int minDuration = 1000000000;
	static int numOfShops = -1;
	static boolean[] listOfShops;
	
	public static void findNextShop(int ID, int currentCap, int duration, int num, boolean[] mark) {
		if (ID == N + 1) { // 목적지 도착
			if (duration < minDuration) {
				minDuration = duration;
				numOfShops = num;
				for (int i = 1; i <= N; i++) {
					listOfShops[i] = mark[i];
				}
			}
			return;
		}
		
		if (ID != 0) {
			currentCap += capacity;
			num += 1;
		}
		
		for (int i = 1; (ID + i <= N + 1) && (posOfShops[ID + i] <= posOfShops[ID] + capacity); i++) {
			mark[ID + i] = true;
			findNextShop(ID + i, currentCap - (posOfShops[ID + i] - posOfShops[ID]), duration + timeOfShop[ID + i], num, mark);
			mark[ID + i] = false;
			
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		capacity = sc.nextInt();
		N = sc.nextInt();
		distance = 0;
		listOfShops = new boolean[N + 2];
		
		posOfShops = new int[N + 2];
		posOfShops[0] = 0;
		
		for (int i = 1; i < N + 2; i++) {
			int interval = sc.nextInt();
			posOfShops[i] = posOfShops[i - 1] + interval;
		}
		
		timeOfShop = new int[N + 2];
		for (int i = 1; i < N + 1; i++) {
			timeOfShop[i] = sc.nextInt();
		}
		timeOfShop[N + 1] = 0;
		
		boolean[] mark = new boolean[N + 2];
		findNextShop(0, capacity, 0, 0, mark);
		
		System.out.println(minDuration);
		System.out.println(numOfShops);
		
		for (int i = 1; i <= N; i++) {
			if (mark[i]) {
				System.out.print(i + " ");
			}
		}
	}
}
