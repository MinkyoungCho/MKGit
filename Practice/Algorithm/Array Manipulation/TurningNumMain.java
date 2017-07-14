package array_programming;

/*
 * BinarySearch응용!!
 * ==key 대신 turning 조건 확인 후 mid index 리턴!
 * 
 */
import java.util.Scanner;

class BinaryChecking {
	int[] inputs;
	
	public BinaryChecking(String[] inputs) { //이 부분 생략, 함수에서 바로 input 전달받기! (시간 단축)
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
		
		return result; // 여기 실수 금지!!
		
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
