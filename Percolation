import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
	private boolean[][] statu;
	private WeightedQuickUnionUF uf;
	private int opensites;
	
    public Percolation(int n){
		statu = new boolean[n][n];
		uf = new WeightedQuickUnionUF(n*n+2);  // create a UF with 2 more elements to stand for top-start and bottoem-end.
		for(int row = 1; row < n; row++){
			for(int col = 1; col <n; col++){
				statu[row-1][col-1] = false;
			}	
		}
	}

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
		if (isOpen(row,col) == false){
		statu[row-1][col-1] = true;
		int n = statu.length;
		int index = n*(row-1)+col;
		if(row == 1){ uf.union(0,index);}   
		if(row == n){ uf.union(n*n+1,index);}
		if(row >=2){   //union the current site with the adjacent open site.
			if(isOpen(row-1,col)){
				uf.union(index-n,index);
			}
		}
		if(row <=n-1){ 
			if(isOpen(row+1,col)){
				uf.union(index+n,index);
			}
		}
		if(col >=2){ 
			if(isOpen(row,col-1)){
				uf.union(index-1,index);
			}
		}
		if(col <=n-1){ 
			if(isOpen(row,col+1)){
				uf.union(index+1,index);
			}
		}
		opensites++;
		}
	}

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
		return statu[row-1][col-1];
	}

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
		int n = statu.length;
		int index = n*(row-1)+col;
		return uf.connected(index,0);
	}
		
    // returns the number of open sites
    public int numberOfOpenSites(){
		return opensites;
	}

    // does the system percolate?
    public boolean percolates(){
		int n = statu.length;
		return uf.connected(0,n*n+1);
		}

    // test client (optional)
    public static void main(String[] args){
		
	}
}
