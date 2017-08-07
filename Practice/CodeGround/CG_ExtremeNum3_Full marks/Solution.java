/*
 * < 만점 원인 파악 >
 * 
 * 1) 클래스 따로만들지 않기
 * 2) String concat 반복 --> Integer.parseInt() 의 구현에서
 *    바로 System.out.print해서 한 숫자씩 출력하도록 바꿈!!
 * 
 */

package CG_ExtremeNum3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		line = reader.readLine();
		int numOfTests =Integer.parseInt(line);
		

		for (int i = 0; i < numOfTests; i++) {
			System.out.println("Case #" + (i + 1));
			int number = Integer.parseInt(reader.readLine());
			
			//1. 자릿수 구하기
			int k = 1; // 한 자릿수
			
			while (true) { // 자릿수 구하기
				if ((Math.pow(2, k) <= (number + 1)) && ((number + 2) <= Math.pow(2, k + 1))) {
					break;
				} else {
					k++;
				}
					
			}
			
			//2. 해당 number의 숫자 출력
			int tempNum = number - ((int)Math.pow(2, k) - 1);
			String res = "";
			
			while (--k >= 0) {
				if (tempNum < (int) Math.pow(2, k)) { 
					System.out.print("4");
				} else {
					System.out.print("7");
					tempNum -= (int) Math.pow(2, k);
				}
			}
			System.out.println();
		}

	}
}
