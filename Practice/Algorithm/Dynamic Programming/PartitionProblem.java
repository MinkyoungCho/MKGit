import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/*
 * ����: ������ ���� ���������� ���� �����ϰ� �ִ� �� ���
 * 
 * �׽�Ʈ���̽�
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
		
		if (index == set.length) { // ������ �о��µ��� goal�� �������� ���� - false
			return false;
		}
		
		// ���� �ϰų� ���ϰų�! (2���� ����� ��)
		boolean ret = (isPossible(set, goal - set[index], index + 1) || isPossible(set, goal, index + 1));
		return ret;
	}
	
	// set���� �ش� index ���� �����ϱ�
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
	
	// ���ȣ��
	public void scanArray(int[] set) {
		if (set.length == 0) {
			return;
		}
		
		int sum = 0;
		for (int i = 0; i < set.length; i++) {
			sum += set[i];
		}
		
		if (sum % 2 == 0) { // ¦���̸� (��/2)�� ���Ͽ� ������ ���� ������ �ִ��� ã��
			if (!hashmap.containsKey(sum / 2) && isPossible(set, sum / 2, 0)) { 
				hashmap.put(sum / 2, 1); // DP: ��� ��� �����ؼ� �ߺ���� ���ϱ�
			}
		} else {
			int j = 0;
			for (int i = 0; i < set.length; i++) {
				// ���� �ϳ��� �����ؼ� ��� ȣ��
				int[] newArray = makeNewArray(set, i);
				scanArray(newArray);
			}
		}
	}
	
	// �ؽøʿ��� �ִ� key�� �̱�
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
