package Bj14502;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	
	public static int bfs(int[][] matrix, boolean[][] mark, int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(i, j)); // add --> mark �ٷ� �̾��ֱ�, �ٷ� ���ϸ� ���� if���� ������ ������ �������� �ʾҴٰ� �Ǵ�
		mark[i][j] = false;
		int res = 0;
		
		while (!queue.isEmpty()) {
			Point tmpPoint = queue.remove();
			
			if (tmpPoint.x - 1 >= 0 && mark[tmpPoint.x - 1][tmpPoint.y] && matrix[tmpPoint.x - 1][tmpPoint.y] == 0) {
				queue.add(new Point(tmpPoint.x - 1, tmpPoint.y));
				mark[tmpPoint.x- 1][tmpPoint.y] = false;
				res++;
			}
			if (tmpPoint.x + 1 < matrix.length && mark[tmpPoint.x + 1][tmpPoint.y] && matrix[tmpPoint.x + 1][tmpPoint.y] == 0) {
				queue.add(new Point(tmpPoint.x + 1, tmpPoint.y));
				mark[tmpPoint.x + 1][tmpPoint.y] = false;
				res++;
			}
			if (tmpPoint.y - 1 >= 0 && mark[tmpPoint.x][tmpPoint.y - 1] && matrix[tmpPoint.x][tmpPoint.y - 1] == 0) {
				queue.add(new Point(tmpPoint.x, tmpPoint.y - 1));
				mark[tmpPoint.x][tmpPoint.y - 1] = false;
				res++;
			}
			if (tmpPoint.y + 1 < matrix[0].length && mark[tmpPoint.x][tmpPoint.y + 1] && matrix[tmpPoint.x][tmpPoint.y + 1] == 0) {
				queue.add(new Point(tmpPoint.x, tmpPoint.y + 1));
				mark[tmpPoint.x][tmpPoint.y + 1] = false;
				res++;	
			}
			
		}
		
		return res;
	}

}
