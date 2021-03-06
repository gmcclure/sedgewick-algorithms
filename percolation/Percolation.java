public class Percolation {

    /* private */ public WeightedQuickUnionUF uf;
    /* private */ public int gridSize;
    private int[][] grid;

    public Percolation(int N) {
        gridSize = N + 2;
        uf = new WeightedQuickUnionUF(gridSize*gridSize);
        grid = new int[gridSize][gridSize];

        // initialize grid
        for (int i = 0; i < gridSize; i++) {
            grid[0][i] = 1;
            grid[gridSize - 1][i] = 1;
        }

        // connect top row of grid via uf
        for (int i = 0; i < gridSize - 1; i++) {
            uf.union(rcToUFIdx(0, i), rcToUFIdx(0, i + 1));
            uf.union(rcToUFIdx(gridSize - 1, i), rcToUFIdx(gridSize - 1, i + 1));
        }
    }

    private void checkBounds(int i, int j) {
        int nSize = gridSize - 2;
        if (i > nSize || j > nSize || i < 1 || j < 1) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
    }

    /* private */ public int rcToUFIdx(int r, int c) {
        return r * gridSize + c;
    }

    public void open(int i, int j) {
        checkBounds(i, j);
        grid[i][j] = 1;

        if (grid[i-1][j] == 1) { uf.union(rcToUFIdx(i, j), rcToUFIdx(i-1, j)); }
        if (grid[i+1][j] == 1) { uf.union(rcToUFIdx(i, j), rcToUFIdx(i+1, j)); }
        if (grid[i][j-1] == 1) { uf.union(rcToUFIdx(i, j), rcToUFIdx(i, j-1)); }
        if (grid[i][j+1] == 1) { uf.union(rcToUFIdx(i, j), rcToUFIdx(i, j+1)); }
    }

    public boolean isOpen(int i, int j) {
        checkBounds(i, j);
        return grid[i][j] == 1;
    }

    public boolean isFull(int i, int j) {
        checkBounds(i, j);
        return uf.connected(rcToUFIdx(0, 0), rcToUFIdx(i, j));
    }

    public boolean percolates() {
        if (uf.connected(rcToUFIdx(0, 0), rcToUFIdx(gridSize - 1, gridSize - 1))) {
            return true;
        }
        return false;
    }
}
