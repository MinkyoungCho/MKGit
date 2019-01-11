//����� ������ �迭�� ������ "��������"�� �����!!
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Game2048 {
	static int N, Answer;
	static int[][] map;
	static int finalMax = -100000;
	
	static boolean isEqual(int[][] pre, int[][] now) {
		for (int i = 0; i < N; i ++) {
			for (int j = 0; j < N; j++) {
				if (pre[i][j] != now[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	static void play(int count, int mode) { //count ��° �̵�
		int[][] preMap = new int[N][N]; 
		for (int i = 0; i < N; i++) {
			preMap[i] = Arrays.copyOf(map[i], map[i].length);
		}
		
		int k = 0, first = 0, second = 0;
		
	/////////// �� ����
		for (int col = 0; col < N; col++) { //���ġ
			first = 0; second = 0; k = 0;
			
			for (k = 0; k < N; k++) {
				while (first < N && map[first][col] == 0) {first ++;}
				second = first + 1;
				if (first == N) {
					int temp = k;
					while (temp < N) {map[temp][col] = 0; temp ++;}
					break;
				}
				while (second < N && map[second][col] == 0) {second ++;}
				if (second == N) {
					int temp = k + 1;
					while (temp < N) {map[temp][col] = 0; temp ++;}
					map[k][col] = map[first][col];
					break;
				}
				
				if (map[first][col] == map[second][col]) {
					map[k][col] = 2 * map[first][col];
					first = second + 1;
					if (map[k][col] > finalMax) {
						finalMax = map[k][col];
					}
				} else { //�ٸ���
					map[k][col] = map[first][col];
					first = second;
					if (map[k][col] > finalMax) {
						finalMax = map[k][col];
					}
				}
				
			}
		}
		
		if (count < 5 && !isEqual(map, preMap)) {
			play(count + 1, 0);
		}
	/////////// �� ��
		
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(preMap[i], map[i].length);
		}
		
	/////////// �Ʒ� ����
		for (int col = 0; col < N; col++) { //���ġ
			first = N - 1; second = N - 1; k = N - 1;
			
			for (k = N - 1; k >= 0; k--) {
				while (first >= 0 && map[first][col] == 0) {first --;}
				second = first - 1;
				if (first == -1) {
					int temp = k;
					while (temp >= 0) {map[temp][col] = 0; temp --;}
					break;
				}
				while (second >= 0 && map[second][col] == 0) {second --;}
				if (second == -1) {
					map[k][col] = map[first][col];
					int temp = k - 1;
					while (temp >= 0) {map[temp][col] = 0; temp --;}
					break;
				}
				
				if (map[first][col] == map[second][col]) {
					map[k][col] = 2 * map[first][col];
					first = second - 1;
					if (map[k][col] > finalMax) {
						finalMax = map[k][col];
					}
				} else { //�ٸ���
					map[k][col] = map[first][col];
					first = second;
					if (map[k][col] > finalMax) {
						finalMax = map[k][col];
					}
				}
				
			}
		}
		
		if (count < 5 && !isEqual(map, preMap)) {
			play(count + 1, 1);
		}
	/////////// �Ʒ� ��

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(preMap[i], map[i].length);
		}
		
	/////////// ���� ����
		for (int row = 0; row < N; row++) { //���ġ
			first = N - 1; second = N - 1; k = N - 1;
			
			for (k = N - 1; k >= 0; k--) {
				while (first >= 0 && map[row][first] == 0) {first --;}
				second = first - 1;
				if (first == -1) {
					int temp = k;
					while (temp >= 0) {map[row][temp] = 0; temp --;}
					break;
				}
				while (second >= 0 && map[row][second] == 0) {second --;}
				if (second == -1) {
					map[row][k] = map[row][first];
					int temp = k - 1;
					while (temp >= 0) {map[row][temp] = 0; temp --;}
					break;
				}
				
				if (map[row][first] == map[row][second]) {
					map[row][k] = 2 * map[row][first];
					first = second - 1;
					if (map[row][k] > finalMax) {
						finalMax = map[row][k];
					}
					
				} else { //�ٸ���
					map[row][k] = map[row][first];
					first = second;
					if (map[row][k] > finalMax) {
						finalMax = map[row][k];
					}
				}
				
			}
		}
		
		if (count < 5 && !isEqual(map, preMap)) {
			play(count + 1, 2);
		}
	/////////// ���� ��
		
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(preMap[i], map[i].length);
		}
		
	/////////// �� ����
		for (int row = 0; row < N; row++) { //���ġ
			first = 0; second = 0; k = 0;
			
			for (k = 0; k < N; k++) {
				while (first < N && map[row][first] == 0) {first ++;}
				second = first + 1;
				if (first == N) {
					int temp = k;
					while (temp < N) {map[row][temp] = 0; temp ++;}
					break;
				}
				while (second < N && map[row][second] == 0) {second ++;}
				if (second == N) {
					map[row][k] = map[row][first];
					int temp = k + 1;
					while (temp < N) {map[row][temp] = 0; temp ++;}
					break;
				}
				
				if (map[row][first] == map[row][second]) {
					map[row][k] = 2 * map[row][first];
					first = second + 1;
					if (map[row][k] > finalMax) {
						finalMax = map[row][k];
					}
					
				} else { //�ٸ���
					map[row][k] = map[row][first];
					first = second;
					if (map[row][k] > finalMax) {
						finalMax = map[row][k];
					}
				}
				
			}
		}
		if (count < 5 && !isEqual(map, preMap)) {
			play(count + 1, 3);
		}
	/////////// �� ��
		
	}
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		N = sc.nextInt();
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		play(1, -1);
		System.out.println(finalMax);
	}
}