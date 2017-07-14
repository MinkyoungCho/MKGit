package string_programming;
/*
 *  ��;˰���
 *  1) ���� first ����, �� SubString�� ���ؼ� permutation ���� �� ���
 *  2) first swapping 
 *  3) �� Swapped first����, �� SubString�� ���ؼ� permutation ���� �� ���
 */
class Permutation {
	
	public String swap(String string, int a, int b) { // string ���� �� ���� SWAP
		Character[] temp = new Character[string.length()];
		
		for (int i = 0; i < string.length(); i++) { //  string to array
			temp[i] = string.charAt(i);
		}
		
		Character tempChar = temp[a]; // swap
		temp[a] = temp[b];
		temp[b] = tempChar;
		
		String result = ""; // array to string
		for (int i = 0; i < string.length(); i++) { 
			result = result + temp[i];
		}
		return result;
	}
	
	public void permutation(String input, int first, int last) {
		String result = null;
		
		if (first == last) {
			System.out.println(input); // ������ scan�� ���!
			return;
		}
		
		permutation(input, (first + 1), last); //���� first�� first�� ���� & �� sublist�� ���� permutation
		
		for (int i = (first + 1); i <= last; i++) {
				String swappedInput = swap(input, first, i); //first change by swapping
				permutation(swappedInput, first + 1, last); //swap�� first�� �� sublist�� ���� perm ����
		}
	}
}

public class PermutationMain {
	public static void main(String[] args) {
		Permutation permute = new Permutation();
		String input = "abcd";
		permute.permutation(input, 0, input.length() - 1);
	}

}
