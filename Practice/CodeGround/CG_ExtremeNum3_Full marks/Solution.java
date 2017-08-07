/*
 * < ���� ���� �ľ� >
 * 
 * 1) Ŭ���� ���θ����� �ʱ�
 * 2) String concat �ݺ� --> Integer.parseInt() �� ��������
 *    �ٷ� System.out.print�ؼ� �� ���ھ� ����ϵ��� �ٲ�!!
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
			
			//1. �ڸ��� ���ϱ�
			int k = 1; // �� �ڸ���
			
			while (true) { // �ڸ��� ���ϱ�
				if ((Math.pow(2, k) <= (number + 1)) && ((number + 2) <= Math.pow(2, k + 1))) {
					break;
				} else {
					k++;
				}
					
			}
			
			//2. �ش� number�� ���� ���
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
