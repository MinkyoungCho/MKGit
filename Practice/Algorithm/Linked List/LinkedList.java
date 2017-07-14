package data_structure;

/*
 * LinkedList ������ m��°
 * reverse
 * 
 * 
 */
import java.util.Scanner;

class Node {
	char data;
	Node nextNode = null;
	
	public Node(char data) {
		this.data = data;
	}
}

class Queue {
	Node front = null;
	Node rear = null;
	

	public int findMthFromEnd(int m) {
		Node temp = front;
		for (int i = 0; i < m; i++) { //temp ��ġ ����
			if (temp != null) {
				temp = temp.nextNode;
			} else {
				return -1;
			}
		}
		
		//�Բ� �����̱�
		while(temp.nextNode != null) {
			front = front.nextNode;
			temp = temp.nextNode;
		}
		
		return front.data;
	}
	
	public void reverse() {
//		Node head = front; // ���Ḯ��Ʈ ������ ��
		Node temp = null; //��ũ �����ϴ� ���
		Node tail = null; //������ ��ũ
		
		rear = front; //rear���� ��ġ ����
		
		while (front != null) { // tail, temp, front��ġ !
			tail = temp; //���� �߿�!
			temp = front;
			front = front.nextNode;
			temp.nextNode = tail;
		}
		
		front = temp;
	}
	
	public void enqueue(char x) {
		Node newNode = new Node(x);
		
		if (front == null) { //ó�� �ִ� ���
			front = rear = newNode;
		} else {
			rear.nextNode = newNode;
			rear = newNode;
			
		}
	}
	
	public char dequeue() {
		char temp = 0;
		
		if (front == null) {
			System.out.println("Stack is empty!!");
			return temp;
		}
		
		if (front == rear) {
			temp = front.data;
			front = rear = null;
			
		} else {
			temp = front.data;
			front = front.nextNode;
		}
		
		return temp;
	}
	
	public void traversal() {
		Node movingPointer = front;
		
		if(movingPointer != null) {
			while (movingPointer != null) {
				System.out.print(movingPointer.data + " ");
				movingPointer = movingPointer.nextNode;
			}
		} else {
			return;
		}
	}
	
	
	public int countLength(Node list) {
		int count = 0;
		Node temp = front;
		
		while (temp != null) {
			count ++;
			temp = temp.nextNode;
		}
		
		return count;
	}
	
	public boolean hasCycle() {
		// ���� - mark�迭 ���� - üũ�� �ߺ� �߻�: ��!
		
		int listLength = 0;
		Node temp = front;
		
		while (temp != null) {
			listLength ++;
			temp = temp.nextNode;
		}
		
		boolean[] mark = new boolean[listLength];
		temp = front;
		
		while (temp != null) {
			if (!mark[temp.data]) {
				mark[temp.data] = true;
				temp = temp.nextNode;
			} else {
				return true;
			}
		}
		return false;
	}
	
	public boolean searchAndDelete(char key) {
		Node temp = front;
		Node head = front;
		
		while(temp != null) {
			if ((char)(temp.data + '0') == key) {
				//delete
				head.nextNode = temp.nextNode;
				return true;
			} else {
				head = temp;
				temp = temp.nextNode;
			}
		}
		
		return false;
	}
	
	public int addTwoLists(Node list2) {
		String list1ToString = "";
		String list2ToString = "";
		
		Node temp = front;
		while (temp != null) {
			list1ToString = list1ToString.concat(temp.data + "");
			temp = temp.nextNode;
		}
		
		temp = list2;
		while (temp != null) {
			list2ToString = list2ToString.concat(Integer.toString(temp.data));
			temp = temp.nextNode;
		}
		 
		int list1ToInteger = Integer.parseInt(list1ToString);
		int list2ToInteger = Integer.parseInt(list2ToString);
		
		return (list1ToInteger + list2ToInteger);
	}
	
}

public class LinkedList {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
//		AppearingAtOnce appearingAtOnce = new AppearingAtOnce(input);
//		appearingAtOnce.checkAppearance();
//		String[] inputs = input.split(" ");
		
//		Queue queue1 = new Queue();
//		for (int i = 0; i < inputs.length; i++) {
//			queue1.enqueue(Integer.parseInt(inputs[i]));
//		}
//
//		input = scanner.nextLine();
//		inputs = input.split(" ");
//		Queue queue2 = new Queue();
//		for (int i = 0; i < inputs.length; i++) {
//			queue2.enqueue(Integer.parseInt(inputs[i]));
//		}

//		queue.reverse();
//		queue.traversal();
//		System.out.println(queue1.addTwoLists(queue2.front) + "");
	}
}
