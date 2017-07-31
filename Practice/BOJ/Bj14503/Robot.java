package Bj14503;

class Robot {
	int[][] matrix;
	boolean[][] mark;
	int r;
	int c;
	int direction;
	
	public Robot(int[][] matrix, boolean[][] mark, int r, int c, int direction ) {
		this.matrix = matrix;
		this.mark = mark;
		this.r = r;
		this.c = c;
		this.direction = direction;
		
	}
	
	public Current rotateRobot(int x, int y, int dir) {
		int count = 0;
		
		while (count < 4) { // rotation �˰���
			count ++;
			
			switch(dir) {
			case 0:
				dir = 3;
				if (y - 1 >= 0 && mark[x][y - 1]) {
					return new Current(x, y - 1, dir);
				} 
				break;
				
			case 1:
				dir = 0;
				if (x - 1 >= 0 && mark[x - 1][y]) {
					return new Current(x - 1, y, dir);
				}
				break;
				
			case 2:
				dir = 1;
				if (y + 1 < mark[0].length && mark[x][y + 1]) {
					return new Current(x, y + 1, dir);
				}
				break;
				
			case 3:
				dir = 2;
				if (x + 1 < mark.length && mark[x + 1][y]) {
					return new Current(x + 1, y , dir);
				}
				break;
			}
		}
		
		return null;
	}
	
	// û�ұⰡ �ش� ���¿� ���� �� �ɷ��� �� �� �ִ� ������ ����
	public int cleanRoom(int posX, int posY, int dir) {
		System.out.println(posX + " " + posY + " " + dir);
		int res = 0;
		if (mark[posX][posY]) { 
			mark[posX][posY] = false; // �ɷ���
			res = 1; // �ɷ��� �� ������ ���� 1 �߰�
		}
		
		Current current = rotateRobot(posX, posY, dir);
		if (current == null) { // �ڿ��� ���̸� �۵� ����, �ƴϸ� �ڷ� ����
			switch(dir) {
			case 0:
				if (posX + 1 < matrix.length && matrix[posX + 1][posY] == 0) {
					res = res + cleanRoom(posX + 1, posY, dir);
				}
				break;
			case 1:
				if (posY - 1 >= 0 && matrix[posX][posY - 1] == 0) {
					res = res + cleanRoom(posX, posY - 1, dir);
				}
				break;
			case 2:
				if (posX - 1 >= 0 && matrix[posX - 1][posY] == 0) {
					res = res + cleanRoom(posX - 1, posY, dir);
				}
				break;
			case 3:
				if (posY + 1 < matrix[0].length && matrix[posX][posY + 1] == 0) {
					res = res + cleanRoom(posX, posY + 1, dir);
				}
				break;
			}
			return res;
		}
		
		// ���� ��ġ���� ���� �� �ִ� ������ ���� = �ش�������� �������� �� �۰Ե� ������ ���� + 1(���� ��ġ�� �۴� ���) or 0(�� �۴� ���)
		return (res + cleanRoom(current.x, current.y, current.dir));
	}
}