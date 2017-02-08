import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    //create n-by-n grid with all sites blocked


    private int height = 0;
    private int width  = 0;
    private int virtualTop = 0;
    private int virtualBottom = 0;
    private int[] field = null;
    private int numOfOpen = 0;
    private WeightedQuickUnionUF uf = null;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        //new array: 0 - n*n-1
        field = new int[n*n];

        //set private height, width
        height = n;
        width = n; //TODO: not needed

        //create WeightedQuickUnionUF(1dsize + 2)
        //2 extra nodes will be used for virtual top and bottom
        uf = new WeightedQuickUnionUF(n*n+2);
        virtualTop = n*n;
        virtualBottom = n*n+1;

        //connect virtual top to toprow
        //connect virtual bottom to bottom row
        for (int i=1; i<=n; i++) {
            uf.union(virtualTop, toArrayIndex(1, i));
            uf.union(virtualBottom, toArrayIndex(n, i));
        }
    }

    //open site (row, col) if it is not open already
    public void open(int row, int col) {
        //if it is not already open
        if (isFull(row, col)) {
            //convert row, col to 1d index
            int index = toArrayIndex(row, col);
            //set field[index] to 1
            field[index] = 1;

            //if not top row, union(index, index-1row)
            if (row > 1) {
                uf.union(index, toArrayIndex(row - 1, col));
            }
            //if not bottom row, union(index, index+1row)
            if (row < height) {
                uf.union(index, toArrayIndex(row + 1, col));
            }
            //if not 1st column, union(index, index-1)
            if (col > 1) {
                uf.union(index, toArrayIndex(row, col - 1));
            }
            //if not last column, union(index, index+1)
            if (col < width) {
                uf.union(index, toArrayIndex(row, col + 1));
            }

            numOfOpen++;
        }
    }

    //is site (row, col) open?
    public boolean isOpen(int row, int col) {
        //if field[index] equals 1, then true
        return (field[toArrayIndex(row, col)] == 1) ? true : false;
    }

    //is site (row, col) full?
    public boolean isFull(int row, int col) {
        return (field[toArrayIndex(row, col)] == 0) ? true : false;
    }

    //number of open sites
    public int numberOfOpenSites() {
        return numOfOpen;
    }

    //does the system percolate
    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

    //convert 2d-1d
    private int toArrayIndex(int row, int col) {
        if (row < 1 || row > height || col < 1 || col > width) {
            throw new IndexOutOfBoundsException();
        }
        return (row-1)*width + col - 1;
    }


    //test client
    public static void main(String[] args) {
//        Percolation p = new Percolation(5);
//        p.open(1,1);
//        p.open(2,1);
//        p.open(3,1);
//        System.out.println("percolates:" + p.percolates());
//        p.open(4,1);
//        p.open(5,1);
//        System.out.println("percolates:" + p.percolates());

    }
}
