import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/*
 * 문제: 원소의 합이 같아지도록 집합 분할하고 최대 합 출력
 * 
 * 테스트케이스
 * {1, 2, 3} --> {1, 2} & {3} --> 3
 * {1, 2, 3, 4} --> {1, 4} & {2, 3} --> 5
 * {1, 2, 3, 5} --> {2, 3} & {5} --> 5
 * {1, 2, 3, 6} --> {1, 2, 3} & {6} --> 6
 * {1, 2} --> -1
 * 
 */

class Partition {
	int[] inputs;
	HashMap<Integer, Integer> hashmap = new HashMap<>();
	
	public Partition(String[] inputs) {
		this.inputs = new int[inputs.length];
		
		for (int i = 0; i < inputs.length; i++) {
			this.inputs[i] = Integer.parseInt(inputs[i]);
		}
	}
	
	public boolean isPossible(int[] set, int goal, int index) {
		if (goal == 0) {
			return true;
		}
		
		if (index == set.length) { // 끝까지 읽었는데도 goal을 만족하지 못함 - false
			return false;
		}
		
		// 선택 하거나 안하거나! (2가지 경우의 수)
		boolean ret = (isPossible(set, goal - set[index], index + 1) || isPossible(set, goal, index + 1));
		return ret;
	}
	
	// set에서 해당 index 원소 제거하기
	public int[] makeNewArray(int[] set, int index) {
		int[] newArray = new int[set.length - 1];
		int j = 0;
		
		for (int i = 0; i < set.length; i++) {
			if (i == index) {
				continue;
			}
			newArray[j] = set[i];
			j++;
		}
		
		return newArray;
		
	}
	
	// 재귀호출
	public void scanArray(int[] set) {
		if (set.length == 0) {
			return;
		}
		
		int sum = 0;
		for (int i = 0; i < set.length; i++) {
			sum += set[i];
		}
		
		if (sum % 2 == 0) { // 짝수이면 (합/2)에 대하여 가능한 원소 조합이 있는지 찾기
			if (!hashmap.containsKey(sum / 2) && isPossible(set, sum / 2, 0)) { 
				hashmap.put(sum / 2, 1); // DP: 계산 결과 저장해서 중복계산 피하기
			}
		} else {
			int j = 0;
			for (int i = 0; i < set.length; i++) {
				// 원소 하나씩 제거해서 재귀 호출
				int[] newArray = makeNewArray(set, i);
				scanArray(newArray);
			}
		}
	}
	
	// 해시맵에서 최대 key값 뽑기
	public int findMaxSum() {
		
		Iterator<Integer> iterator = hashmap.keySet().iterator();
		
		int max = -1;
		int key;
		
		while (iterator.hasNext()) {
			if ((key = iterator.next()) > max) {
				max = key;
			}
		}
		
		return max;
	}
}

public class PartitionProblem {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		String input = scanner.nextLine();
		String[] inputs = input.split(" ");

		Partition partition = new Partition(inputs);
		partition.scanArray(partition.inputs);
		System.out.println(partition.findMaxSum());
		
	}

}
