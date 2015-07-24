
public class Percolation {
	
	WeightedQuickUnionUF wquf;
	boolean[][] isOpenMark;
	int col = 0;
	int row = 0;
	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		if (N <= 0) {
			throw new IndexOutOfBoundsException();
		}
		row = N;
		col = N;
		wquf = new WeightedQuickUnionUF(N*N + 2);
		isOpenMark = new boolean[N][N];
		
		for(int j = 0; j < col;j++){
			wquf.union(j, row * col);
		}
		int i = row - 1;
		for(int j = 0; j < col; j++){
			wquf.union(i * col + j, row * col + 1);
		}
	}

	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
		if(isOpen(i, j)){
			return;
		}
		isOpenMark[i-1][j-1] = true;
		int index = (i-1) * col + j-1;
		wquf.union(index, row * col);
	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		if(isOpenMark[i-1][j-1]){
			return true;
		}
		return false;
	}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		// TODO
		return false;
	}

	// does the system percolate?
	public boolean percolates() {
		// TODO
		return false;
	}

	// test client (optional)
	public static void main(String[] args) {

	}
}