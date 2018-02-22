import java.util.Scanner;

//Sudoku solution : Assuming there is only unique solution.
class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Sudoku {
	int a[][] = new int[9][9];

	Sudoku(int a[][]) {
		this.a = a;
	}

	public boolean isSafe(int x, int y, int number) {

		for (int i = 0; i < 9; i++) {
			if (a[i][y] == number)
				return false;
			if (a[x][i] == number)
				return false;
		}
		// square boxes
		int xBox = x / 3;
		int yBox = y / 3;
		for (int k = xBox * 3; k < (xBox * 3 + 3); k++) {
			for (int j = yBox * 3; j < (yBox * 3 + 3); j++) {
				if (a[k][j] == number)
					return false;
			}
		}
		return true;
	}

	public boolean solve() {
		Point point = findEmptyPoint();
		if (point == null)
			return true;
		for (int num = 0; num < 10; num++) {
			if (isSafe(point.x, point.y, num)) {
				a[point.x][point.y] = num;
				if (solve())
					return true;
				a[point.x][point.y] = 0;
			}
		}
		return false;
	}

	private Point findEmptyPoint() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (a[i][j] == 0) {
					return new Point(i, j);
				}
			}
		}
		return null;
	}

	public void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public static void main(String s[]) {
		// int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0
		// }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
		// { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0,
		// 9, 0, 6, 0, 0 },
		// { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2,
		// 0, 6, 3, 0, 0 } };
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t > 0) {
			int grid[][] = new int[9][9];
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			Sudoku sudoku = new Sudoku(grid);
			if (!sudoku.solve())
				System.out.println("Not solvable");
			else {
				sudoku.print();
			}
			t--;
		}

	}
}
