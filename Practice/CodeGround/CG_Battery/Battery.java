package CG_Battery;

public class Battery {
	int[][] lines;
	int numOfLines;
	int size = 100;
	
	public Battery(int[][] lines, int numOfLines) {
		this.lines = lines;
		this.numOfLines = numOfLines;
	}
	
	
	public int calcPowerDist(int x, int y) { // (x, y)에 전원이 있을때 최대 전원 거리 리턴
		int max = -1;
		
		for (int i = 0; i < numOfLines; i++) { // N개의 line에 대해 
			// 끝점 1에서의 거리
			int dist1 =  Math.max(Math.abs(x - lines[i][0]), Math.abs(x - lines[i][1]));
					
			// 끝점 2에서의 거리
			int dist2 = Math.max(Math.abs(x - lines[i][2]), Math.abs(x - lines[i][3]));
			
			// 둘 중 최소 값 선택
			int dist = Math.min(dist1, dist2);
			
			// 최대 전원거리 선택
			if (dist > max) {
				max = dist;
			}
		}
		
		return max;
	}
	
	public int findMinVal() {
		int min = size;
		
		for (int i = 0; i < size; i++) { // 판의 각 좌표에 대해 최대 전원 거리 계산--> 이 중 최소값 선택
			for (int j = 0; j < size; j++) {
				
				int res = calcPowerDist(i, j);
				if (res < min) {
					min = res;
				}
			}
		}
		
		return min;
	}
}
