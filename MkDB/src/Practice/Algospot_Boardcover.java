package Practice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
		
class Filling {
	int row;
	int col;
	char[][] matrix;
	
	public Filling(int row, int col, char[][] matrix, boolean[][] mark) {
		this.row = row;
		this.col = col;    
		this.matrix = matrix;
	}
	
	int count = 0;

	public Point findWhite(boolean[][] mark) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (!mark[i][j]) {
					return (new Point(i, j));  // ÇöÀç Ä­ÀÌ ºóÄ­ÀÌ¸é ¸®ÅÏ
				}
			}
		}
		return null; // ºóÄ­ ¾øÀ½
	}
	
	
	public void fillMatrix(boolean[][] mark) {
		//backup!!
		boolean[][] backup = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				backup[i][j] = mark[i][j];
			}
		}
		
		Point currentPoint = findWhite(mark); // ÈòÄ­ Ã£±â
		if (currentPoint == null) { // ºóÄ­ ¾øÀ½
			count++; // Èò Ä­À» ¸ðµÎ Ã¤¿ì´Â °æ¿ì ¹ß°ß!
			return;
		} 

		// ÇöÀç Èò Ä­¿¡ ´ëÇØ 4°¡Áö ¸ð¾çÀÌ µé¾î°¥ ¼ö ÀÖ´ÂÁö °ËÅä & °¡´ÉÇÑ ¸ð¾çÀ» Ã¤¿ì°í Àç±Í È£Ãâ
		// L ÁÂ¿ì´ëÄª
		if (currentPoint.x + 1 < row && currentPoint.y - 1 >= 0 && mark[currentPoint.x + 1][currentPoint.y] == false && mark[currentPoint.x + 1][currentPoint.y - 1] == false) {
			mark[currentPoint.x][currentPoint.y] = true;
			mark[currentPoint.x + 1][currentPoint.y] = true;
			mark[currentPoint.x + 1][currentPoint.y - 1] = true;
			fillMatrix(mark); // º¯Çü mark
			
		}
		// L
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				mark[i][j] = backup[i][j];
			}
		}
		
		if (currentPoint.x + 1 < row && currentPoint.y + 1 < col && mark[currentPoint.x + 1][currentPoint.y] == false && mark[currentPoint.x + 1][currentPoint.y + 1] == false) {
			mark[currentPoint.x][currentPoint.y] = true;
			mark[currentPoint.x + 1][currentPoint.y] = true;
			mark[currentPoint.x + 1][currentPoint.y + 1] = true;
			fillMatrix(mark);
		}
		
		//¤¡
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				mark[i][j] = backup[i][j];
			}
		}
		
		if (currentPoint.x + 1 < row && currentPoint.y + 1 < col && mark[currentPoint.x][currentPoint.y + 1] == false && mark[currentPoint.x + 1][currentPoint.y + 1] == false) {
			mark[currentPoint.x][currentPoint.y] = true;
			mark[currentPoint.x][currentPoint.y + 1] = true;
			mark[currentPoint.x + 1][currentPoint.y + 1] = true;
			fillMatrix(mark);
		}
		
		//¤¡ ÁÂ¿ì´ëÄª
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				mark[i][j] = backup[i][j];
			}
		}
		
		if (currentPoint.x + 1 < row && currentPoint.y + 1 < col && mark[currentPoint.x + 1][currentPoint.y] == false && mark[currentPoint.x][currentPoint.y + 1] == false) {
			mark[currentPoint.x][currentPoint.y] = true;
			mark[currentPoint.x + 1][currentPoint.y] = true;
			mark[currentPoint.x][currentPoint.y + 1] = true;
			fillMatrix(mark);
		}
		
	}
}

public class Algospot_Boardcover {

	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		int testCase = Integer.parseInt(reader.readLine().trim());
		for (int i = 0; i < testCase; i++) {
			line = reader.readLine().trim();
			String[] inputs = line.split(" ");
			
			int row = Integer.parseInt(inputs[0]);
			
			int col = Integer.parseInt(inputs[1]);
			System.out.println(row +  " " + col);
			char[][] matrix = new char[row][col];
			boolean[][] mark = new boolean[row][col];
			
			for (int j = 0; j < row; j++) {
				line = reader.readLine().trim();
				for (int k = 0; k < col; k++) {
					matrix[j][k] = line.charAt(k);
					if (matrix[j][k] == '#') {
						mark[j][k] = true;
					}
				}
			}

			Filling filling = new Filling(row, col, matrix, mark);
			filling.fillMatrix(mark);
			System.out.println(filling.count);
		}
	}

}
