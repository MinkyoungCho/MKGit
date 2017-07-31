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
		if (x == matrix.length) { // 끝
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
		 * 초반에 에러 났던 이유!
		 * 1) 중복 checking 해결: 중복으로 들어가는 부분이 있어서 답이 51이나 나왔던 것임
		 * 2) return 추가
		 *   : 이미 mark된 원소는 다음 원소로 포커스 넘기고 다른 실행은 하면 안됨(임무 끝)
		 *     만약 return을 하지 않는다면 if {} 다음 실행
		 *     이는 기껏 없앤 중복 checking을 다시 실행하는 것이 됨
		 *     즉, 다시 다음원소로 포커스 넘기는거 잘한 다음 다시 중복체킹내용 실행
		 *     --> 노답!! 
		 *     포커스 넘겼으면 그걸로 임무끝났으므로 return해서 끝내야 중복체킹 방지해야함!!!
		 *     
		 */
		if (mark[x][y]) { // 이전에 marking 했었다면
			if (y + 1 < mark[0].length) {
				calcPrice(mark, x, y + 1, price); // 포커스 넘기기
			} else {
				calcPrice(mark, x + 1, 0, price);
			}
			return; // 해당 함수콜은 이 전에 이미 marking된 상태이므로, 다음 원소로 포커스 넘기면 임무 끝!
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
