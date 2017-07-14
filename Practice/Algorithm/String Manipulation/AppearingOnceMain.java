package string_programming;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * �ѹ��� ��Ÿ���� ���� ã��
 *   - ������ ¦������ ������ ��� XOR
 *   - 3���� ���: ��Ʈ���� ���ؼ� %3
 *  
 * ����: abaa, aaab, abcd
 * 1) Queue: ���� ������� ť�� ����
 * 2) Hashmap: Alphabet��Ÿ�� Ƚ�� ����
 * 
 * 3) �˰���: ���� ������� ť�� �����ϰ� ���߿� ��ť�ϸ鼭 Hashmap ���� 1�� �� ����
 * 
 */

class AppearanceCheck {
	String input;
	Queue<Character> queue = new LinkedList<>();
	HashMap<Character, Integer> hashmap = new HashMap<>();
	
	public AppearanceCheck(String input) {
		this.input = input;
	}
	
	public void scanInputElement(){ //��ĵ�ϸ鼭 ť�� �ؽ����̺� ����!
		for (int i = 0; i < input.length(); i++) {
			queue.add(input.charAt(i)); 
			
			if(!hashmap.containsKey(input.charAt(i))) { 
				hashmap.put(input.charAt(i), 1);
			} else {
				hashmap.put(input.charAt(i), hashmap.get(input.charAt(i)) + 1);
			}
		}
	}
	
	public char extractElement() { // ��ĵ �� - ��ť �����ϸ鼭 �˻�!!
		char temp = 0;
		char key;
		
		while (!queue.isEmpty()) {
			key = queue.remove();
//			System.out.println(key);
			if (hashmap.get(key) == 1) {
				return key;
			}
		}
		return temp;
	}	
	
}

public class AppearingOnceMain  {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		
		AppearanceCheck appearanceCheck = new AppearanceCheck(input);
		appearanceCheck.scanInputElement();
		System.out.println(appearanceCheck.extractElement());
	}
		
			
	
}