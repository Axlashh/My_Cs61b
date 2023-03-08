package lab11.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private PriorityQueue<unity> pq;
    private class unity implements Comparable{
        private int p;
        private int pr;
        public unity(int pos, int priority) {
            p = pos;
            pr = priority;
        }

        @Override
        public int compareTo(Object o) {
            unity a = (unity) o;
            return this.pr - a.priority();
        }

        public int pos() {
            return p;
        }

        public int priority() {
            return pr;
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        for (int i = 0; i < maze.V(); i += 1) {
            distTo[i] = -1;
        }
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
        pq = new PriorityQueue<>();
        announce();
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    private int u(int v) {
        return 2 * h(v) + distTo[v];
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        if (targetFound) {
            return;
        }
        for (int r : maze.adj(s)) {
            if (marked[r]) {
                continue;
            }
            if (distTo[r] == -1 || u(s) + 1 < u(r)) {
                distTo[r] = distTo[s] + 1;
                edgeTo[r] = s;
                pq.add(new unity(r, u(r)));
            }
            if (r == t) {
                targetFound = true;
                marked[t] = true;
                announce();
                break;
            }
        }
        unity temp = pq.remove();
        marked[temp.pos()] = true;
        announce();
        astar(temp.pos());
    }

    @Override
    public void solve() {
        astar(s);
    }

}

