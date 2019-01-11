//import java.util.Arrays;
//
//class Solution {
//    public static int solution(int N, String S) {
//    	String[] inputs = S.split(" ");
//    	
//    	boolean[][] mark = new boolean[N + 1][4];
//    	for (int i = 1; i <= N; i++) {
//    		Arrays.fill(mark[i], false);
//    	}
//    	
//    	for (int i = 0; i < inputs.length; i++) {
//    		if (inputs[i].length() == 0) {
//    			break;
//    		}
//    		
//    		int row = Integer.parseInt(inputs[i].substring(0, inputs[i].length() - 1));
//    		char col = inputs[i].charAt(inputs[i].length() - 1);
//    		
//    		switch (col) {
//    		case 'A':
//    		case 'B':
//    		case 'C':
//    			mark[row][0]  = true;
//    			break;
//    			
//    		case 'D':
//    			mark[row][1]  = true;
//    			break;
//    			
//    		case 'E':
//    		case 'F':
//    			mark[row][1]  = true;
//    			mark[row][2]  = true;
//    			break;
//    			
//    		case 'G':
//    			mark[row][2]  = true;
//    			break;
//    			
//    		case 'H':
//    		case 'J':
//    		case 'K':
//    			mark[row][3]  = true;
//    			break;
//    		}
//    	}
//    	
//    	int result = 0;
//    	
//    	for (int i = 1; i <= N; i++) {
//    		if (!mark[i][0]) { //비었다면
//    			result ++;
//    		}
//    		if (!mark[i][1] || !mark[i][2]) { // 둘중 하나가 비었다면
//    			result ++;
//    		}
//    		if (!mark[i][3]) {
//    			result ++;
//    		}
//    	}
//    	
//    	return result;
//    }
//}
//
//public class Main3 {
//	public static void main(String args[]) {
//		System.out.println(Solution.solution(2, "1D 2G   "));
//	}
//}
