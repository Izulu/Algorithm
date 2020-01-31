import edu.princeton.cs.algs4.Queue;

public class Board {
    private int N;
    private int[][] tiles;
    private int HammingDistance = -1;  // Initialization as negative number to check whether it has been calculated before.
    private int ManhattanDistance = -1;


    public Board(int[][] t) {
            // create a board from an n-by-n array of tiles(t),
            // where tiles[row][col] = tile at (row, col)
            N = t.length;
            int[][] board = new int[N][N];
            for(int i=0; i<N;i++){
                for(int j=0;j<N;j++){
                    board[i][j] = t[i][j];
                }
            }
            tiles = board;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();

    }

    public int dimension(){
        return N;
    }

    public int hamming(){
        if (HammingDistance == -1) {
            HammingDistance = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tiles[i][j] != i * N + j + 1 && tiles[i][j] != 0) {
                        HammingDistance++;
                    }
                }
            }
        }
        return HammingDistance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        if (ManhattanDistance == -1) {  // Avoid re-calculating. Updating in swap steps.
            ManhattanDistance = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tiles[i][j] != 0) {
                        ManhattanDistance += Math.abs((tiles[i][j] - 1) / N - i) + Math.abs((tiles[i][j] - 1) % N - j);
                    }
                }
            }
        }
        return ManhattanDistance;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return HammingDistance == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if(this == y) {return true;}
        if(y == null) {return false;}
        if(this.getClass() != y.getClass()) {return false;}
        Board that = (Board) y;
        if(N!=that.dimension()) {return false;}
        for(int i=0; i <N;i++) {
            for(int j=0; j<N;j++) {
                if(this.tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<Board>();
        int m = 0;
        int n = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    m = i;
                    n = j;
                    break;
                }
            }
        }
        if (m + 1 < N) {
            neighbors.enqueue(swap(m, n, m + 1, n));
        }
        if (m - 1 >= 0) {
            neighbors.enqueue(swap(m, n, m - 1, n));
        }
        if (n + 1 < N) {
            neighbors.enqueue(swap(m, n, m, n + 1));
        }
        if (n - 1 >= 0) {
            neighbors.enqueue(swap(m, n, m, n - 1));
        }
        return neighbors;
    }

    private int HammingUnit(int row, int col) {
        if(tiles[row][col] != row*N + col+1 && tiles[row][col] != 0) {
            return 1;
        }
        return 0;
    }

    private int ManhattanUnit(int row, int col){
            if (tiles[row][col] != 0) {
                return Math.abs((tiles[row][col] - 1) / N - row) + Math.abs((tiles[row][col] - 1) % N - col);
            }
            return 0;
        }


    private Board swap(int k, int l, int m, int n) {  // swap blank space with neighbor tile.
        int[][] copy = new int[N][N];
        for(int i=0; i<N;i++){
            for(int j=0;j<N;j++){
                copy[i][j] = tiles[i][j];
            }
        }
        int temp = copy[k][l];
        copy[k][l] = copy[m][n];
        copy[m][n] = temp;
        Board result_board = new Board(copy);
        result_board.HammingDistance = this.hamming() + result_board.HammingUnit(k,l) + result_board.HammingUnit(m,n) - this.HammingUnit(k,l) -this.HammingUnit(m,n);
        result_board.ManhattanDistance = this.manhattan() + result_board.ManhattanUnit(k,l) + result_board.ManhattanUnit(m,n) - this.ManhattanUnit(k,l) - this.ManhattanUnit(m,n);
        return result_board;
     }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if(tiles[0][0] != 0 && tiles[0][1]!=0) {
            return swap(0,0,0,1);
        }
        else{
            return swap(1,0,1,1);
        }
    }

    // unit testing (not graded)
    public static void main(String[] args) {
            int[][] t = new int[3][3];
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++){
                    t[i][j] = i*3+j+1;
                }
            }
            t[2][2] = 0;
            Board board1 = new Board(t);
            Board board2 = board1.swap(2,1,2,2);
            Board board3 = board2.swap(1,1,2,1);
            Board board4 = board3.swap(1,2,2,2);
            System.out.println(board4);
            System.out.println(board4.manhattan());
            System.out.println(board4.hamming());
            Iterable<Board> neighbors = board4.neighbors();
            for (Board n : neighbors) {
                System.out.println(n);
                System.out.println(n.manhattan());
                System.out.println(n.hamming());
            }
    }

}
