package stack_queue;

import java.util.Scanner;
import java.util.Stack;

class MinStack {
	int minValue = 100;
	Stack<Integer> stack = new Stack<>();
	Stack<Integer> stackOfMin = new Stack<>();

	public void pushItem(int item) { // O(1)
		stack.push(item);
		if (item < minValue) {
			minValue = item;
		}
		stackOfMin.push(minValue);
	}

	public int popItem() { // O(1)
		if (!stack.isEmpty()) {
			stackOfMin.pop();
			return stack.pop();
		}
		return -1;
	}

	public int getMinValue() {
		int temp = stackOfMin.pop();
		stackOfMin.push(temp);
		return temp;
	}
}

public class MinStackMain {
	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.pushItem(3);
		minStack.pushItem(4);
		minStack.pushItem(5);
		minStack.pushItem(6);
		minStack.pushItem(2);
		int a = minStack.popItem();
		System.out.println(minStack.getMinValue());
	}
}