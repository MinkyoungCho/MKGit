//package ȸ�ǽ����;
//
//import java.util.Scanner;
//
//class Seminar {
//	int ID;a
//	int start;
//	int finish;
//
//	public Seminar(int ID, int start, int finish) {
//		this.ID = ID;
//		this.start = start;
//		this.finish = finish;
//	}
//}
//
//public class Main {
//	public static void sorting(Seminar[] arr) {
//		for (int i = 0; i < arr.length; i++) {
//			int minVal = arr[i].start;
//			int minIdx = i;
//
//			for (int j = i + 1; j < arr.length; j++) {
//				if (arr[j].start < minVal) {
//					minIdx = j;
//					minVal = arr[j].start;
//				}
//
//				if (minIdx != i) {
//					Seminar temp = arr[i];
//					arr[i] = arr[minIdx];
//					arr[minIdx] = temp;
//				}
//			}
//		}
//	}
//
//	public static void main(String[] args) {
//
//		Scanner sc = new Scanner(System.in);
//
//		int N = sc.nextInt();
//		Seminar[] seminars = new Seminar[N];
//		for (int i = 0; i < N; i++) {
//			seminars[i] = new Seminar(sc.nextInt(), sc.nextInt(), sc.nextInt());
//		}
//		int[][] list = new int[N][2]; // count, next ����
//		int maxIdx = N - 1; // ��� ����
//		int maxCount = 1;
//
//		// start �ð��� ���� ���̳� ����
//		sorting(seminars);
//
//		// DP, ������ ROW���� start, finish �ð� �� �� fill Table
//		list[N - 1][0] = 1;
//		list[N - 1][1] = -1;
//
//		for (int i = N - 2; i >= 0; i--) {
//			Seminar current = seminars[i];
//
//			for (int j = i + 1; j < N; j++) {
//				// ���� finish <= ���� start --> ���� count ++, next ����Ű��, break
//				if (current.finish <= seminars[j].start) {
//					list[i][0] = list[j][0] + 1;
//					list[i][1] = j;
//
//					if (list[i][0] > maxCount) {
//						maxCount = list[i][0];
//						maxIdx = i;
//					}
//					break;
//				}
//
//				// N - 1���� ������ --> 1, -1
//				if (j == N - 1) {
//					list[i][0] = 1;
//					list[i][1] = -1;
//				}
//			}
//		}
//
//		// ���
//		System.out.println(maxCount);
//		for (int i = maxIdx;; i = list[i][1]) {
//			System.out.print(seminars[i].ID + " ");
//			if (list[i][1] == -1) {
//				break;
//			}
//		}
//
//	}
//}