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
        int k = N * N;
        map = new WeightedQuickUnionUF(k  + 2);
        onum = 0;
        size = N;
        for (int i = 0; i < N; i += 1) {
            map.union(pos(0, i), k);
            map.union(pos(N - 1, i), k + 1);
        }
    }

    private int pos(int row, int col) {
        return row * size + col;
    }

    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Cant open !");
        }
        if (isOpen(row, col)) {
            return;
        }
        onum += 1;
        omap[row][col] = true;
        if (row + 1 < size && isOpen(row + 1, col)) {
            map.union(pos(row + 1, col), pos(row, col));
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            map.union(pos(row - 1, col), pos(row, col));
        }
        if (col + 1 < size && isOpen(row, col + 1)) {
            map.union(pos(row, col + 1), pos(row, col));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            map.union(pos(row, col - 1), pos(row, col));
        }
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
        if (!isOpen(row, col)) {
            return false;
        }
        return map.connected(pos(row, col), size * size)
            || percolates() && map.connected(pos(row, col), size * size + 1);
    }

    public int numberOfOpenSites() {
        return onum;
    }

    public boolean percolates() {
        if (size == 1) {
            return isOpen(0, 0);
        }
        return map.connected(size * size, size * size + 1);
    }

    public static void main(String[] args) {
    }
}
