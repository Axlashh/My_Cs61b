package hw4.puzzle;

import java.util.Iterator;
import edu.princeton.cs.algs4.Queue;
public class Board implements WorldState{

    private int[][] tiles;
    private int size;
    private int estimatedDist;


    public Board(int[][] tiles) {
        this.size = tiles.length;
        estimatedDist = -1;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < size && j >=0 && j < size;
    }

    public int tileAt(int i, int j) {
        if (!isValid(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return size;
    }
    private class ne implements Iterable<WorldState> {

        private class neit implements Iterator<WorldState> {
            private WorldState[] queue = new WorldState[4];
            private int cur;
            private int num;
            private int posX;
            private int posY;
            private int[][] h = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

            public neit() {
                for (int i = 0; i < size; i += 1) {
                    boolean isFound = false;
                    for (int j = 0; j < size; j += 1) {
                        if (tiles[i][j] == 0) {
                            posX = i;
                            posY = j;
                            isFound = true;
                            break;
                        }
                    }
                    if (isFound) {
                        break;
                    }
                }
                num = 0;
                cur = 0;
                for (int i = 0; i < 4; i += 1) {
                    if (isValid(posX + h[i][0], posY + h[i][1])) {
                        int[][] temp = new int[tiles.length][];
                        for (int j = 0; j < tiles.length; j += 1) {
                            temp[j] = tiles[j].clone();
                        }
                        int k = temp[posX][posY];
                        temp[posX][posY] = temp[posX + h[i][0]][posY + h[i][1]];
                        temp[posX + h[i][0]][posY + h[i][1]] = k;
                        Board nei = new Board(temp);
                        queue[num] = nei;
                        num += 1;
                    }
                }
            }

            public boolean hasNext() {
                return cur < num;
            }

            public WorldState next() {
                WorldState ret = queue[cur];
                cur += 1;
                return ret;
            }
        }

        public Iterator<WorldState> iterator() {
            return new neit();
        }
    }


    public Iterable<WorldState> neighbors() {
        return new ne();
    }

    public int hamming() {
        int ret = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (tiles[i][j] != i * size + j + 1) {
                    ret += 1;
                }
            }
        }
        return ret - 1;
    }

    public int manhattan() {
        int ret = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (tiles[i][j] == 0) {
                    continue;
                }/*
                int targeti = (tiles[i][j] - 1) / size;
                int targetj = (tiles[i][j] - 1) % size;
                ret += Math.abs(targeti - i);
                ret += Math.abs(targetj - j);*/
                ret += Math.abs(i - ((tiles[i][j] - 1) / size)) + Math.abs(j - ((tiles[i][j] - 1) % size));
            }
        }
        return ret;
    }


    public int estimatedDistanceToGoal() {
        if (estimatedDist == -1) {
            estimatedDist = manhattan();
        }
        return estimatedDist;
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (this == y) {
            return true;
        }
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (tiles[i][j] != ((Board) y).tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int size = size();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
