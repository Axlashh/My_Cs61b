package hw2;

import java.util.Random;

public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        double[] data = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation test = pf.make(N);
            Random r = new Random();
            while (!test.percolates()) {
                int row = r.nextInt(N);
                int col = r.nextInt(N);
                test.open(row, col);
            }
            data[i] = (double) test.numberOfOpenSites() / (double) (N * N);
        }
        double sum = 0;
        for (double i : data) {
            sum += i;
        }
        mean = sum / T;
        double k = 0;
        for (double i : data) {
            k += Math.pow((i - mean), 2);
        }
        stddev = Math.sqrt((k / (T - 1)));
        confidenceLow = mean - (1.96 * stddev / Math.sqrt(T));
        confidenceHigh = mean + (1.96 * stddev / Math.sqrt(T));
    }



    /*sample standard deviation of percolation threshold*/
    public double stddev() {
        return stddev;
    }

    /*sample mean of percolation threshold*/
    public double mean() {
        return mean;
    }

    public double confidenceLow() {
        return confidenceLow;
    }

    public double confidenceHigh() {
        return confidenceHigh;
    }

}
