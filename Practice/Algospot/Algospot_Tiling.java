import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class Cell {
	int x; 
	int y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Tiling {
	int wide;
	int count = 0;
	boolean[][] mark;
	
	public Tiling(int wide) {
		this.wide = wide;
		mark = new boolean[4][wide];
	}
	
	public Cell hasEmptyCell(boolean[][] mark) {
		for (int i = 0; i < mark.length; i++) {
			for (int j = 0; j < mark[0].length; j++) {
				if (!mark[i][j]) {
					return new Cell(i, j);
				}
			}
		}
		
		return null;
	}
	
	public void tileGrid(boolean[][] mark) {
		boolean[][] backup = new boolean[mark.length][mark[0].length]; //매개변수행렬 백업
		for (int i = 0; i < mark.length; i++) {
			for (int j = 0; j < mark[0].length; j++) {
				backup[i][j] = mark[i][j];
			}
		}
		
		Cell currentCell;
		if ((currentCell = hasEmptyCell(mark)) == null) {
			count++;
			return;
		}
		
		if (currentCell.y < mark[0].length - 1 && !mark[currentCell.x][currentCell.y + 1]) { // ㅡ 모양 넣기
			mark[currentCell.x][currentCell.y] = true;
			mark[currentCell.x][currentCell.y + 1] = true;
			tileGrid(mark);
		}
		
		for (int i = 0; i < mark.length; i++) {
			for (int j = 0; j < mark[0].length; j++) {
				mark[i][j] = backup[i][j];
			}
		}
		
		if (currentCell.x < mark.length - 1 && !mark[currentCell.x + 1][currentCell.y]) { // ㅣ 모양 넣기
			mark[currentCell.x][currentCell.y] = true;
			mark[currentCell.x + 1][currentCell.y] = true;
			tileGrid(mark);
		}
		
	}
}

public class Algospot_Tiling {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
		String line = null;
		
		int testCase = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testCase; i++) {
			int input = Integer.parseInt(reader.readLine());
			System.out.println(input);
			
			//객체 생성 및 함수 호출 및 결과 출력
			Tiling tiling = new Tiling(input);
			tiling.tileGrid(new boolean[4][input]);
			System.out.println(tiling.count);
		}
		
	}

}
