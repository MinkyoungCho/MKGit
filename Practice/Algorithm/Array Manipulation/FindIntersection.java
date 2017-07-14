package array_programming;

/*
 * 두 ARRAY사이 공통 원소 찾기
 * 1) Merge()방식 이용: O(n+m) --> n과 m이 비슷할때
 * 2) 짧은 array 기준  이진탑색: O(nlogm) --> m이 매우 클때
 *
 */

class InterSection {
	int[] array1;
	int[] array2;

	public InterSection(int[] array1, int[] array2) {
		this.array1 = array1;
		this.array2 = array2;
	}

	public void findByMerge() {
		int i = 0;
		int j = 0;

		while ((i < array1.length) && (j < array2.length)) {
			if (array1[i] == array2[j]) {
				System.out.print(array1[i] + " ");
				i++;
				j++;
			} else if (array1[i] > array2[j]) {
				j++;
			} else {
				i++;
			}
		}
	}

	public void findByBinarySearch() {
		if (array1.length <= array2.length) {
			for (int i = 0; i < array1.length; i++) {
				binarySearch(array2, 0, array2.length - 1, array1[i]);
			}
		} else {
			for (int i = 0; i < array2.length; i++) {
				binarySearch(array1, 0, array1.length - 1, array2[i]);
			}
		}
	}

	public void binarySearch(int[] longArray, int first, int last, int key) {
		if (first > last) {

			System.out.println("Not Found");
			return;
		}
		int mid = (first + last) / 2;

		if (longArray[mid] == key) {
			System.out.print(key + " ");
			return;
		} else if (longArray[mid] < key) {
			binarySearch(longArray, (mid + 1), last, key);

		} else {
			binarySearch(longArray, first, (mid - 1), key);
		}

	}
}

public class FindIntersection {
	public static void main(String[] args) {
		int[] input1 = {1, 3, 5, 7, 8, 10, 11, 14};
		int[] input2 = {2, 3, 4, 5, 7, 10};
		
		InterSection interSection = new InterSection(input1, input2);
		interSection.findByMerge();
		System.out.println();
		
		interSection.findByBinarySearch();
		
		
	}
}	
