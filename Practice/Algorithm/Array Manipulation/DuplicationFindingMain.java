package array_programming;
/*
 * Array 내 중복 원소 찾기!
 * 1) Array 이용
 * 2) HashMap 이용
 */
import java.util.HashMap;

class DuplicationFinding {
	int[] array = { 1, 2, 3, 4, 5, 6, 7, 9, 9, 3 };
	
	public void findByArray() { // 범위가 1~n까지라면
		int[] countArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			countArray[i] = 0;
		}

		for (int i = 0; i < array.length; i++) {
			countArray[array[i]]++;
			if (countArray[array[i]] >= 2) {
				System.out.print(array[i] + " ");
			}
		}

	}

	public void findByHashMap() {
		HashMap<Integer, Integer> hashmap = new HashMap<>();

		for (int i = 0; i < array.length; i++) {
			if (hashmap.get(array[i]) == null) {
				hashmap.put(array[i], 1);
			} else {
				hashmap.put(array[i], (hashmap.get(array[i]) + 1)); //값 덮어씌우기
				System.out.print(array[i] + " "+ hashmap.get(array[i]));
			}
		}
	}


}

public class DuplicationFindingMain {
	public static void main(String[] args) {
		DuplicationFinding duplicationFinding = new DuplicationFinding();
		
//		duplicationFinding.findByArray();
		System.out.println();
		duplicationFinding.findByHashMap();
		
	}
}
