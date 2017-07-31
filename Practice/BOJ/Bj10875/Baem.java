package Bj10875;

public class Baem {
	int size;
	boolean[][] matrix;
	int direction;
	int lenOfLife = 0;
	int PosX;
	int PosY;
	
	public Baem(int size, boolean[][] matrix, int direction) {
		this.size = size;
		this.matrix = matrix;
		this.direction = direction;
		this.PosX = size;
		this.PosY = size;
	}
	
	public boolean move(int numOfSteps, char rotate) { // 살면 true 죽으면 false리턴
		// 움직이기 (사는 경우: 거리 update 및 회전 / 죽는 경우: 움직인 거리만 update 및 return)
		for (int i = 0; i < numOfSteps; i++) {
			switch(direction) {
			case 0:
				if (PosX - 1 >= 0 && !matrix[PosX - 1][PosY]) {
					matrix[--PosX][PosY] = true; 
					lenOfLife++;
				} else {
					lenOfLife ++;
					return false;
				}
				break;
				
			case 1:
				if (PosY - 1 >= 0 && !matrix[PosX][PosY - 1]) {
					matrix[PosX][--PosY] = true;
					lenOfLife++;
				} else {
					lenOfLife++;
					return false;
				}
				break;
				
			case 2:
				if (PosX + 1 < matrix.length && !matrix[PosX + 1][PosY]) {
					matrix[++PosX][PosY] = true;
					lenOfLife++;
				} else {
					lenOfLife++;
					return false;
				}
				break;
				
			case 3:
				if (PosY + 1 < matrix[0].length && !matrix[PosX][PosY + 1]) {
					matrix[PosX][++PosY] = true;
					lenOfLife++;
				} else {
					lenOfLife++;
					return false;
				}
				break;
			}
		}
	
		// 움직이고 나서 회전시키기
		switch (direction) { 
		case 0:
			if (rotate == 'L') {
				direction = 1;
			} else {
				direction = 3;
			}
			break;

		case 1:
			if (rotate == 'L') {
				direction = 2;
			} else {
				direction = 0;
			}
			break;

		case 2:
			if (rotate == 'L') {
				direction = 3;
			} else {
				direction = 1;
			}
			break;

		case 3:
			if (rotate == 'L') {
				direction = 0;
			} else {
				direction = 2;
			}
			break;
		}
		
		return true;
	}
}
