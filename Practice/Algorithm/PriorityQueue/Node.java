package PriorityQueue;

/*
 * 우선순위큐에서 값이 아닌 '객체'가 큐의 원소로 들어갈 경우,
 * 객체의 어떤 변수가 <정렬의 기준>이 될지를 정해야함 --> Comparable<> 인터페이스 구현!
 */
public class Node implements Comparable<Node> {
	int x;
	int y; // 정렬 기준
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Node target) {
		if (y > target.y) {
			return 1;
		} else if (y < target.y) {
			return -1;
		}
		
		return 0; //같으면
	}
	
	
}
