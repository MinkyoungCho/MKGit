package Bj1657;

public class Dubu {
	int[][] matrix;
	int[][] priceMatrix = {{10, 8, 7, 5, 1}, {8, 6, 4, 3, 1}, {7, 4, 3, 2, 1}, {5, 3, 2, 2, 1}, {1, 1, 1, 1, 0}};
	boolean[][] mark;
	
	public Dubu(int[][] matrix) {
		this.matrix = matrix;
		this.mark = new boolean[matrix.length][matrix[0].length];
	}
	
	int maxPrice = -1;
	
	public void calcPrice(boolean[][] mark, int x, int y, int price) {
		if (x == matrix.length) { // ��
			if (price > maxPrice) {
//				System.out.println(x + " " + y + " " + price);
				if (price == 37) {
					for (int i = 0; i < mark.length; i++) {
						for (int j = 0; j < mark[0].length; j++) {
							System.out.print(mark[i][j] + " ");
						}
						System.out.println();
					}
				}
				maxPrice = price;
			}
			return;
		}
		
		/*		  
		 * �ʹݿ� ���� ���� ����!
		 * 1) �ߺ� checking �ذ�: �ߺ����� ���� �κ��� �־ ���� 51�̳� ���Դ� ����
		 * 2) return �߰�
		 *   : �̹� mark�� ���Ҵ� ���� ���ҷ� ��Ŀ�� �ѱ�� �ٸ� ������ �ϸ� �ȵ�(�ӹ� ��)
		 *     ���� return�� ���� �ʴ´ٸ� if {} ���� ����
		 *     �̴� �ⲯ ���� �ߺ� checking�� �ٽ� �����ϴ� ���� ��
		 *     ��, �ٽ� �������ҷ� ��Ŀ�� �ѱ�°� ���� ���� �ٽ� �ߺ�üŷ���� ����
		 *     --> ���!! 
		 *     ��Ŀ�� �Ѱ����� �װɷ� �ӹ��������Ƿ� return�ؼ� ������ �ߺ�üŷ �����ؾ���!!!
		 *     
		 */
		if (mark[x][y]) { // ������ marking �߾��ٸ�
			if (y + 1 < mark[0].length) {
				calcPrice(mark, x, y + 1, price); // ��Ŀ�� �ѱ��
			} else {
				calcPrice(mark, x + 1, 0, price);
			}
			return; // �ش� �Լ����� �� ���� �̹� marking�� �����̹Ƿ�, ���� ���ҷ� ��Ŀ�� �ѱ�� �ӹ� ��!
		}
		
		mark[x][y] = true;
		boolean[][] backUp = new boolean[matrix.length][matrix[0].length];
		for (int i = 0; i < mark.length; i++) {
			for (int j = 0; j < mark[0].length; j++) {
				backUp[i][j] = mark[i][j];
			}
		}
		
		// 2 * 1
		if (x + 1 < matrix.length) {
			mark[x + 1][y] = true;
		
			if (y + 1 < matrix[0].length) {
				calcPrice(mark, x, y + 1, price + priceMatrix[matrix[x][y] - 'A'][matrix[x + 1][y] - 'A']);
			} else {
				calcPrice(mark, x + 1, 0, price + priceMatrix[matrix[x][y] - 'A'][matrix[x + 1][y] - 'A']);
			}
		}
		
		for (int i = 0; i < mark.length; i++) {
			for (int j = 0; j < mark[0].length; j++) {
				mark[i][j] = backUp[i][j];
			}
		}
		
		// 1 * 2
		if (y + 1 < matrix[0].length && !mark[x][y + 1]) {
			mark[x][y + 1] = true;
			
			if (y + 2 < matrix[0].length) {
				calcPrice(mark, x, y + 2, price + priceMatrix[matrix[x][y] - 'A'][matrix[x][y + 1] - 'A']);		
			} else {
				calcPrice(mark, x + 1, 0, price + priceMatrix[matrix[x][y] - 'A'][matrix[x][y + 1] - 'A']);
			}
		}
		
		for (int i = 0; i < mark.length; i++) {
			for (int j = 0; j < mark[0].length; j++) {
				mark[i][j] = backUp[i][j];
			}
		}
		
		// 1 * 1
		if (y + 1 < matrix[0].length) {
			calcPrice(mark, x, y + 1, price);		
		} else {
			calcPrice(mark, x + 1, 0, price);
		}
	}
}
