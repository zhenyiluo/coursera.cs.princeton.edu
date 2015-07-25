public class Percolation {

	WeightedQuickUnionUF wquf;
	WeightedQuickUnionUF wqufFull;
	boolean[][] isOpenMark;
	int col = 0;
	int row = 0;
	final static int[] dx = { -1, 1, 0, 0 };
	final static int[] dy = { 0, 0, -1, 1 };

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

		for (int j = 0; j < col; j++) {
			wquf.union(j, row * col);
			wqufFull.union(j, row * col);
		}
		int i = row - 1;
		for (int j = 0; j < col; j++) {
			wquf.union(i * col + j, row * col + 1);
		}
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
		for (int k = 0; k < 4; k++) {
			int x = i - 1 + dx[k];
			int y = j - 1 + dy[k];
			if (isOutOfRange(x, y)) {
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