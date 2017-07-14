package array_programming;

/*
 * BinarySearch����!!
 * ==key ��� turning ���� Ȯ�� �� mid index ����!
 * 
 */
import java.util.Scanner;

class BinaryChecking {
	int[] inputs;
	
	public BinaryChecking(String[] inputs) { //�� �κ� ����, �Լ����� �ٷ� input ���޹ޱ�! (�ð� ����)
		this.inputs = new int[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			this.inputs[i] = Integer.parseInt(inputs[i]);
		}
	}
	
	public int findTurningNumber(int first, int last) {
		int mid = (first + last) / 2;
		int result = 0;
		
		if ((inputs[mid] >= inputs[mid + 1]) && (inputs[mid] >= inputs[mid - 1])) {
			return mid;
		} else if(inputs[mid] > inputs[mid + 1]) {
			result = findTurningNumber(first, (mid - 1));
		} else {
			result = findTurningNumber((mid + 1), last);
		}
		
		return result; // ���� �Ǽ� ����!!
		
	}
}

public class TurningNumMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] inputs = input.split(" ");
		
		BinaryChecking binaryCheck = new BinaryChecking(inputs);
		System.out.println(binaryCheck.findTurningNumber(0, (inputs.length - 1)));
		
	}

}
