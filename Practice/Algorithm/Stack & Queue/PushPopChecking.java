package Stack;

import java.util.Stack;

class PushPop {
	Stack<Integer> stack = new Stack<>();
	
	public boolean checkSeq(int[] seq1, int[] seq2) {
		int index = 0;
		
		for (int i = 0; i < seq2.length; i++) {
			if (seq1[index] == seq2[i]) {
				index++;
				continue;
			} else {
				
				while (seq1[index] != seq2[i]) {
					stack.push(seq1[index]);
					index++;
				}
				
				int move = 1;
				while (!stack.isEmpty()) {
					if (i - move >= 0 && seq2[i - move] == stack.pop()) {
						move++;
					} 
				}
			}
		}
		
		if (stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}

public class PushPopChecking {
	public static void main(String[] args) {
		PushPop pp = new PushPop();
		int[] inputs1 = new int[]{1, 2, 3, 4, 5};
		int[] inputs2 = new int[]{4, 5, 3, 2, 1};
		System.out.println(pp.checkSeq(inputs1, inputs2));
	}
}

