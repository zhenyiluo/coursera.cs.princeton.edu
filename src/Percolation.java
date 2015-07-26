public class Percolation {

	private static final int[] DX = { -1, 1, 0, 0 };
	private static final int[] DY = { 0, 0, -1, 1 };
	private WeightedQuickUnionUF wquf;
	private WeightedQuickUnionUF wqufFull;
	private boolean[][] isOpenMark;
	private int col = 0;
	private int row = 0;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException();
		}
		row = N;
		col = N;
		wquf = new WeightedQuickUnionUF(N * N + 2);
		wqufFull = new WeightedQuickUnionUF(N * N + 1);
		isOpenMark = new boolean[N][N];

	}

	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
		if (i < 1 || j < 1 || i > row || j > col) {
			throw new IndexOutOfBoundsException();
		}
		if (isOpen(i, j)) {
			return;
		}
		isOpenMark[i - 1][j - 1] = true;
		int index = (i - 1) * col + j - 1;
		if (i == 1) {
			wquf.union(index, row * col);
			wqufFull.union(index, row * col);
		}
		if (i == row) {
			wquf.union(index, row * col + 1);
		}
		for (int k = 0; k < 4; k++) {
			int x = i - 1 + DX[k];
			int y = j - 1 + DY[k];
			if (isOutOfRange(x, y) || !isOpen(x + 1, y + 1)) {
				continue;
			}
			int neighborIndex = x * col + y;
			wquf.union(index, neighborIndex);
			wqufFull.union(index, neighborIndex);
		}
	}

	private boolean isOutOfRange(int x, int y) {
		if (x < 0 || y < 0 || x >= row || y >= col) {
			return true;
		}
		return false;
	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		if (i < 1 || j < 1 || i > row || j > col) {
			throw new IndexOutOfBoundsException();
		}
		if (isOpenMark[i - 1][j - 1]) {
			return true;
		}
		return false;
	}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		if (i < 1 || j < 1 || i > row || j > col) {
			throw new IndexOutOfBoundsException();
		}
		int index = (i - 1) * col + j - 1;
		return isOpen(i, j) && wqufFull.connected(index, row * col);
	}

	// does the system percolate?
	public boolean percolates() {
		return wquf.connected(row * col, row * col + 1);
	}

	// test client (optional)
	public static void main(String[] args) {

	}
}