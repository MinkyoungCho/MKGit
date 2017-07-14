package stack_queue;

import java.util.Stack;

class StackToQueue {
	Stack<Integer> stack1 = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();

	public void appendTail(int x) {
		stack1.push(x);
	}

	public int deleteHead() {
		if (stack2.isEmpty()) { //���� ������ �׳� pop ������ stack1���� �ű� �� pop
			while (!(stack1.isEmpty())) { 
				stack2.push(stack1.pop()); //stack 1���� ���� stack2�� �ֱ�!
			}
		}
		return stack2.pop();
	}
}

public class StackToQueueMain {
	public static void main(String[] args) {
		StackToQueue stq = new StackToQueue();
		stq.appendTail(3);
		stq.appendTail(2);
		stq.appendTail(1);
		System.out.println(stq.deleteHead());
		System.out.println(stq.deleteHead());

	}
}