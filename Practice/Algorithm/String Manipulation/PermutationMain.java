package string_programming;
/*
 *  재귀알고리즘
 *  1) 현재 first 기준, 뒤 SubString에 대해서 permutation 수행 후 출력
 *  2) first swapping 
 *  3) 각 Swapped first기준, 뒤 SubString에 대해서 permutation 수행 후 출력
 */
class Permutation {
	
	public String swap(String string, int a, int b) { // string 내에 두 글자 SWAP
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
			System.out.println(input); // 끝까지 scan시 출력!
			return;
		}
		
		permutation(input, (first + 1), last); //현재 first를 first로 유지 & 뒷 sublist에 한해 permutation
		
		for (int i = (first + 1); i <= last; i++) {
				String swappedInput = swap(input, first, i); //first change by swapping
				permutation(swappedInput, first + 1, last); //swap된 first의 뒤 sublist에 대해 perm 수행
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
