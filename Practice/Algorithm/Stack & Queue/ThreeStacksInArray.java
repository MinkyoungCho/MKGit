package stack_queue;

class Node {
	int item;
	int nextNodeIndex;
	
	public Node(int x) {
		item = x;
	}
}

class MultipleStacks {
	int numOfStacks;
	int[] topIndices;
	Node[] array = new Node[100];
	
	public MultipleStacks(int numOfStacks) {
		this.numOfStacks = numOfStacks;
		topIndices = new int[numOfStacks + 1];
		for (int i = 0; i <= numOfStacks; i++) {
			topIndices[i] = i;
		}
		 
		//Stack �ʱ�ȭ : ���� ����Ʈ ���� ��� avail�� �̾�����!
		
	}
	
	int availList = topIndices[numOfStacks];
	public void pushItem(int stack, int item) { 
		/*
		 *  1) Stack Full üũ
		 *  2) avail list���� �ϳ� ��� ���� ���̱� : ù ������ ��� �ƴ� ���� ������
		 */
		if (array[availList] == null) { 
			System.out.println("Stack Full");
			return;
		} else {
	
//			for (; array[].nextNodeIndex != -1;)
		}
	}
	
//	public int popItem(int stack) {
//		/*
//		 *  1) Stack Empty üũ
//		 *  2) top�κ� ���Ҹ� ���� avail �� �տ� �ֱ�
//		 *  3) top Indices�κ� �ٲٱ�
//		 *  
//		 */
//		
//	}
}

public class ThreeStacksInArray {
	

}
