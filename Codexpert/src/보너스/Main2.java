package 보너스;

import java.util.Scanner;

class Person {
	int ID; 
	int numOfSub;
	int pay;
	
	public Person(int ID, int numOfSub) {
		this.ID = ID;
		this.numOfSub = numOfSub;
	}
}

public class Main2 {
	static int N, M;
	static boolean[][] relationship;
	
	public static void sorting(int[] arr) { // 1번째 원소부터 내림차순 정렬
		for (int i = 1; i < arr.length - 1; i++) {
			int maxVal = arr[i];
			int maxIdx = i;

			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > maxVal) {
					maxIdx = j;
					maxVal = arr[j];
				}
			}
			
			if (maxIdx != i) {
				int temp = arr[i];
				arr[i] = arr[maxIdx];
				arr[maxIdx] = temp;
			}
		}
	}
	
	public static void sorting(Person[] people, int mode) { // sub명수 따른 내림차순 정렬
		if (mode == 1) {
			for (int i = 1; i < people.length; i++) {
				int maxVal = people[i].numOfSub;
				int maxIdx = i;

				for (int j = i + 1; j < people.length; j++) {
					if (people[j].numOfSub > maxVal) {
						maxIdx = j;
						maxVal = people[j].numOfSub;
					}
				}
				
				if (maxIdx != i) {
					Person temp = people[i];
					people[i] = people[maxIdx];
					people[maxIdx] = temp;
				}
			}		
		} else {
			for (int i = 1; i < people.length; i++) {
				int minVal = people[i].ID;
				int minIdx = i;

				for (int j = i + 1; j < people.length; j++) {
					if (people[j].ID < minVal) {
						minIdx = j;
						minVal = people[j].ID;
					}
				}
				
				if (minIdx != i) {
					Person temp = people[i];
					people[i] = people[minIdx];
					people[minIdx] = temp;
				}
			}		
		}
	}
	
	public static boolean hasSub(int ID) {
		for (int i = 1; i <= N; i++) {
			if (relationship[ID][i]) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean[] findSub(int ID) {
		if (!hasSub(ID)) {
			return new boolean[N + 1];
		} 
		
		boolean[] temp;
		for (int i = 1; i <= N; i++) {
			if (relationship[ID][i]) {
				temp = findSub(i);
				
				for (int j = 1; j <= N; j++) {
					relationship[ID][j] = relationship[ID][j] || temp[j];
				}
			}
		}
		
		return relationship[ID];
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		relationship = new boolean[N + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			relationship[sc.nextInt()][sc.nextInt()] = true;
		}
		
		int[] pay = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			pay[i] = sc.nextInt();
		}
		
		// 금액 내림차순
		sorting(pay);
		
		// table에서 두번째 row부터 따라가며 다시 채우기
		findSub(1);
			
		//부하 직원 명수 세기
		Person[] people = new Person[N + 1];
		
		for (int i = 1; i <= N; i++) {
			int count = 0;
			for (int j = 1; j <= N; j++) {
				if (relationship[i][j]) {
					count++;
				}
			}
			people[i] = new Person(i, count); // ID, 부하직원 
		}
		
		
		sorting(people, 1); // 부하직원 명수 기준 내림차순, 금액과 매칭
		for (int i = 1; i <= N; i++) {
			people[i].pay = pay[i];
		}
		
		sorting(people, 2); // ID 오름차순
		for (int i = 1; i <= N; i++) {
			System.out.print(people[i].pay + " ");
		}
		
	}
}
