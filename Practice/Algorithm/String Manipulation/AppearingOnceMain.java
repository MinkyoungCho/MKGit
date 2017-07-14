package string_programming;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 한번만 나타나는 원소 찾기
 *   - 나머지 짝수번씩 더해진 경우 XOR
 *   - 3번인 경우: 비트끼리 더해서 %3
 *  
 * 예시: abaa, aaab, abcd
 * 1) Queue: 읽은 순서대로 큐에 삽입
 * 2) Hashmap: Alphabet나타난 횟수 저장
 * 
 * 3) 알고리즘: 읽은 순서대로 큐에 삽입하고 나중에 디큐하면서 Hashmap 개수 1인 것 리턴
 * 
 */

class AppearanceCheck {
	String input;
	Queue<Character> queue = new LinkedList<>();
	HashMap<Character, Integer> hashmap = new HashMap<>();
	
	public AppearanceCheck(String input) {
		this.input = input;
	}
	
	public void scanInputElement(){ //스캔하면서 큐와 해쉬테이블에 삽입!
		for (int i = 0; i < input.length(); i++) {
			queue.add(input.charAt(i)); 
			
			if(!hashmap.containsKey(input.charAt(i))) { 
				hashmap.put(input.charAt(i), 1);
			} else {
				hashmap.put(input.charAt(i), hashmap.get(input.charAt(i)) + 1);
			}
		}
	}
	
	public char extractElement() { // 스캔 끝 - 디큐 시작하면서 검사!!
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