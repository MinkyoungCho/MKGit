package CG_Race;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class Solution {
	static int Answer;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int N = sc.nextInt();
			HashMap<Integer, Integer> inputs = new HashMap<>();
			int num;
			
			for (int i = 0; i < N; i++) {
				num = sc.nextInt();
				if (!inputs.containsKey(num)) {
					inputs.put(num, 1);
				} else {
					inputs.put(num, inputs.get(num) + 1);
				}
			}
			
			Iterator iter = inputs.keySet().iterator();
			int res = 0, tempKey, tempVal;
			while (iter.hasNext()) {
				tempKey = (int) iter.next();
				tempVal = inputs.get(tempKey);
				if (tempVal % 2 == 1) { //È¦¼ö
					res = res ^ tempKey;
				}
			}


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(res);
		}
	}
}