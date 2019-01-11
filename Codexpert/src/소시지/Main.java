package 소시지;

import java.util.Scanner;

class Sausage {
	int length;
	int width;

	public Sausage(int length, int width) {
		this.length = length;
		this.width = width;
	}
}

public class Main {
	public static void sorting(Sausage[] arr) { //length로 정렬
		for (int i = 0; i < arr.length - 1; i++) {
			int minVal = arr[i].length;
			int minIdx = i;

			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j].length < minVal) {
					minIdx = j;
					minVal = arr[j].length;
				}
			}
			
			if (minIdx != i) {
				Sausage temp = arr[i];
				arr[i] = arr[minIdx];
				arr[minIdx] = temp;
			}
		}
	}
	
	public static int findCurrent(boolean[] mark) {
		for (int i = 0; i < mark.length; i++) {
			if (!mark[i]) {
				return i;
			}
		}
		
		return -1;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		Sausage[] sausages = new Sausage[N];
		for (int i = 0; i < N; i++) {
			sausages[i] = new Sausage(sc.nextInt(), sc.nextInt());
		}
		boolean[] mark = new boolean[N];
		int count = 0;
		
		// start 시각에 따라 세미나 정렬
		sorting(sausages);
		
		int idx;
		
		while (true) {
			idx = findCurrent(mark);
			if (idx == -1) {
				break;
			}
			
			mark[idx] = true;
			count++;
			int max = sausages[idx].width;
			
			for (int i = idx + 1; i < N; i++) {
				if ((!mark[i]) && (sausages[i].width >= max)) {
					mark[i] = true;
					max = sausages[i].width;
				}
			}
		} 
		// 출력
		System.out.println(count);

	}
}