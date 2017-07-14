package stack_queue;

class TwoStacks {
	int[] array = new int[100];
	
	int top1 = -1;
	int top2 = array.length - 1;
	
	public void pushItem(int stack, int item) { // full check 후 삽입
		if (top1 + 1 >= top2) {
			System.out.println("Stack Full");
			return;
		}
		
		if (stack == 1) {
			array[++top1] = item;
		} else {
			array[--top2] = item;
		}

	}
	
	public int popItem(int stack) { //empty체크 후 pop
		if (stack == 1) {
			if (top1 == -1) {
				System.out.println("Stack1 Empty");
				return -1;
			} else {
				return array[top1--];
			}
		} else {
			if (top2 == array.length - 1) {
				System.out.println("Stack1 Empty");
				return -1;
			} else {
				return array[top2++];
			}
		}
	}
	
	
}
public class TwoStacksInArray {

}
