/*<명심 또 명심!!>
 *백업에 사용되는 모!!!!!!!!!!!!든!!!!!!!!!!!! 변수와 배열은 local, 재귀하나끝나면 바로뒤에 초기화!!!!!!!!!!!!
 *재귀에서 이해안되는 프로세스가 있다면 그건 백업잘못한거일 가능성 높음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *백업 잘못해서 독립적이어야할 나뭇가지들이 섞임!! 제발 조심!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *제발 조심하자!!!!!!
*/
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Zero {
	static int N, M, Answer;
	static char[][] map;
	static Point red, blue, goal;
	public static final int max = 100000;
	
	static class Point {
		int r, c;
		char color;
		
		Point(int r, int c, char color) {
			this.r = r;
			this.c = c;
			this.color = color;
		}
	}
	
	static int shake(int count, Point red, Point blue) {
		if (count == 10) {
			return max;
		} 
		
		char[][] backup = new char[N + 1][M + 1];
		for (int i = 0; i < backup.length; i++) {
			backup[i] = Arrays.copyOf(map[i], map[i].length);
		}
		Point backupR = new Point(red.r, red.c, 'R');
		Point backupB = new Point(blue.r, blue.c, 'B');
		int result = max, change = 0, numOfGoal = 0;
		
	///////////////////위 시작
		ArrayList<Point> up = new ArrayList<>();
		up.add(red);
		up.add(blue);
		
		Collections.sort(up, new Comparator<Point>() {
			@Override
			public int compare(Point first, Point second) {
				if (first.r < second.r) {
					return -1;
				} else if (first.r > second.r) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		change = 0;
		for (Point i : up) { // 기울인 후 위치 set
			while (map[--i.r][i.c] == '.') { }
			if (map[i.r][i.c] == 'O') {
				if (i.color == 'R') {
					numOfGoal += 1;
				} else {
					numOfGoal += 2 ;
				}
			} else { // 벽
				i.r++;
				if (i.color == 'R' && backupR.r != red.r ) {
					map[backupR.r][red.c] = '.';
					red.r = i.r;
					map[red.r][red.c] = 'R';
					change ++;
				} else if (i.color == 'B' && backupB.r != blue.r){
					map[backupB.r][blue.c] = '.';
					blue.r = i.r;
					map[blue.r][blue.c] = 'B';
					change ++;
				}
			}
		}
		
		if (numOfGoal == 1) {
			return 1;
		} else if (numOfGoal != 1) {
			return max;
		} else {
			if (change > 0) { //하나라도 변경 시 
				result = Math.min(result, 1 + shake(count + 1 , red, blue));
			}
		}
	///////////////////위 끝
		
		red.r = backupR.r;
		blue.r = backupB.r;
		for (int m = 0; m < map.length; m++) {
			map[m] = Arrays.copyOf(backup[m], backup[m].length);
		}
		
				
	///////////////////아래 시작
		ArrayList<Point> down = new ArrayList<>();
		down.add(red);
		down.add(blue);
		
		Collections.sort(down, new Comparator<Point>() { //r에 대해 내림차순하도록 정렬기준 변경 
			@Override
			public int compare(Point first, Point second) {
				if (first.r < second.r) {
					return 1;
				} else if (first.r > second.r) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		change = 0; 
		numOfGoal = 0;
		for (Point i : down) { // 기울인 후 위치 
			while (map[++i.r][i.c] == '.') { }
			if (map[i.r][i.c] == 'O') {
				if (i.color == 'R') {
					numOfGoal += 1;
				} else {
					numOfGoal += 2;
				}
			} else { // 벽
				i.r--;
				if (i.color == 'R' && backupR.r != red.r) {
					change ++;
					map[backupR.r][red.c] = '.';
					red.r = i.r;
					map[red.r][red.c] = 'R';
				} else if (i.color == 'B' && backupB.r != blue.r){
					change ++;
					map[backupB.r][blue.c] = '.';
					blue.r = i.r;
					map[blue.r][blue.c] = 'B';
				} 
			}
		}
		
		if (numOfGoal == 1) {
			return 1;
		} else if (numOfGoal != 1) {
			return max;
		} else {
			if (change > 0) { //하나라도 변경 시 
				result = Math.min(result, 1 + shake(count + 1 , red, blue));
			}
		}
	///////////////////아래 끝

		red.r = backupR.r;
		blue.r = backupB.r;
		for (int m = 0; m < map.length; m++) {
			map[m] = Arrays.copyOf(backup[m], backup[m].length);
		}
		
	///////////////////오른 시작
		ArrayList<Point> right = new ArrayList<>();
		right.add(red);
		right.add(blue);
		
		Collections.sort(right, new Comparator<Point>() { //c에 대해 내림차순하도록 정렬기준 변경 
			@Override
			public int compare(Point first, Point second) {
				if (first.c < second.c) {
					return 1;
				} else if (first.c > second.c) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		change = 0;
		for (Point i : right) { // 기울인 후 위치 
			while (map[i.r][++i.c] == '.') { }
			if (map[i.r][i.c] == 'O') {
				if (i.color == 'R') {
					numOfGoal += 1;
				} else {
					numOfGoal += 2;
				}
			} else { // 벽
				i.c--;
				if (i.color == 'R' && backupR.c != red.c) {
					change ++;
					map[red.r][backupR.c] = '.';
					red.c = i.c;
					map[red.r][red.c] = 'R';
				} else if (i.color == 'B' && backupB.c != blue.c){
					change ++;
					map[blue.r][backupB.c] = '.';
					blue.c = i.c;
					map[blue.r][blue.c] = 'B';
				}
			}
		}
		
		if (numOfGoal == 1) {
			return 1;
		} else if (numOfGoal != 1) {
			return max;
		} else {
			if (change > 0) { //하나라도 변경 시 
				result = Math.min(result, 1 + shake(count + 1 , red, blue));
			}
		}
	///////////////////오른 끝

		red.c = backupR.c;
		blue.c = backupB.c;
		for (int m = 0; m < map.length; m++) {
			map[m] = Arrays.copyOf(backup[m], backup[m].length);
		}

	///////////////////왼 시작
		ArrayList<Point> left = new ArrayList<>();
		left.add(red);
		left.add(blue);
		
		Collections.sort(left, new Comparator<Point>() { //c에 대해 오름차순
			@Override
			public int compare(Point first, Point second) {
				if (first.c < second.c) {
					return -1;
				} else if (first.c > second.c) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		change = 0;
		for (Point i : left) { // 기울인 후 위치 
			while (map[i.r][--i.c] == '.') { }
			if (map[i.r][i.c] == 'O') {
				if (i.color == 'R') {
					numOfGoal += 1;
				} else {
					numOfGoal += 2;
				}
			} else { // 벽
				i.c++;
				if (i.color == 'R' && backupR.c != red.c) {
					map[red.r][backupR.c] = '.';
					map[red.r][red.c] = 'R';
					change ++;
				} else if (i.color == 'B' && backupB.c != blue.c){
					map[blue.r][backupB.c] = '.';	
					map[blue.r][blue.c] = 'B';
					change ++;
				}
			}
			
		}
		
		if (numOfGoal == 1) {
			return 1;
		} else if (numOfGoal != 1) {
			return max;
		} else {
			if (change > 0) { //하나라도 변경 시 
				result = Math.min(result, 1 + shake(count + 1 , red, blue));
			}
		}
	///////////////////왼 끝

		return result;
	}

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));
	
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			map = new char[N + 1][M + 1];
			for (int i = 1; i <= N; i++) {
				String input = sc.next();
				for (int j = 1; j <= M; j++) {
					map[i][j] = input.charAt(j - 1);
					
					if (map[i][j] == 'R') {
						red = new Point(i, j, 'R');
					} else if (map[i][j] == 'B') {
						blue = new Point(i, j, 'B');
					} else if (map[i][j] == 'O') {
						goal = new Point(i, j, 'O');
					}
				}
			}
			
			int res = shake(0, red, blue);
			if (res >= max) {
				res = -1;
			}

			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(res);
		}
	}
}