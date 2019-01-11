package 회의실잡기;

import java.util.Scanner;

class Seminar {
	int ID;
	int start;
	int finish;

	public Seminar(int ID, int start, int finish) {
		this.ID = ID;
		this.start = start;
		this.finish = finish;
	}
}

public class Main2 {
	public static void sorting(Seminar[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minVal = arr[i].finish;
			int minIdx = i;

			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j].finish < minVal) {
					minIdx = j;
					minVal = arr[j].finish;
				}

				if (minIdx != i) {
					Seminar temp = arr[i];
					arr[i] = arr[minIdx];
					arr[minIdx] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		Seminar[] seminars = new Seminar[N];
		for (int i = 0; i < N; i++) {
			seminars[i] = new Seminar(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}

		// finish 시각에 따라 세미나 정렬
		sorting(seminars);

		int count = 1;
		boolean[] order = new boolean[N];
		Seminar current = seminars[0];	
		order[0] = true;
		
		// 그리디
		for (int i = 1; i < N; i++) {
			if (current.finish <= seminars[i].start) {
				order[i] = true;
				count ++;
				current = seminars[i];
				
			}
		}
		
		// 출력
		System.out.println(count);
		for (int i = 0; i < N; i++) {
			if (order[i]) {
				System.out.print(seminars[i].ID + " ");
				
			}
		}

	}
}