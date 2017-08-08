package PriorityQueue;

/*
 * �켱����ť���� ���� �ƴ� '��ü'�� ť�� ���ҷ� �� ���,
 * ��ü�� � ������ <������ ����>�� ������ ���ؾ��� --> Comparable<> �������̽� ����!
 */
public class Node implements Comparable<Node> {
	int x;
	int y; // ���� ����
	
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
		
		return 0; //������
	}
	
	
}
