package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int size;
    private int visited;
    private int source;
    private boolean cycle = false;
    private int[] unedgeTo;

    public MazeCycles(Maze m) {
        super(m);
        size = m.V();
        visited = 0;
        source = m.xyTo1D(1, 1);
        distTo[source] = 0;
        marked[source] = true;
        unedgeTo = new int[m.V()];
        for (int i = 0; i < m.V(); i += 1) {
            unedgeTo[i] = i;
        }
    }

    @Override
    public void solve() {
        dfs(source);
    }

    // Helper methods go here
    private void dfs(int s) {
        if (visited == size) {
            return;
        }
        visited += 1;
        for (int i : maze.adj(s)) {
            if (marked[i] && unedgeTo[s] != i) {
                int cy = s;
                while (cy != i) {
                    edgeTo[cy] = unedgeTo[cy];
                    cy = unedgeTo[cy];
                }
                edgeTo[i] = s;
                announce();
                cycle = true;
                break;
            } else if (marked[i] && unedgeTo[s] == i) {
                continue;
            }
            marked[i] = true;
            distTo[i] = distTo[s] + 1;
            unedgeTo[i] = s;
            announce();
            dfs(i);
            if (cycle) {
                break;
            }
        }
    }
}

