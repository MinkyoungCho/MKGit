package stack_queue;

import java.util.Stack;

class StackToQueue {
	Stack<Integer> stack1 = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();

	public void appendTail(int x) {
		stack1.push(x);
	}

	public int deleteHead() {
		if (stack2.isEmpty()) { //원소 있으면 그냥 pop 없으면 stack1에서 옮긴 후 pop
			while (!(stack1.isEmpty())) { 
				stack2.push(stack1.pop()); //stack 1에서 빼서 stack2에 넣기!
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