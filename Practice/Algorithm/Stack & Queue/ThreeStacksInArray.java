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
		 
		//Stack 초기화 : 시작 포인트 빼고 모두 avail로 이어지게!
		
	}
	
	int availList = topIndices[numOfStacks];
	public void pushItem(int stack, int item) { 
		/*
		 *  1) Stack Full 체크
		 *  2) avail list에서 하나 떼어서 끝에 붙이기 : 첫 원소인 경우 아닌 경우로 나누기
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
//		 *  1) Stack Empty 체크
//		 *  2) top부분 원소를 빼서 avail 맨 앞에 넣기
//		 *  3) top Indices부분 바꾸기
//		 *  
//		 */
//		
//	}
}

public class ThreeStacksInArray {
	

}
