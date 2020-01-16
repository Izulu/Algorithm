import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

// Statistcs of Percolation 
public class PercolationStats {
	private Percolation p;
	private double[] results;
	private double mean;
	private double stddev;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
		results = new double[trials];
		for (int i = 0; i<trials; i++){
		Percolation p = new Percolation(n);
		while(p.percolates()==false){
		int rand_row = StdRandom.uniform(1,n+1);
		int rand_col = StdRandom.uniform(1,n+1);
		p.open(rand_row,rand_col);
		}
		results[i] = 1.0 * p.numberOfOpenSites()/(n*n);
		}
		mean = StdStats.mean(results);
		stddev = StdStats.stddev(results);
		}
		
    // sample mean of percolation threshold
    public double mean(){
		return mean;
	}

    // sample standard deviation of percolation threshold
    public double stddev(){
		return stddev;
	}

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
		return mean - 1.96 * stddev / Math.sqrt(results.length);
	}

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
		return mean + 1.96 * stddev / Math.sqrt(results.length);
	}
   // test client (see below)
   public static void main(String[] args){
	int n = StdIn.readInt();
	int trials = StdIn.readInt();
	PercolationStats p = new PercolationStats(n,trials);
	StdOut.println("mean = " + p.mean());
	StdOut.println("stddev = " + p.stddev());
	StdOut.println("95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");
   }

}
