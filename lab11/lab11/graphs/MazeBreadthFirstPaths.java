package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean found = false;
    Queue<Integer> q;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        s = m.xyTo1D(sourceX, sourceY);
        t = m.xyTo1D(targetX, targetY);
        q = new LinkedList<>();
        q.add(s);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        while (!found) {
            int begin = q.remove();
            for (int rec : maze.adj(begin)) {
                if (!marked[rec]) {
                    edgeTo[rec] = begin;
                    marked[rec] = true;
                    distTo[rec] = distTo[begin] + 1;
                    announce();
                    if (rec == t) {
                        found = true;
                        break;
                    }
                    q.add(rec);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

