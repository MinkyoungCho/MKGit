//// you can also use imports, for example:
//// import java.util.*;
//
//// you can write to stdout for debugging purposes, e.g.
//// System.out.println("this is a debug message");
//
//class Solution {
//	
//    static public int solution(int[] A) {
//    	int standard = 0, temp, count, diff;
//    	int res = 0;
//    	
//    	while (standard < A.length - 2) {
//    		temp = standard + 1;
//    		diff = A[temp] - A[standard];
//    		count = 2;
//    		
//    		while (temp + 1 < A.length && ((A[temp + 1] - A[temp]) == diff)) {
//    			temp ++;
//    			count ++;
//    		}
//    		
//    		if (count > 2) {
//    			res += (count - 2) * (count - 1) / 2;
//    		}
//    		
//    		standard = temp;
//    	}
//    	
//    	return res;
//    }
//}
//
//public class Main {
//	public static void main(String args[]) {
//		int[] inputs = {-1, 1, 3, 3, 3, 2, 1, 0};
//		System.out.println(Solution.solution(inputs));
//	}
//}