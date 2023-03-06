package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size;
    private WeightedQuickUnionUF map;
    private int onum;
    private boolean[][] omap;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be positive!");
        }
        omap = new boolean[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                omap[i][j] = false;
            }
        }
        map = new WeightedQuickUnionUF(N * N);
        onum = 0;
        size = N;
    }

    public int pos(int row, int col) {
        return row * size + col;
    }

    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Cant open !");
        }
        onum += 1;
        omap[row][col] = true;
        if (row + 1 < size && isOpen(row + 1, col))
            map.union(pos(row + 1, col), pos(row, col));
        if (row - 1 >= 0 && isOpen(row - 1, col))
            map.union(pos(row - 1, col), pos(row, col));
        if (col + 1 < size && isOpen(row, col + 1))
            map.union(pos(row, col + 1), pos(row, col));
        if (col - 1 >= 0 && isOpen(row, col - 1))
            map.union(pos(row, col - 1), pos(row, col));
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Cant isOpen!");
        }
        return omap[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Cant isFull!");
        }
        if (!isOpen(row, col)) return false;
        for (int i = 0; i < size; i += 1) {
            if (map.connected(pos(row, col), pos(0, i)))
                return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return onum;
    }

    public boolean percolates() {
        int row = size - 1;
        for (int i = 0; i < size; i += 1) {
            if (isFull(row, i)) {
                return true;
            }
        }
        return false;
    }
}
