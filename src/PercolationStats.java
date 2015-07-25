
public class PercolationStats {
	// perform T independent experiments on an N-by-N grid
	private double[] result;

	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException();
		}
		result = new double[T];
		for (int k = 0; k < T; k++) {
			Percolation perc = new Percolation(N);
			int count = 0;
			while (!perc.percolates()) {
				int i = StdRandom.uniform(1, N + 1);
				int j = StdRandom.uniform(1, N + 1);
				if (!perc.isOpen(i, j)) {
					perc.open(i, j);
					count++;
				}
			}
			result[k] = (double) count / (N * N);
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		double sum = 0;
		for (double d : result) {
			sum += d;
		}
		return sum / result.length;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		double u = mean();
		double ret = 0;
		for (double d : result) {
			ret += Math.pow(d - u, 2);
		}
		ret /= result.length - 1;
		return Math.sqrt(ret);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		double u = mean();
		double dev = stddev();
		return u - 1.96 * dev / Math.sqrt(result.length);
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		double u = mean();
		double dev = stddev();
		return u + 1.96 * dev / Math.sqrt(result.length);
	}

	// test client (described below)
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(N, T);

		String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + confidence);
	}
}
