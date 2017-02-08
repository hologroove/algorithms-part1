//import java.lang.Math;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private Double meanValue = 0.0;
    private Double stddevValue = 0.0;
    private Double confLoValue = 0.0;
    private Double confHiValue = 0.0;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }
        double[] thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                //choose a site randomly
                int rowIndex = StdRandom.uniform(n) + 1;
                int colIndex = StdRandom.uniform(n) + 1;
                p.open(rowIndex, colIndex);
            }
            thresholds[i] = (double) p.numberOfOpenSites() / (n*n);
        }
        meanValue = StdStats.mean(thresholds);
        stddevValue = StdStats.stddev(thresholds);
        confLoValue = meanValue - (1.96*stddevValue / Math.sqrt(trials));
        confHiValue = meanValue + (1.96*stddevValue / Math.sqrt(trials));
    }

    // sample mean of percolation threshold
    public double mean() {
        return meanValue;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddevValue;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return confLoValue;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confHiValue;
    }

    // test client (described below)
    public static void main(String[] args) {
        PercolationStats p = new PercolationStats(Integer.parseInt(args[0]),
                                                  Integer.parseInt(args[1]));
        System.out.println("mean   : " + p.mean());
        System.out.println("stddev : " + p.stddev());
        System.out.println("conflo : " + p.confidenceLo());
        System.out.println("confhi : " + p.confidenceHi());
    }
}
