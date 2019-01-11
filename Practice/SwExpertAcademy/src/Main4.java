import java.util.Stack;

class Solution {
    public static int solution(String S) {	
    	long result = -1;
    	String[] inputs = S.split(" ");
    	Stack<Integer> stack = new Stack<>();
    	
    	for (int i = 0; i < inputs.length; i++) {
    		if (inputs[i].equals("POP")) {
    			if (stack.isEmpty()) {
    				return -1;
    			}
    			stack.pop();
    			
    		} else if (inputs[i].equals("DUP")) {
    			if (stack.isEmpty()) {
    				return -1;
    			}
    			int temp = stack.peek();
    			stack.push(temp);
    			
    		} else if (inputs[i].equals("+")) {
    			if (stack.isEmpty()) {
    				return -1;
    			}
    			int first = stack.pop(); 
    			
    			if (stack.isEmpty()) {
    				return -1;
    			}
    			int second = stack.pop();
    			
    			int res = first + second;
    			if (res < (int) Math.pow(2, 20)) { //범위 안
        			stack.push(first + second);
    			} else {
    				return -1;
    			}
    			
    		} else if (inputs[i].equals("-")) {
    			if (stack.isEmpty()) {
    				return -1;
    			}
    			int first = stack.pop(); // top
    			
    			if (stack.isEmpty()) {
    				return -1;
    			}
    			int second = stack.pop();
    			
    			int res = first - second;
    			if (res >= 0) {
        			stack.push(first - second);
    			} else { // 음수
    				return -1;
    			}
    			
    		} else { // 숫자
    			stack.push(Integer.parseInt(inputs[i]));
    		} 
    	}
    	
    	if (stack.isEmpty()) {
			return -1;
		}
		return stack.pop();
    }
}

public class Main4 {
	public static void main(String args[]) {
		System.out.println(Solution.solution("13 DUP 4 POP 5 DUP + DUP + -"));
	}
}
